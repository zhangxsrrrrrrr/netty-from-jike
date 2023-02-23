package com.dmm.client;

import com.dmm.auth.AuthOperation;
import com.dmm.client.codec.*;
import com.dmm.client.dispatch.OperationResultFuture;
import com.dmm.client.dispatch.RequestPendingCenter;
import com.dmm.client.dispatch.handler.ResponseDispatcherHandler;
import com.dmm.common.MessageHeader;
import com.dmm.common.OperationResult;
import com.dmm.common.RequestMessage;
import com.google.gson.Gson;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.ExecutionException;

/**
 * @author: zhangxun
 * @create: 2023-02-23 21:24
 * @description:
 **/
public class WsClientV1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        RequestPendingCenter requestPendingCenter = new RequestPendingCenter();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        ChannelPipeline pipeline = nioSocketChannel.pipeline();
                        pipeline.addLast(new AuthFrameDecoder());
                        pipeline.addLast(new AuthProtocolDecoder());
                        pipeline.addLast(new AuthFrameEncoder());
                        pipeline.addLast(new AuthProtocolEncoder());
                        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
//                        pipeline.addLast(new OperationToRequestMwssageEncoder());
                        pipeline.addLast(new ResponseDispatcherHandler(requestPendingCenter));
                    }
                });
        ChannelFuture channelFuture = bootstrap.connect("localhost", 8080).sync();
        long id = System.currentTimeMillis();
        OperationResultFuture operationResultFuture = new OperationResultFuture();
        requestPendingCenter.add(id, operationResultFuture);
        RequestMessage requestMessage = new RequestMessage();
        MessageHeader messageHeader = new MessageHeader(id);
        messageHeader.setOpCode(1);
        requestMessage.setMessageHeader(messageHeader);
        requestMessage.setMessageBody(new AuthOperation("zhangsan"));
        channelFuture.channel().writeAndFlush(requestMessage);
        OperationResult operationResult = operationResultFuture.get();
        System.out.println(new Gson().toJson(operationResult));
        channelFuture.channel().closeFuture().get();

    }
}
