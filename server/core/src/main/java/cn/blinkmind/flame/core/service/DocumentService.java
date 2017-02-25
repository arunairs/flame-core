package cn.blinkmind.flame.core.service;

import cn.blinkmind.flame.core.dto.DocumentDTO;
import cn.blinkmind.flame.core.dto.UserDTO;

public interface DocumentService {
    DocumentDTO get(final Long id, final UserDTO userDTO);

    DocumentDTO create(final DocumentDTO documentDTO, final UserDTO userDTO);
}
