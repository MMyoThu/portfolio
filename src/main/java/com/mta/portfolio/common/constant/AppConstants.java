package com.mta.portfolio.common.constant;

import java.util.List;

public final class AppConstants {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String TOKEN_TYPE_BEARER = "Bearer";

    public static final List<String> CORS_ALLOWED_ORIGINS = List.of(
            "http://localhost:5173",
            "https://myothuaung.vercel.app"
    );

    private AppConstants() {
    }
}
