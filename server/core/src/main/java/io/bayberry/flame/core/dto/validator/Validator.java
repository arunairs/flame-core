package io.bayberry.flame.core.dto.validator;

public abstract class Validator<T> {

    public abstract boolean isValid(T target);
}
