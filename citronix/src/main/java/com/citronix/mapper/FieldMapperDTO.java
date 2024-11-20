package com.citronix.mapper;

import com.citronix.dto.req.FieldDTO;
import com.citronix.dto.res.FieldDisplayDTO;
import com.citronix.model.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = FarmMapperDTO.class)
public interface FieldMapperDTO {
    FieldMapperDTO INSTANCE = Mappers.getMapper(FieldMapperDTO.class);

    // Map from FieldSaveDTO to Field entity
    @Mapping(source = "fieldDTO.name", target = "fieldName")
    @Mapping(source = "fieldDTO.area", target = "fieldArea")
    @Mapping(source = "fieldDTO.farmId", target = "farm.id")
    Field toEntity(FieldDTO fieldDTO);

    // Map from Field entity to FieldDisplayDTO
    @Mapping(source = "field.id", target = "id")
    @Mapping(source = "field.fieldName", target = "name")
    @Mapping(source = "field.fieldArea", target = "area")
    @Mapping(source = "field.farm", target = "farm")
    FieldDisplayDTO toDTO(Field field);
}

