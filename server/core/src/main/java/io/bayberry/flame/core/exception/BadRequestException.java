package io.bayberry.flame.core.exception;

import io.bayberry.flame.core.common.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BadRequestException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final Error error;

    public BadRequestException(Error error) {
        this(HttpStatus.BAD_REQUEST, error);
    }
}
