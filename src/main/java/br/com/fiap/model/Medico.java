package br.com.fiap.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Medico {
    private Long idMedico;
    private String crm;
    private String nome;
    private String telefone;
    private String email;
    private LocalDate nascimento;
    private Endereco endereco;
    private List<Especialidade> especialidades;

    public Medico() {
        this.especialidades = new ArrayList<>();
    }

    public Medico(Long idMedico, String crm, String nome, String telefone, String email,
                  LocalDate nascimento, Endereco endereco) {
        this.idMedico = idMedico;
        this.crm = crm;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.nascimento = nascimento;
        this.endereco = endereco;
        this.especialidades = new ArrayList<>();
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
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

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    // --- Métodos utilitários ---
    public void adicionarEspecialidade(Especialidade especialidade) {
        if (especialidade != null && !especialidades.contains(especialidade)) {
            especialidades.add(especialidade);
        }
    }

    public void removerEspecialidade(Especialidade especialidade) {
        especialidades.remove(especialidade);
    }

    @Override
    public String toString() {
        return "Medico{" +
                "idMedico=" + idMedico +
                ", crm='" + crm + '\'' +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", nascimento=" + nascimento +
                ", endereco=" + endereco +
                ", especialidades=" + especialidades +
                '}';
    }
}
