package com.chronoswood.doublechoose.exception;

import com.chronoswood.doublechoose.model.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BizException extends RuntimeException {
    private Message errMsg;

    public BizException(Message message) {
        super(message.getMessage());
        this.errMsg = message;
    }
}
