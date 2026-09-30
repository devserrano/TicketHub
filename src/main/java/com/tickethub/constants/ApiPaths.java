package com.tickethub.constants;

/** Rutas base de la API, para no repetir strings en los controladores. */
public final class ApiPaths {

    public static final String API_V1 = "/api/v1";

    public static final String AUTH = API_V1 + "/auth";
    public static final String USERS = API_V1 + "/users";
    public static final String CATEGORIES = API_V1 + "/categories";
    public static final String TICKETS = API_V1 + "/tickets";
    public static final String ATTACHMENTS = API_V1 + "/attachments";
    public static final String REPORTS = API_V1 + "/reports";
    public static final String DASHBOARD = API_V1 + "/dashboard";

    private ApiPaths() {
    }
}
