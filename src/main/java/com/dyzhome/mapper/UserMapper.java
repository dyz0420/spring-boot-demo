package com.dyzhome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyzhome.model.dto.UserDTO;
import com.dyzhome.model.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author Dyz
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
