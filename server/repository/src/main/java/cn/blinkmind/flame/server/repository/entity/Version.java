package cn.blinkmind.flame.server.repository.entity;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Version {
    private Integer major;
    private Integer minor;
    private Integer patch;
}
