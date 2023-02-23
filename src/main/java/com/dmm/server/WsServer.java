package com.dmm.server;

import com.dmm.server.codec.AuthFrameDecoder;
import com.dmm.server.codec.AuthFrameEncoder;
import com.dmm.server.codec.AuthProtocolDecoder;
import com.dmm.server.codec.AuthProtocolEncoder;
import com.dmm.common.RequestMessage;
import com.dmm.server.codec.handler.AuthServerProcessHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author: zhangxun
 * @create: 2023-02-23 17:36
 * @description:
 **/
public class WsServer {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            pipeline.addLast(new AuthFrameDecoder());
                            pipeline.addLast(new AuthProtocolDecoder());
                            pipeline.addLast(new AuthFrameEncoder());
                            pipeline.addLast(new AuthProtocolEncoder());
                            pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                            pipeline.addLast(new AuthServerProcessHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().addListener((ChannelFutureListener) channelFuture1 -> System.out.println("关闭了 ... "));
        } catch (InterruptedException e) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
