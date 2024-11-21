package com.citronix.service.interfaces;

import com.citronix.dto.req.FieldDTO;
import com.citronix.dto.req.FarmDTO;
import com.citronix.dto.res.FieldDisplayDTO;
import com.citronix.model.Farm;
import com.citronix.model.Field;

import java.util.List;

public interface IFieldService {
    FieldDisplayDTO saveField(FieldDTO fieldDTO);
    public List<FieldDisplayDTO> findFieldsByFarmId(Long farmId) ;


}
