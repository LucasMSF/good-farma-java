package br.goodfarma.model;

import java.util.Objects;

public class Employ {
    private int id;
    private String name;
    private String cpf;
    private String telephone;
    private String login;
    private String password;

    public Employ(String name, String cpf, String telephone, String login, String password) {
        this.name = name;
        this.cpf = cpf;
        this.telephone = telephone;
        this.login = login;
        this.password = password;
    }

    public Employ(String name, String cpf, String telephone, String login, String password, int id) {
        this.name = name;
        this.cpf = cpf;
        this.telephone = telephone;
        this.login = login;
        this.password = password;
        this.id = id;
    }

    public Employ(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
