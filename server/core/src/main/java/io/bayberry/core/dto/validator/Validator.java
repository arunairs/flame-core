package io.bayberry.core.dto.validator;

public abstract class Validator<T> {

    public abstract boolean isValid(T target);
}
