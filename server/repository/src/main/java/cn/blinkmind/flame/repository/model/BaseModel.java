package cn.blinkmind.flame.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public abstract class BaseModel<ID extends Serializable> implements Persistable<ID> {
    @Id
    private ID id;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

    @JsonIgnore
    private Ref<ID> creatorRef;

    @Transient
    private User creator;
}
