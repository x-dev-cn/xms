package cn.xdev.auth.security.enums;

/**
 * Created by felix on 2017-06-12-0012.
 */
public enum LoginType {
    USER("User"), ADMIN("Admin");

    private String type;

    LoginType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }
}
