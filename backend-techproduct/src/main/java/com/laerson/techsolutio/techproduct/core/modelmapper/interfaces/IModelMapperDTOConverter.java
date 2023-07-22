package com.laerson.techsolutio.techproduct.core.modelmapper.interfaces;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IModelMapperDTOConverter {

    public <S, T> T convertToModelDTO(S entity, Class<T> dtoClass);
    public <S, T> List<T> convertToModelListDTO(List<S> entities, Class<T> dtoClass);
    public <S, T> T convertToEntity(S dto, Class<T> entity);
    public <S, T> void configureModelMapperForUpdate(S source, T target);
    public <S, T> Page<T> convertToModelPageDTO(Page<S> entities, Class<T> dtoClass);

}
