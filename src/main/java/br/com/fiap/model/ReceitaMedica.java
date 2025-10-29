package br.com.fiap.model;

public class ReceitaMedica {
    private Long id;
    private Paciente paciente;
    private String medicamentos;
    private int qtdDias;
    private String observacao;

    public ReceitaMedica() {
    }

    public ReceitaMedica(Long id, Paciente paciente, String medicamentos, int qtdDias, String observacao) {
        this.id = id;
        this.paciente = paciente;
        this.medicamentos = medicamentos;
        this.qtdDias = qtdDias;
        this.observacao = observacao;
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

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public int getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(int qtdDias) {
        this.qtdDias = qtdDias;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "ReceitaMedica{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", medicamentos='" + medicamentos + '\'' +
                ", qtdDias=" + qtdDias +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}