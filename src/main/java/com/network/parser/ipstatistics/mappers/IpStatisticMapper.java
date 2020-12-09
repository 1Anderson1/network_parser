package com.network.parser.ipstatistics.mappers;

import com.network.parser.ipstatistics.DTOs.IpStatisticResponseDTO;
import com.network.parser.ipstatistics.entities.IpStatistic;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IpStatisticMapper {

    IpStatisticResponseDTO toDto(IpStatistic ipStatistic);

    IpStatistic toEntity(IpStatisticResponseDTO ipStatisticResponseDTO);

    List<IpStatisticResponseDTO> toDtoList(List<IpStatistic> ipStatistic);

    List<IpStatistic> toEntityList(List<IpStatisticResponseDTO> ipStatisticResponseDTO);
}
