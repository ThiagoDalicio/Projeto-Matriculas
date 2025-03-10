package model;

import java.time.LocalDate;

public class Matricula {
    private Long id;
    private String matricula;
    private String nomeCompleto;
    private LocalDate dataIngresso;

    public Matricula() {}

    public Matricula(Long id, String matricula, String nomeCompleto, LocalDate dataIngresso) {
        this.id = id;
        this.matricula = matricula;
        this.nomeCompleto = nomeCompleto;
        this.dataIngresso = dataIngresso;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public LocalDate getDataIngresso() { return dataIngresso; }
    public void setDataIngresso(LocalDate dataIngresso) { this.dataIngresso = dataIngresso; }
}