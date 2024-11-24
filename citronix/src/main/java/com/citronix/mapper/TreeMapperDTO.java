package com.citronix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.citronix.dto.res.TreeResponseDTO;
import com.citronix.model.Tree;

@Mapper
public interface TreeMapperDTO {
    TreeMapperDTO INSTANCE = Mappers.getMapper(TreeMapperDTO.class);


  
    @Mapping(source = "field.id", target = "fieldId")
    @Mapping(source = "field.fieldName", target = "fieldName") 
    @Mapping(source = "level", target = "level")
    @Mapping(expression = "java(tree.calculateAge())", target = "age") 
    TreeResponseDTO toDTO(Tree tree);

}
