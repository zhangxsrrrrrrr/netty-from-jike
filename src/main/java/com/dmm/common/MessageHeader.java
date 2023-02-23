package com.dmm.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangxun_a
 * @date 2023/2/22 22:36
 * @description: TODO
 */
@Data
@NoArgsConstructor
public class MessageHeader {
    private int version = 1;
    private long streamId;
    private int opCode;

    public MessageHeader(long streamId) {
        this.streamId = streamId;
    }
}
