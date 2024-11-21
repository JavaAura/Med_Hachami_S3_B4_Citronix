package com.citronix.service.interfaces;

import com.citronix.dto.req.FarmDTO;
import com.citronix.model.Farm;

import java.util.List;

public interface IFarmService {
    Farm saveFarm(FarmDTO farmDTO);
    public List<FarmDTO>  findFarmByNameAndAddress(String farmName, String farmAddress , int page, int size);
    public FarmDTO updateFarm(FarmDTO farmDTO) ;
    public FarmDTO getFarmById(Long id) ;
    public void deleteFarmById(Long id);

}
