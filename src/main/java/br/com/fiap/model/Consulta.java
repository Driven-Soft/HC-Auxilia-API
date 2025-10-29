package br.com.fiap.model;

import java.time.LocalDateTime;

public class Consulta {
    private Long id;
    private Paciente paciente;
    private LocalDateTime dataHora;
    private String motivo;

    public Consulta() {
    }

    public Consulta(Long id, Paciente paciente, LocalDateTime dataHora, String motivo) {
        this.id = id;
        this.paciente = paciente;
        this.dataHora = dataHora;
        this.motivo = motivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", dataHora=" + dataHora +
                ", motivo='" + motivo + '\'' +
                '}';
    }
}

