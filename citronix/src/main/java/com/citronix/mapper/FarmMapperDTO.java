package com.citronix.mapper;

import com.citronix.dto.req.FarmDTO;
import com.citronix.model.Farm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FarmMapperDTO {
    FarmMapperDTO INSTANCE = Mappers.getMapper(FarmMapperDTO.class);

    @Mapping(source = "farmDTO.id", target = "id") // farmId in BaseEntity is inherited as id
    @Mapping(source = "farmDTO.name", target = "farmName")
    @Mapping(source = "farmDTO.address", target = "farmAddress")
    @Mapping(source = "farmDTO.surface", target = "farmSurface")
    Farm toEntity(FarmDTO farmDTO);

    @Mapping(source = "farm.id", target = "id") // farmId is represented by id in BaseEntity
    @Mapping(source = "farm.farmName", target = "name")
    @Mapping(source = "farm.farmAddress", target = "address")
    @Mapping(source = "farm.farmSurface", target = "surface")
    FarmDTO toDTO(Farm farm);
}
