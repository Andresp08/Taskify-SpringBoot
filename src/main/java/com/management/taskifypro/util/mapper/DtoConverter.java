package com.management.taskifypro.util.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

public class DtoConverter<Entity, DTO> {

    @Autowired
    private ModelMapper modelMapper;
    private Class<Entity> entityClass;
    private Class<DTO> dtoClass;

    public DtoConverter(Class<Entity> entityClass, Class<DTO> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public DTO convertEntityToDTO(Entity entity) {
        if(entity != null) {
            return this.modelMapper.map(entity, this.dtoClass);
        } else {
            return null;
        }
    }

    public Entity convertDTOToEntity(DTO dto) {
        if(dto != null) {
            return this.modelMapper.map(dto, this.entityClass);
        } else {
            return null;
        }
    }
}
