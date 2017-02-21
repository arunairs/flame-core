package cn.blinkmind.flame.core.exception;

import org.springframework.http.HttpStatus;

import java.lang.*;

public class BadRequestException extends RuntimeException
{
    private cn.blinkmind.flame.core.exception.Error error;

    public BadRequestException(HttpStatus httpStatus, Integer code, String message)
    {
        this(new cn.blinkmind.flame.core.exception.Error(httpStatus, code, message));
    }

    public BadRequestException(cn.blinkmind.flame.core.exception.Error error)
    {
        this.error = error;
    }

    public cn.blinkmind.flame.core.exception.Error getError()
    {
        return error;
    }

    private void setError(cn.blinkmind.flame.core.exception.Error error)
    {
        this.error = error;
    }
}
