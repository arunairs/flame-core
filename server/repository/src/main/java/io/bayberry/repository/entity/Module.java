package io.bayberry.repository.entity;

import io.bayberry.common.protocol.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class Module implements Persistable<Long> {
    @Id
    private Long id;
    private String name;
    private String description;
    private Request request;
    private Ref<Long> parent;
    private List<Ref<Long>> moduleOrder;
    private List<Ref<Long>> apiOrder;
}
