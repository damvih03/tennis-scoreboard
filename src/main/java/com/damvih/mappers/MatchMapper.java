package com.damvih.mappers;

import com.damvih.dto.MatchResponseDto;
import com.damvih.entities.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    List<MatchResponseDto> toDto(List<Match> matches);

}
