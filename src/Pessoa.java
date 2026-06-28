abstract class Pessoa {
    private String nome;
    private String cpf;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
    if (nome != null && !nome.equals("")) {
        this.nome = nome;
    }
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
    if (cpf != null && !cpf.equals("")) {
        this.cpf = cpf;
    }
    }
        public Pessoa(String nome, String cpf) {
    setNome(nome);
    setCpf(cpf);
}
    public abstract String exibirResumo();
}
