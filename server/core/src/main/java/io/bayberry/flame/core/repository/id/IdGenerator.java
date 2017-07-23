package io.bayberry.flame.core.repository.id;

public interface IdGenerator<T> {

    T nextId();
}
