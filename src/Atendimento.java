// COMPOSIÇÃO: Atendimento contém Prontuario.
// O Prontuario é criado dentro do Atendimento e não existe sem ele.
public class Atendimento implements Exportavel {

    private Consulta consulta;
    private Prontuario prontuario;
    private boolean concluido;

    // SOBRECARGA: registro básico - só observações
    public Atendimento(Consulta consulta, String observacoes) {
        this.consulta = consulta;
        this.prontuario = new Prontuario(observacoes);
        this.concluido = false;
    }

    // SOBRECARGA: registro com diagnóstico
    public Atendimento(Consulta consulta, String observacoes, String diagnostico) {
        this.consulta = consulta;
        this.prontuario = new Prontuario(observacoes, diagnostico);
        this.concluido = false;
    }

    // SOBRECARGA: registro completo com procedimentos já definidos
    public Atendimento(Consulta consulta, String observacoes, String diagnostico,
                       String[] procedimentos, int totalProcedimentos) {
        this.consulta = consulta;
        this.prontuario = new Prontuario(observacoes, diagnostico);
        this.prontuario.adicionarProcedimento(procedimentos, totalProcedimentos);
        this.concluido = false;
    }

    public void concluir() {
        this.concluido = true;
    }

    // SOBRECARGA: adiciona um procedimento por vez
    public void adicionarProcedimento(String procedimento) {
        prontuario.adicionarProcedimento(procedimento);
    }

    // SOBRECARGA: adiciona vários de uma vez
    public void adicionarProcedimento(String[] procs, int quantidade) {
        prontuario.adicionarProcedimento(procs, quantidade);
    }

    public void exibirResumo() {
        System.out.println("=== Atendimento ===");
        if (consulta != null) {
            System.out.println("Paciente: " + consulta.getPaciente().getNome());
            System.out.println("Profissional: " + consulta.getProfissional().getNome());
            System.out.println("Data consulta: " + consulta.getData());
        }
        System.out.println("Status: " + (concluido ? "Concluido" : "Em aberto"));
        System.out.println("Prontuario:");
        prontuario.exibir();
    }

    @Override
    public String exportarDados() {
        String nomePac = (consulta != null) ? consulta.getPaciente().getNome() : "N/A";
        String nomeProf = (consulta != null) ? consulta.getProfissional().getNome() : "N/A";
        return "ATENDIMENTO;" + nomePac + ";" + nomeProf + ";"
                + prontuario.getDataRegistro() + ";"
                + prontuario.getDiagnostico() + ";"
                + (concluido ? "concluido" : "aberto");
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }
}


