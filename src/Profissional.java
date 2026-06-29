import java.util.ArrayList;
import java.util.List;

public class Profissional extends Pessoa implements Exibivel {

    private String especialidade;
    private String registroProfissional;
    private double valorConsulta;

    // Adicionando coleção
    private List<String> diasDisponiveis;

    // Sobrecargas:
    public Profissional(String nome, String especialidade) {

        super(nome);

        this.especialidade = especialidade;
        this.registroProfissional = "";
        this.valorConsulta = 0;
        this.diasDisponiveis = new ArrayList<>();
    }

    public Profissional(String nome, String especialidade,
                        String registroProfissional,
                        double valorConsulta) {

        super(nome);

        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
        this.diasDisponiveis = new ArrayList<>();
    }

    public Profissional(String nome, String especialidade,
                        String registroProfissional,
                        double valorConsulta,
                        List<String> dias) {

        super(nome);

        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
        this.diasDisponiveis = new ArrayList<>(dias);
    }

    public void atualizar(String registro, double valor) {
        setRegistroProfissional(registro);
        setValorConsulta(valor);
    }
    
    public void atualizar(String registro, double valor,
                          List<String> dias) {

        setRegistroProfissional(registro);
        setValorConsulta(valor);
        this.diasDisponiveis = new ArrayList<>(dias);
    }

    public boolean atendeNoDia(String dia) {
        return diasDisponiveis.contains(dia);
    }

    public static boolean especialidadeValida(String esp) {

        return esp.equals("clinica geral")
                || esp.equals("fisioterapia")
                || esp.equals("psicologia")
                || esp.equals("nutricao");
    }

    // método protegido
    protected String gerarIdentificacao() {
        return getNome() + " - " + especialidade;
    }

    @Override
    public String exibirResumo() {

        String dias = String.join(", ", diasDisponiveis);

        return "Nome: " + getNome()
                + " | Espec: " + especialidade
                + " | Reg: " + registroProfissional
                + " | Valor: R$" + valorConsulta
                + " | Dias: " + dias;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public List<String> getDiasDisponiveis() {
        return diasDisponiveis;
    }

    public void setEspecialidade(String especialidade) {

        if (!especialidadeValida(especialidade)) {
            throw new IllegalArgumentException(
                    "Especialidade inválida");
        }

        this.especialidade = especialidade;
    }

    public void setRegistroProfissional(String registroProfissional) {

        if (registroProfissional == null
                || registroProfissional.isBlank()) {

            throw new IllegalArgumentException(
                    "Registro profissional inválido");
        }

        this.registroProfissional = registroProfissional;
    }

    public void setValorConsulta(double valorConsulta) {

        if (valorConsulta < 0) {
            throw new IllegalArgumentException(
                    "Valor da consulta não pode ser negativo");
        }

        this.valorConsulta = valorConsulta;
    }

    public void setDiasDisponiveis(List<String> diasDisponiveis) {
        this.diasDisponiveis = new ArrayList<>(diasDisponiveis);
    }


    public void adicionarDiaDisponivel(String dia) {
        diasDisponiveis.add(dia);
    }

    public String obterDia(int indice) {
        return diasDisponiveis.get(indice);
    }
}


/*
    Diagrama completo da classe:
- especialidade : String
- registroProfissional : String
- valorConsulta : double
- diasDisponiveis : List<String>

+ Profissional(nome, especialidade)
+ Profissional(nome, especialidade, registro, valor)
+ Profissional(nome, especialidade, registro, valor, dias)

+ atualizar(registro, valor)
+ atualizar(registro, valor, dias)

+ atendeNoDia(dia) : boolean
+ exibirResumo() : String

# gerarIdentificacao() : String

    (Relacionamentos)

Pessoa -----------|> Profissional
                     (HERANÇA)

Profissional ......|> Exibivel
                     (IMPLEMENTA INTERFACE)

Profissional ------> List<String>
                     (AGREGAÇÃO dos dias disponíveis) */
