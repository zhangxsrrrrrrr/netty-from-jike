package com.dmm.common;

import lombok.ToString;

/**
 * @author zhangxun_a
 * @date 2023/2/22 23:08
 * @description: TODO
 */
@ToString
public class ResponseMessage extends Message<OperationResult> {
    @Override
    public Class getMessageBodyDecodeClass(int opCode) {
        return OperationType.fromCode(opCode).getOperationResultClazz();
    }
}
