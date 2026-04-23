package com.m1.biblioteca.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Document(collection = "emprestimos")
public class Emprestimo {

    @Id
    private String id;
    
    private String cpfLeitor;
    
    private String identificadorLivro;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataSaida;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDevolucaoPrevisao;

    private String observacoes;

    private boolean ativo;

    public Emprestimo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpfLeitor() {
        return cpfLeitor;
    }

    public void setCpfLeitor(String cpfLeitor) {
        this.cpfLeitor = cpfLeitor;
    }

    public String getIdentificadorLivro() {
        return identificadorLivro;
    }

    public void setIdentificadorLivro(String identificadorLivro) {
        this.identificadorLivro = identificadorLivro;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public LocalDate getDataDevolucaoPrevisao() {
        return dataDevolucaoPrevisao;
    }

    public void setDataDevolucaoPrevisao(LocalDate dataDevolucaoPrevisao) {
        this.dataDevolucaoPrevisao = dataDevolucaoPrevisao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}