package com.dmm.common;

import lombok.ToString;

/**
 * @author zhangxun_a
 * @date 2023/2/22 22:52
 * @description: TODO
 */
@ToString
public class RequestMessage extends Message<Operation> {
    @Override
    public Class getMessageBodyDecodeClass(int opCode) {
        return OperationType.fromCode(opCode).getOperationClazz();
    }

    public RequestMessage() {
    }

    public RequestMessage(Long streamId, Operation operation) {
        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setStreamId(streamId);
        messageHeader.setOpCode(OperationType.fromOperation(operation).getOpCode());

        this.setMessageHeader(messageHeader);
        this.setMessageBody(operation);
    }
}
