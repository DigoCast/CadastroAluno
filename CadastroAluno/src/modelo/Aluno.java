package modelo;

import java.time.LocalDate;

public class Aluno {
    private int alu_cpf;
    private String alu_nome;
    private LocalDate alu_nasc;
    private float alu_peso;
    private float alu_altura;

    public Aluno(int alu_cpf, String alu_nome, LocalDate alu_nasc, float alu_peso, float alu_altura) {
        this.alu_cpf = alu_cpf;
        this.alu_nome = alu_nome;
        this.alu_nasc = alu_nasc;
        this.alu_peso = alu_peso;
        this.alu_altura = alu_altura;
    }

    public int getAlu_cpf() {
        return alu_cpf;
    }

    public void setAlu_cpf(int alu_cpf) {
        this.alu_cpf = alu_cpf;
    }

    public String getAlu_nome() {
        return alu_nome;
    }

    public void setAlu_nome(String alu_nome) {
        this.alu_nome = alu_nome;
    }

    public LocalDate getAlu_nasc() {
        return alu_nasc;
    }

    public void setAlu_nasc(LocalDate alu_nasc) {
        this.alu_nasc = alu_nasc;
    }

    public float getAlu_peso() {
        return alu_peso;
    }

    public void setAlu_peso(float alu_peso) {
        this.alu_peso = alu_peso;
    }

    public float getAlu_altura() {
        return alu_altura;
    }

    public void setAlu_altura(float alu_altura) {
        this.alu_altura = alu_altura;
    }
}
