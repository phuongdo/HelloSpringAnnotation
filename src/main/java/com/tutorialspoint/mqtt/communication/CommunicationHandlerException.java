package com.tutorialspoint.mqtt.communication;

/**
 * Created by phuongdv on 6/6/17.
 */
public class CommunicationHandlerException extends Exception {
    private static final long serialVersionUID = 2736466230451105440L;

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public CommunicationHandlerException(String msg, Exception nestedEx) {
        super(msg, nestedEx);
        setErrorMessage(msg);
    }

    public CommunicationHandlerException(String message, Throwable cause) {
        super(message, cause);
        setErrorMessage(message);
    }

    public CommunicationHandlerException(String msg) {
        super(msg);
        setErrorMessage(msg);
    }

    public CommunicationHandlerException() {
        super();
    }

    public CommunicationHandlerException(Throwable cause) {
        super(cause);
    }
}