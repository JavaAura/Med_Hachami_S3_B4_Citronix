package com.citronix.mapper;

import com.citronix.dto.req.FieldDTO;
import com.citronix.dto.res.FieldDisplayDTO;
import com.citronix.model.Farm;
import com.citronix.model.Field;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-20T16:31:52+0000",
    comments = "version: 1.6.2, compiler: javac, environment: Java 18.0.2-ea (Private Build)"
)
public class FieldMapperDTOImpl implements FieldMapperDTO {

    private final FarmMapperDTO farmMapperDTO = FarmMapperDTO.INSTANCE;

    @Override
    public Field toEntity(FieldDTO fieldDTO) {
        if ( fieldDTO == null ) {
            return null;
        }

        Field field = new Field();

        field.setFarm( fieldDTOToFarm( fieldDTO ) );
        field.setFieldName( fieldDTO.getName() );
        field.setFieldArea( fieldDTO.getArea() );
        field.setId( fieldDTO.getId() );

        return field;
    }

    @Override
    public FieldDisplayDTO toDTO(Field field) {
        if ( field == null ) {
            return null;
        }

        FieldDisplayDTO fieldDisplayDTO = new FieldDisplayDTO();

        fieldDisplayDTO.setId( field.getId() );
        fieldDisplayDTO.setName( field.getFieldName() );
        fieldDisplayDTO.setArea( field.getFieldArea() );
        fieldDisplayDTO.setFarm( farmMapperDTO.toDTO( field.getFarm() ) );

        return fieldDisplayDTO;
    }

    protected Farm fieldDTOToFarm(FieldDTO fieldDTO) {
        if ( fieldDTO == null ) {
            return null;
        }

        Farm farm = new Farm();

        farm.setId( fieldDTO.getFarmId() );

        return farm;
    }
}
