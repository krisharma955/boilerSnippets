package com.K955.boilerSnippets.DTOs.User;

import com.K955.boilerSnippets.Enum.User.Role;

public record UserProfileResponse(
        Long id,
        String username,
        Role role
) {
}
