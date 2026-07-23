package com.K955.boilerSnippets.Security.OAuth;

import com.K955.boilerSnippets.Entity.User;
import com.K955.boilerSnippets.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Object id = oAuth2User.getAttribute("id");
        if (id == null) {
            throw new OAuth2AuthenticationException("GitHub ID not found");
        }
        String gitHubId = id.toString();

        User user = userRepository.findByGithubId(Long.parseLong(gitHubId))
                .orElseGet(() -> createUser(oAuth2User, gitHubId));

        return new CustomOAuth2User(user, oAuth2User.getAttributes());
    }

    private User createUser(OAuth2User oAuth2User, String gitHubId) {
        User user = User.builder()
                .githubId(Long.valueOf(gitHubId))
                .username(oAuth2User.getAttribute("login"))
                .avatarUrl(oAuth2User.getAttribute("avatar_url"))
                .profileUrl(oAuth2User.getAttribute("html_url"))
                .build();

        return userRepository.save(user);
    }

}
