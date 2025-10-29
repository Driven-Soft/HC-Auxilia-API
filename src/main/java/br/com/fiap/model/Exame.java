package br.com.fiap.model;

import java.time.LocalDateTime;

public class Exame {
    private Long idExame;
    private String nomeExame;
    private LocalDateTime dataHoraExame;
    private String resultado;
    private String status;

    private Paciente paciente;
    private Medico medico;
    private Endereco endereco;

    public Exame() {};

    public Exame(Long idExame, String nomeExame, LocalDateTime dataHoraExame, String resultado, String status, Paciente paciente, Medico medico, Endereco endereco) {
        this.idExame = idExame;
        this.nomeExame = nomeExame;
        this.dataHoraExame = dataHoraExame;
        this.resultado = resultado;
        this.status = status;
        this.paciente = paciente;
        this.medico = medico;
        this.endereco = endereco;
    }

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

    public LocalDateTime getDataHoraExame() {
        return dataHoraExame;
    }

    public void setDataHoraExame(LocalDateTime dataHoraExame) {
        this.dataHoraExame = dataHoraExame;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Exame{" +
                "idExame=" + idExame +
                ", nomeExame='" + nomeExame + '\'' +
                ", dataHoraExame=" + dataHoraExame +
                ", resultado='" + resultado + '\'' +
                ", status='" + status + '\'' +
                ", paciente=" + paciente +
                ", medico=" + medico +
                ", endereco=" + endereco +
                '}';
    }
}