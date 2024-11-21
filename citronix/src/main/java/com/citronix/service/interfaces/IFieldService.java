package com.citronix.service.interfaces;

import com.citronix.dto.req.FieldDTO;
import com.citronix.dto.req.FarmDTO;
import com.citronix.dto.res.FieldDisplayDTO;
import com.citronix.model.Farm;
import com.citronix.model.Field;

import java.util.List;

public interface IFieldService {
    FieldDisplayDTO saveField(FieldDTO fieldDTO);
    List<FieldDisplayDTO> findFieldsByFarmId(Long farmId) ;
    FieldDisplayDTO getFieldById(Long fieldId);
    List<FieldDisplayDTO> findAllFields();
    void deleteFieldById(Long id);
    FieldDisplayDTO updateField(Long fieldId, FieldDTO fieldDTO);


}
