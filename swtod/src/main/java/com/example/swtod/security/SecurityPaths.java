package com.example.swtod.security;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public final class SecurityPaths {
    public static final String[] PERMITTED_ALL_PATHS = new String[]{
            "/api/login",
            "/api/reset-password"};

    public static final List<String> USER_PATHS = List.of(
            "/api/change-password"
    );

    public static final List<String> ADMIN_PATHS = List.of(
            "/api/create-user"
    );
}
