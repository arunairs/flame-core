package cn.blinkmind.flame.core.dto;

import cn.blinkmind.flame.repository.entity.Branch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SnapshotDTO extends BaseDTO {
    private Branch branch;

}
