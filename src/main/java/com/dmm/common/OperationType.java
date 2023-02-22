package com.dmm.common;

import com.dmm.auth.AuthOperation;
import com.dmm.auth.AuthOperationResult;
import com.dmm.keepalive.KeepAliveOperation;
import com.dmm.keepalive.KeepAliveOperationResult;

public enum OperationType {
    /**
     * auth
     */
    AUTH(1, AuthOperation.class, AuthOperationResult.class),
    /**
     * keep alive
     */
    KEEPALIVE(2, KeepAliveOperation .class, KeepAliveOperationResult .class);

    final int opCode;
    final Class<? extends Operation> operationClazz;
    final Class<? extends OperationResult> operationResultClazz;

    OperationType(int opCode, Class<? extends Operation> operationClazz, Class<? extends OperationResult> operationResultClazz) {
        this.opCode = opCode;
        this.operationClazz = operationClazz;
        this.operationResultClazz = operationResultClazz;
    }

    public static OperationType fromCode(int opCode) {
        OperationType[] values = OperationType.values();
        for (OperationType value : values) {
            if (value.opCode == opCode) {
                return value;
            }
        }
        return null;
    }

    public static OperationType fromOperation(Operation operation) {
        OperationType[] values = OperationType.values();
        for (OperationType value : values) {
            if (operation.getClass() == value.operationClazz) {
                return value;
            }
        }
        return null;
    }

    public int getOpCode() {
        return opCode;
    }

    public Class<? extends Operation> getOperationClazz() {
        return operationClazz;
    }

    public Class<? extends OperationResult> getOperationResultClazz() {
        return operationResultClazz;
    }
}
