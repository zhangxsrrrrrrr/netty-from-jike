package com.dmm.auth;

import com.dmm.common.OperationResult;

/**
 * @author zhangxun_a
 * @date 2023/2/22 22:48
 * @description: TODO
 */
public class AuthOperationResult extends OperationResult {
    private boolean passAuth;

    public AuthOperationResult(boolean passAuth) {
        this.passAuth = passAuth;
    }
}
