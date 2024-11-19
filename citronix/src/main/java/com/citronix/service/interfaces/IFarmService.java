package com.citronix.service.interfaces;

import com.citronix.dto.req.FarmDTO;
import com.citronix.model.Farm;

import java.util.List;

public interface IFarmService {
    Farm saveFarm(FarmDTO farmDTO);
    public List<FarmDTO> findFarmByNameAndAddress(String farmName, String farmAddress) ;
    public FarmDTO updateFarm(FarmDTO farmDTO) ;
}
