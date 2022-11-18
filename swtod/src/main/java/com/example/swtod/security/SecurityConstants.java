package com.example.swtod.security;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class SecurityConstants {

    public static final String ADMIN_AUTH = "ADMIN";
    public static final String USER_AUTH = "USER";
    public static final String[] PERMITTED_ALL_PATHS = new String[]{
            "/api/user/login",
            "/api/user/reset-password",
    };

    public static final String[] USER_PATHS = new String[]{
            "/api/user/is-user",
            "/api/user/change-password",
            "/api/user/{id}/update",
            "/api/user/all",
            "/api/user",
    };

    public static final String[] ADMIN_PATHS = new String[]{
            "/api/admin/is-admin",
            "/api/admin/create",
            "/api/admin/{id}/activate-account",
            "/api/admin/{id}/deactivate-account",
            "/api/admin/{id}/update",
            "/api/plan-year-subject/upload-file",
            "/api/plan-year-subject/delete-all",
            "/api/plan-year-subject/all",
            "/api/plan-year-subject/add",
            "/api/plan-year-subject/{id}/update",
            "/api/plan-year-subject/{id}/delete",
            "api/plan-year-subject-user/assign-groups",
            "api/plan-year-subject-user/",
            "api/plan-year-subject-user/change-assignment",
            "api/plan-year-subject-user/delete-assignment"
    };
}
