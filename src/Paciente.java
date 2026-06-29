public class Paciente extends Pessoa {
    private String cpf;
    private int idade;
    private String telefone;
    private String convenioNome;
    private boolean ativo;

    public Paciente(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
        this.idade = 0;
        this.telefone = "";
        this.convenioNome = "";
        this.ativo = true;
    }

    public Paciente(String nome, String cpf, int idade, String telefone) {
        super(nome);
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
        this.convenioNome = "";
        this.ativo = true;
    }

    // construtor com todos os dados
    public Paciente(String nome, String cpf, int idade, String telefone, String convenioNome) {
        super(nome);
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
        this.convenioNome = convenioNome;
        this.ativo = true;
    }
    
    //getters e setters para todos os atributos privados de Paciente   
public String getCpf() {
    return cpf;
}

public void setCpf(String cpf) {
    if (cpf != null && !cpf.equals("")) {
        this.cpf = cpf;
    }
}
    
public int getIdade() {
    return idade;
}

public void setIdade(int idade) {
    this.idade = idade;
}

public String getTelefone() {
    return telefone;
}

public void setTelefone(String telefone) {
    this.telefone = telefone;
}

public String getConvenioNome() {
    return convenioNome;
}

public void setConvenioNome(String convenioNome) {
    this.convenioNome = convenioNome;
}

public boolean isAtivo() {
    return ativo;
}

public void setAtivo(boolean ativo) {
    this.ativo = ativo;
}
    // atualiza so idade e telefone
    public void complementar(int idade, String telefone) {
        setIdade(idade);
        setTelefone(telefone);
    }

    // atualiza tudo incluindo convenio
    public void complementar(int idade, String telefone, String convenioNome) {
        setIdade(idade);
        setTelefone(telefone);
        setConvenioNome(convenioNome);
    }

    public void desativar() {
        this.ativo = false;
    }

    @Override
    public String exibirResumo() {
        String status = "Sim";
        if (!ativo) {
            status = "Nao";
        }
        return "Nome: " + getNome() + " | CPF: " + getCpf() + " | Idade: " + getIdade()
                + " | Tel: " + getTelefone() + " | Convenio: " + getConvenioNome()
                + " | Ativo: " + status;
    }
}
