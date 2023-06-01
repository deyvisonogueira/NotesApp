package br.edu.ifsuldeminas.mch.notesapp;

import io.realm.RealmObject;


public class Note extends RealmObject {
    String titulo;
    String descricao;
    long criarHora;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getCriarHora() {
        return criarHora;
    }

    public void setCriarHora(long criarHora) {
        this.criarHora = criarHora;
    }
}
