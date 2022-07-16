package com.martikan.carrental.mapper;

import org.mapstruct.Mapper;

import com.martikan.carrental.domain.user.User;
import com.martikan.carrental.dto.user.UserDTO;

/**
 * Mapper for {@link User} and {@link UserDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {
}