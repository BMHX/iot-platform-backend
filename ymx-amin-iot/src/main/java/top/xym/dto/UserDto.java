package top.xym.dto;

public class UserDto {
    private String username;
    private String password;
    // Additional fields like identity and permissionId can be added here

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
