package cn.blinkmind.flame.repository.model;

import java.io.Serializable;

public interface Persistable<ID extends Serializable>{
    ID getId();
}
