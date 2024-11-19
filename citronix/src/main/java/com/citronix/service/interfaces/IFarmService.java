package com.citronix.service.interfaces;

import com.citronix.dto.req.FarmDTO;
import com.citronix.model.Farm;

import java.util.List;

public interface IFarmService {
    Farm saveFarm(FarmDTO farmDTO);
    List<FarmDTO> allFarms();
}
