package io.bayberry.repository.entity;

import java.io.Serializable;

public interface Persistable<ID extends Serializable>{

    ID getId();
}
