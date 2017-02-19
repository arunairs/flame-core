package cn.blinkmind.flame.server.repository.entity;

import lombok.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Version {
    private Integer major;
    private Integer minor;
    private Integer patch;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
