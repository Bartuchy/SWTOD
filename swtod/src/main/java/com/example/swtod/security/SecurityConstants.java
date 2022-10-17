package com.example.swtod.security;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class SecurityConstants {

    public static final String ADMIN_AUTH = "ADMIN";
    public static final String USER_AUTH = "USER";
    public static final String[] PERMITTED_ALL_PATHS = new String[]{
            "/api/user/create",
            "/api/user/login",
            "/api/user/reset-password"};

    public static final String[] USER_PATHS = new String[]{
            "/api/user/change-password"
    };

    public static final String[] ADMIN_PATHS = new String[]{
            "/api/user/activate-account/{id}",
            "/api/user/deactivate-account/{id}"
    };
}
