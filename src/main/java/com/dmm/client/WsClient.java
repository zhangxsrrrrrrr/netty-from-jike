package com.dmm.client;

import com.dmm.auth.AuthOperation;
import com.dmm.client.codec.*;
import com.dmm.common.RequestMessage;
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
public class WsClient {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
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
                        pipeline.addLast(new OperationToRequestMwssageEncoder());
                    }
                });
        ChannelFuture channelFuture = bootstrap.connect("localhost", 8080).sync();
        channelFuture.channel().writeAndFlush(new AuthOperation("zhangsan"));
        channelFuture.channel().closeFuture().get();

    }
}
