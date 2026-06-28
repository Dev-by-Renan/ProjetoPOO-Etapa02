import java.util.ArrayList;
import java.util.List;

// COMPOSIÇÃO: Prontuario só existe dentro de Atendimento.
// Construtor package-private garante que só Atendimento consegue criar um Prontuario.
class Prontuario {

    private String observacoes;
    private String diagnostico;
    // ArrayList<String>: ordem de inserção importa; procedimentos adicionados sequencialmente
    private List<String> procedimentos;
    private String dataRegistro;

    Prontuario(String observacoes) {
        this.observacoes = observacoes;
        this.diagnostico = "";
        this.procedimentos = new ArrayList<>();
        this.dataRegistro = java.time.LocalDate.now().toString();
    }

    Prontuario(String observacoes, String diagnostico) {
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.procedimentos = new ArrayList<>();
        this.dataRegistro = java.time.LocalDate.now().toString();
    }

    // SOBRECARGA: mesmo nome, parâmetros diferentes (resolvido em tempo de compilação)
    public void adicionarProcedimento(String procedimento) {
        procedimentos.add(procedimento);
    }

    public void adicionarProcedimento(String[] procs, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            procedimentos.add(procs[i]);
        }
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public List<String> getProcedimentos() {
        return procedimentos;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void exibir() {
        System.out.println("  Data: " + dataRegistro);
        System.out.println("  Observacoes: " + observacoes);
        if (!diagnostico.equals("")) {
            System.out.println("  Diagnostico: " + diagnostico);
        }
        if (!procedimentos.isEmpty()) {
            System.out.print("  Procedimentos: ");
            for (int i = 0; i < procedimentos.size(); i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(procedimentos.get(i));
            }
            System.out.println();
        }
    }
}
