abstract class Pessoa {
    private String nome;
    private String cpf;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public abstract void exibirResumo();
}
