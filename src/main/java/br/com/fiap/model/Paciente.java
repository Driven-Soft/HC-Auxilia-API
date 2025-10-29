package br.com.fiap.model;

import java.time.LocalDate;

public class Paciente {
    private Long idPaciente;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private String sexo;
    private LocalDate nascimento;
    private Endereco endereco;

    public Paciente() {
    }

    public Paciente(Long idPaciente, String cpf, String nome, String telefone, String email, String sexo, LocalDate nascimento, Endereco endereco) {
        this.idPaciente = idPaciente;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.endereco = endereco;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente=" + idPaciente +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", sexo='" + sexo + '\'' +
                ", nascimento=" + nascimento +
                ", endereco=" + endereco +
                '}';
    }
}
