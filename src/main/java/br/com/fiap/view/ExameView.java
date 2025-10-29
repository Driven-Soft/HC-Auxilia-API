package br.com.fiap.view;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ExameView {
    private Long idExame;
    private String nomeExame;
    private String dataHoraExame;
    private String nomeDoutor;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String status;

    public ExameView() {}

    public Long getIdExame() {
        return idExame;
    }

    public void setIdExame(Long idExame) {
        this.idExame = idExame;
    }

    public String getNomeExame() {
        return nomeExame;
    }

    public void setNomeExame(String nomeExame) {
        this.nomeExame = nomeExame;
    }

    public String getDataHoraExame() {
        return dataHoraExame;
    }

    public void setDataHoraExame(String dataHoraExame) {
        this.dataHoraExame = dataHoraExame;
    }

    public String getNomeDoutor() {
        return nomeDoutor;
    }

    public void setNomeDoutor(String nomeDoutor) {
        this.nomeDoutor = nomeDoutor;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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
}
