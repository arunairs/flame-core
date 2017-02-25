package cn.blinkmind.flame.core.dto;

import cn.blinkmind.flame.common.util.BeanUtils;
import cn.blinkmind.flame.repository.entity.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DocumentDTO extends BaseDTO {
    private String name;
    private String description;

    public DocumentDTO(Document document) {
        BeanUtils.copyProperties(this, document);
    }
}
