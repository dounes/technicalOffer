package com.fr.technicaltestoffer.mapper;

import com.fr.technicaltestoffer.dto.UserDTO;
import com.fr.technicaltestoffer.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserDTO mapEntity2DTO(User user);
    User mapDTO2Entity(UserDTO userDTO);

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
