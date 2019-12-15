package br.edu.iftm.loginapp.entities;

public class UserLoginReturn {

    private String email;
    private String token;

    public UserLoginReturn() {}

    public UserLoginReturn(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
