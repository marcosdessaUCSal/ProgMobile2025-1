package com.appdatabase.model;

import java.io.Serializable;

public class Contato {

    public Integer id;
    public String nome;
    public String email;
    public String tel;

    public Contato() {}

    public Contato(Integer id, String nome, String email, String tel) {
        this. id = id;
        this.nome = nome;
        this.email = email;
        this.tel = tel;
    }
}
