package br.com.fiap.model;

import java.util.Date;

public class Feedback {
    private long idFeedback;
    private String nome;
    private String email;
    private int nivelSatisfacao;
    private String sugestao;
    private Date dataEnvio;

    public Feedback() {}

    public Feedback(long idFeedback, String nome, String email, int nivelSatisfacao, String sugestao, Date dataEnvio) {
        this.idFeedback = idFeedback;
        this.nome = nome;
        this.email = email;
        this.nivelSatisfacao = nivelSatisfacao;
        this.sugestao = sugestao;
        this.dataEnvio = dataEnvio;
    }

    public long getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(long idFeedback) {
        this.idFeedback = idFeedback;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNivelSatisfacao() {
        return nivelSatisfacao;
    }

    public void setNivelSatisfacao(int nivelSatisfacao) {
        this.nivelSatisfacao = nivelSatisfacao;
    }

    public String getSugestao() {
        return sugestao;
    }

    public void setSugestao(String sugestao) {
        this.sugestao = sugestao;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "idFeedback=" + idFeedback +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", nivelSatisfacao=" + nivelSatisfacao +
                ", sugestao='" + sugestao + '\'' +
                ", dataEnvio=" + dataEnvio +
                '}';
    }
}
