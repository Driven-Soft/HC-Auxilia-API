package br.com.fiap.model;

import java.time.LocalDateTime;

public class Exame {
    private long idExame;
    private String nomeExame;
    private LocalDateTime dataHoraExame;
    private String nomeDoutor;
    private String logradouro;
    private String numero;
    private String estado;
    private String status;

    public Exame () {}

    public Exame(long idExame, String nomeExame, LocalDateTime dataHoraExame, String nomeDoutor, String logradouro, String numero, String estado, String status) {
        this.idExame = idExame;
        this.nomeExame = nomeExame;
        this.dataHoraExame = dataHoraExame;
        this.nomeDoutor = nomeDoutor;
        this.logradouro = logradouro;
        this.numero = numero;
        this.estado = estado;
        this.status = status;
    }

    public long getIdExame() {
        return idExame;
    }

    public void setIdExame(long idExame) {
        this.idExame = idExame;
    }

    public String getNomeExame() {
        return nomeExame;
    }

    public void setNomeExame(String nomeExame) {
        this.nomeExame = nomeExame;
    }

    public LocalDateTime getDataHoraExame() {
        return dataHoraExame;
    }

    public void setDataHoraExame(LocalDateTime dataHoraExame) {
        this.dataHoraExame = dataHoraExame;
    }

    public String getNomeDoutor() {
        return nomeDoutor;
    }

    public void setNomeDoutor(String nomeDoutor) {
        this.nomeDoutor = nomeDoutor;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Exame{" +
                "idExame=" + idExame +
                ", nomeExame='" + nomeExame + '\'' +
                ", dataHoraExame=" + dataHoraExame +
                ", nomeDoutor='" + nomeDoutor + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", estado='" + estado + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
