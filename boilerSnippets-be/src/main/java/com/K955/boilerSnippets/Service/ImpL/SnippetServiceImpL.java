package com.K955.boilerSnippets.Service.ImpL;

import com.K955.boilerSnippets.DTOs.Snippet.SnippetRequest;
import com.K955.boilerSnippets.DTOs.Snippet.SnippetResponse;
import com.K955.boilerSnippets.DTOs.Snippet.UpdateSnippetRequest;
import com.K955.boilerSnippets.Entity.Snippet;
import com.K955.boilerSnippets.Entity.User;
import com.K955.boilerSnippets.Enum.Snippet.Framework;
import com.K955.boilerSnippets.Enum.Snippet.Language;
import com.K955.boilerSnippets.Enum.Snippet.SnippetType;
import com.K955.boilerSnippets.Enum.User.Role;
import com.K955.boilerSnippets.Mapper.SnippetMapper;
import com.K955.boilerSnippets.Mapper.UserMapper;
import com.K955.boilerSnippets.Repository.SnippetRepository;
import com.K955.boilerSnippets.Repository.SnippetSpecification;
import com.K955.boilerSnippets.Repository.UserRepository;
import com.K955.boilerSnippets.Security.JwtUtil;
import com.K955.boilerSnippets.Service.SnippetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class SnippetServiceImpL implements SnippetService {

    private final UserRepository userRepository;
    private final SnippetRepository snippetRepository;
    private final SnippetMapper snippetMapper;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    @Override
    public SnippetResponse createSnippet(SnippetRequest request) {
        if(!checkAdmin()) throw new RuntimeException("Only Accessible to Admins");

        Long userId = jwtUtil.getCurrentUserId();
        User user = userRepository.findById(userId).orElse(null);

        Snippet snippet = Snippet.builder()
                .title(request.title())
                .description(request.description())
                .language(request.language())
                .framework(request.framework())
                .snippetType(request.snippetType())
                .frameworkVersion(request.frameworkVersion())
                .code(request.code())
                .filePath(request.filePath())
                .dependencies(request.dependencies())
                .createdBy(user)
                .build();
        Snippet saved = snippetRepository.save(snippet);

        return snippetMapper.toSnippetResponse(saved);
    }

    @Override
    public SnippetResponse getSnippetById(Long snippetId) {
        Snippet snippet = snippetRepository.findById(snippetId).orElse(null);
        return snippetMapper.toSnippetResponse(snippet);
    }

    @Override
    public Page<SnippetResponse> getAllSnippets(Language language, Framework framework, SnippetType snippetType, String keyword, Pageable pageable) {
        var spec = SnippetSpecification.searchSnippet(language, framework, snippetType, keyword);
        return snippetRepository.findAll(spec, pageable).map(snippetMapper::toSnippetResponse);
    }

    @Override
    public SnippetResponse updateSnippet(Long snippetId, UpdateSnippetRequest request) {
        if(!checkAdmin()) throw new RuntimeException("Only Accessible to Admins");
        Snippet snippet = snippetRepository.findById(snippetId).orElse(null);

        if(request.title() != null) snippet.setTitle(request.title());
        if(request.description() != null) snippet.setDescription(request.description());
        if(request.language() != null) snippet.setLanguage(request.language());
        if(request.framework() != null) snippet.setFramework(request.framework());
        if(request.snippetType() != null) snippet.setSnippetType(request.snippetType());
        if(request.frameworkVersion() != null) snippet.setFrameworkVersion(request.frameworkVersion());
        if(request.code() != null) snippet.setCode(request.code());
        if(request.filePath() != null) snippet.setFilePath(request.filePath());
        if(request.dependencies() != null) snippet.setDependencies(request.dependencies());

        Snippet saved = snippetRepository.save(snippet);

        return snippetMapper.toSnippetResponse(saved);
    }

    @Override
    public void deleteSnippet(Long snippetId) {
        if(!checkAdmin()) throw new RuntimeException("Only Accessible to Admins");
        Snippet snippet = snippetRepository.findById(snippetId).orElse(null);
        snippetRepository.delete(snippet);
    }

    /// Utility Methods

    private boolean checkAdmin() {
        Long userId = jwtUtil.getCurrentUserId();
        User user = userRepository.findById(userId).orElse(null);
        return user.getRole().equals(Role.ADMIN);
    }

}
