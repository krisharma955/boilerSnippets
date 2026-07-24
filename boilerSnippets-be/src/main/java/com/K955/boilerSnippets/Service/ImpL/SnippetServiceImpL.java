package com.K955.boilerSnippets.Service.ImpL;

import com.K955.boilerSnippets.DTOs.Snippet.SnippetRequest;
import com.K955.boilerSnippets.DTOs.Snippet.SnippetResponse;
import com.K955.boilerSnippets.DTOs.Snippet.UpdateSnippetRequest;
import com.K955.boilerSnippets.Enum.Snippet.Framework;
import com.K955.boilerSnippets.Enum.Snippet.Language;
import com.K955.boilerSnippets.Enum.Snippet.SnippetType;
import com.K955.boilerSnippets.Mapper.SnippetMapper;
import com.K955.boilerSnippets.Mapper.UserMapper;
import com.K955.boilerSnippets.Repository.SnippetRepository;
import com.K955.boilerSnippets.Service.SnippetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SnippetServiceImpL implements SnippetService {

    private final SnippetRepository snippetRepository;
    private final SnippetMapper snippetMapper;
    private final UserMapper userMapper;

    @Override
    public SnippetResponse createSnippet(SnippetRequest request) {
        return null;
    }

    @Override
    public SnippetResponse getSnippetById(Long snippetId) {
        return null;
    }

    @Override
    public Page<SnippetResponse> getAllSnippets(Language language, Framework framework, SnippetType type, String title, Pageable pageable) {
        return null;
    }

    @Override
    public SnippetResponse updateSnippet(Long snippetId, UpdateSnippetRequest request) {
        return null;
    }

    @Override
    public void deleteSnippet(Long snippetId) {

    }

}
