package io.bayberry.core.repository.id;

public interface IdGenerator<T> {

    T nextId();
}
