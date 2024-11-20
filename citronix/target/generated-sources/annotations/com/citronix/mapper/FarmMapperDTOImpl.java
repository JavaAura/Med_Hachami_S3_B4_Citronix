package com.citronix.mapper;

import com.citronix.dto.req.FarmDTO;
import com.citronix.model.Farm;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-20T16:31:52+0000",
    comments = "version: 1.6.2, compiler: javac, environment: Java 18.0.2-ea (Private Build)"
)
public class FarmMapperDTOImpl implements FarmMapperDTO {

    @Override
    public Farm toEntity(FarmDTO farmDTO) {
        if ( farmDTO == null ) {
            return null;
        }

        Farm farm = new Farm();

        farm.setId( farmDTO.getId() );
        farm.setFarmName( farmDTO.getName() );
        farm.setFarmAddress( farmDTO.getAddress() );
        farm.setFarmSurface( farmDTO.getSurface() );

        return farm;
    }

    @Override
    public FarmDTO toDTO(Farm farm) {
        if ( farm == null ) {
            return null;
        }

        FarmDTO farmDTO = new FarmDTO();

        if ( farm.getId() != null ) {
            farmDTO.setId( farm.getId() );
        }
        farmDTO.setName( farm.getFarmName() );
        farmDTO.setAddress( farm.getFarmAddress() );
        if ( farm.getFarmSurface() != null ) {
            farmDTO.setSurface( farm.getFarmSurface() );
        }

        return farmDTO;
    }
}
