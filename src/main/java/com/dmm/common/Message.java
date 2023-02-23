package com.dmm.common;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author zhangxun_a
 * @date 2023/2/22 22:33
 * @description: TODO
 */
public abstract class Message<T extends MessageBody> {
    private MessageHeader messageHeader;
    private T messageBody;

    public MessageHeader getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }

    public T getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(T messageBody) {
        this.messageBody = messageBody;
    }

    Gson gson = new Gson();

    // 编码 content --> byte
    public void encode(ByteBuf byteBuf) {
        byteBuf.writeInt(messageHeader.getVersion());
        byteBuf.writeLong(messageHeader.getStreamId());
        byteBuf.writeInt(messageHeader.getOpCode());
        byteBuf.writeBytes(gson.toJson(messageBody).getBytes(StandardCharsets.UTF_8));
    }

    public abstract Class<T> getMessageBodyDecodeClass(int opCode);

    // 解码 byte --> content
    public void decode(ByteBuf msg) {
        int version = msg.readInt();
        long streamId = msg.readLong();
        int opCode = msg.readInt();

        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setOpCode(opCode);
        messageHeader.setVersion(version);
        messageHeader.setStreamId(streamId);

        this.messageHeader = messageHeader;

        Class<T> bodyClass = getMessageBodyDecodeClass(opCode);
        this.messageBody = gson.fromJson(msg.toString(Charset.defaultCharset()), bodyClass);
    }
}
