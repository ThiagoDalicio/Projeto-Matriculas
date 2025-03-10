package model;

public class Dados {
    private Long id;
    private Long matriculaId;
    private String nome; // ✅ Novo atributo para armazenar o nome do aluno
    private String endereco;
    private String cep;
    private String estado;
    private String cidade;
    private int idade;
    private String genero;

    public Dados(Long id, Long matriculaId, String nome, String endereco, String cep, String estado, String cidade, int idade, String genero) {
        this.id = id;
        this.matriculaId = matriculaId;
        this.nome = nome; // ✅ Atribuindo o nome
        this.endereco = endereco;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.idade = idade;
        this.genero = genero;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getMatriculaId() { return matriculaId; }
    public void setMatriculaId(Long matriculaId) { this.matriculaId = matriculaId; }

    public String getNome() { return nome; } // ✅ Novo getter
    public void setNome(String nome) { this.nome = nome; } // ✅ Novo setter

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}

