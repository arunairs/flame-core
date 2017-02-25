package cn.blinkmind.flame.core.service;

import cn.blinkmind.flame.core.dto.UserDTO;

public interface UserService {
    Long create(final UserDTO userDTO);
}
