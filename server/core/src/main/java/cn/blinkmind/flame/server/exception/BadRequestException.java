package cn.blinkmind.flame.server.exception;

import org.springframework.http.HttpStatus;

import java.lang.*;

public class BadRequestException extends RuntimeException
{
    private Error error;

    public BadRequestException(HttpStatus httpStatus, Integer code, String message)
    {
        this(new Error(httpStatus, code, message));
    }

    public BadRequestException(Error error)
    {
        this.error = error;
    }

    public Error getError()
    {
        return error;
    }

    private void setError(Error error)
    {
        this.error = error;
    }
}
