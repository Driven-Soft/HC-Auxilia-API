package br.com.fiap.model;

public class Cuidador {
    private Long id;
    private String nome;
    private String cpf;
    private String contato;
    private Paciente paciente;

    public Cuidador() {
    }

    public Cuidador(Long id, String nome, String cpf, String contato, Paciente paciente) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.contato = contato;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Cuidador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", contato='" + contato + '\'' +
                ", paciente=" + paciente +
                '}';
    }
}
