package cn.blinkmind.flame.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PullDTO extends BaseDTO {
    private SnapshotDTO snapshot;
}
