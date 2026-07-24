package com.K955.boilerSnippets.Mapper;

import com.K955.boilerSnippets.DTOs.Snippet.SnippetResponse;
import com.K955.boilerSnippets.Entity.Snippet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SnippetMapper {

    SnippetResponse toSnippetResponse(Snippet snippet);

}
