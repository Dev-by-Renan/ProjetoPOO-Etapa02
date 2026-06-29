abstract class Pessoa {
    private String nome;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
    if (nome != null && !nome.equals("")) {
        this.nome = nome;
    }
    }
        public Pessoa(String nome) {
    setNome(nome);
}
    public abstract String exibirResumo();
}
