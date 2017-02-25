package cn.blinkmind.flame.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushDTO extends BaseDTO {
    private SnapshotDTO snapshot;
}
