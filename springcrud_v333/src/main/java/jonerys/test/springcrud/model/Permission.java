package jonerys.test.springcrud.model;

public enum Permission {
    ADMIN_PERMISSIONS("admin_permissions"),
    USER_PERMISSIONS("user_permissions");

    private final String permission;

    Permission(String permission) { this.permission = permission; }

    public String getPermission() { return permission; }
}
