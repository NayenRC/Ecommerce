package Ecommerce_FullStackcom.example.Ecommerce.dto;

import Ecommerce_FullStackcom.example.Ecommerce.model.Usuario;

public class LoginResponse {

    private String token;
    private Usuario usuario;

    public LoginResponse(String token, Usuario usuario) {
        this.token = token;
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
