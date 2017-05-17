package io.bayberry.core.repository.entity;

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

    public static boolean isValid(Version version) {
        return version.getMajor() != null && version.getMinor() != null && version.getPatch() != null;
    }
}
