package br.com.fiap.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.LocalDate;

public class InscritoNotificacao {
    private Long idInscritoNotificacao;
    private String nome;
    private String telefone;
    @JsonAlias({"sms", "recebeSms"})
    private String recebeSms;       // 'S' ou 'N'
    @JsonAlias({"whatsapp", "recebeWhatsapp"})
    private String recebeWhatsapp;  // 'S' ou 'N'
    private LocalDate dataInscricao;
    private String status;          // 'A' ou 'I

    public InscritoNotificacao () {};

    public InscritoNotificacao(Long idInscritoNotificacao, String nome, String telefone, String recebeSms, String recebeWhatsapp, LocalDate dataInscricao, String status) {
        this.idInscritoNotificacao = idInscritoNotificacao;
        this.nome = nome;
        this.telefone = telefone;
        this.recebeSms = recebeSms;
        this.recebeWhatsapp = recebeWhatsapp;
        this.dataInscricao = dataInscricao;
        this.status = status;
    }

    public Long getIdInscritoNotificacao() {
        return idInscritoNotificacao;
    }

    public void setIdInscritoNotificacao(Long idInscritoNotificacao) {
        this.idInscritoNotificacao = idInscritoNotificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRecebeSms() {
        return recebeSms;
    }

    public void setRecebeSms(String recebeSms) {
        this.recebeSms = recebeSms;
    }

    public String getRecebeWhatsapp() {
        return recebeWhatsapp;
    }

    public void setRecebeWhatsapp(String recebeWhatsapp) {
        this.recebeWhatsapp = recebeWhatsapp;
    }

    public LocalDate getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDate dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "InscritoNotificacao{" +
                "idInscritoNotificacao=" + idInscritoNotificacao +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", recebeSms='" + recebeSms + '\'' +
                ", recebeWhatsapp='" + recebeWhatsapp + '\'' +
                ", dataInscricao=" + dataInscricao +
                ", status='" + status + '\'' +
                '}';
    }
}
