package com.chronoswood.doublechoose.exception;

import com.chronoswood.doublechoose.model.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BizException extends RuntimeException {
    private static final long serialVersionUID = -5809389053058449023L;
    private Message errMsg;

    public BizException(Message message) {
        super(message.getMessage());
        this.errMsg = message;
    }

    public BizException(String message) {
        super(message);
        this.errMsg = Message.BIZ_ERROR.setErrMsg(message);
    }

    public BizException(Throwable cause) {
        super(cause);
        this.errMsg = Message.BIZ_ERROR.bindArgs(cause.getMessage());
    }
}
