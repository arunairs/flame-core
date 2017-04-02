package io.bayberry.repository.model;

import java.io.Serializable;

public interface Persistable<ID extends Serializable>{
    ID getId();
}
