package com.ascodev.newshubbackend.mapper;

import com.ascodev.newshubbackend.dto.NewsDTO;
import com.ascodev.newshubbackend.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    @Mapping(source = "id", target = "id")
    News mapToNews(NewsDTO newsDTO);

    @Mapping(source = "id", target = "id")
    NewsDTO mapToNewsDTO(News news);
}
