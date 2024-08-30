package com.damvih.mappers;

import com.damvih.dto.PlayerResponseDto;
import com.damvih.entities.Player;
import org.mapstruct.Mapper;

@Mapper
public interface PlayerMapper {

    PlayerResponseDto toDto(Player player);

}
