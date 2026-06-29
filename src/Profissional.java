import java.util.ArrayList;
import java.util.List;

public class Profissional implements Exibivel {


//Fazendo o encapsulamento:

private String nome;
private String especialidade;
private String registroProfissional;
private double valorConsulta;

private List<String> diasDisponiveis;

// Sequencia de sobrecarga com diferentes paramentros:

public Profissional(String nome, String especialidade) {
    this.nome = nome;
    this.especialidade = especialidade;
    this.registroProfissional = "";
    this.valorConsulta = 0;
    this.diasDisponiveis = new ArrayList<>();
}

public Profissional(String nome, String especialidade,
                    String registroProfissional,
                    double valorConsulta) {

    this.nome = nome;
    this.especialidade = especialidade;
    this.registroProfissional = registroProfissional;
    this.valorConsulta = valorConsulta;
    this.diasDisponiveis = new ArrayList<>();
}

public Profissional(String nome, String especialidade,
                    String registroProfissional,
                    double valorConsulta,
                    List<String> dias) {

    this.nome = nome;
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


protected String gerarIdentificacao() {
    return nome + " - " + especialidade;
}

@Override
public String exibirResumo() {

    String dias = String.join(", ", diasDisponiveis);

    return "Nome: " + nome
            + " | Espec: " + especialidade
            + " | Reg: " + registroProfissional
            + " | Valor: R$" + valorConsulta
            + " | Dias: " + dias;
}

public String getNome() {
    return nome;
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

public void setNome(String nome) {

    if (nome == null || nome.isBlank()) {
        throw new IllegalArgumentException(
                "Nome não pode ser vazio");
    }

    this.nome = nome;
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

// Operações de List 

public void adicionarDiaDisponivel(String dia) {
    diasDisponiveis.add(dia);
}

public String obterDia(int indice) {
    return diasDisponiveis.get(indice);
}

}
