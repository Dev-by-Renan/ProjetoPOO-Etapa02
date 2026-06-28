import java.util.List;

// SOBRECARGA: gerarRelatorio tem 3 versoes com parametros diferentes
// (resolvido em tempo de compilacao)
public class Relatorio {

    // versao 1: mostra todas as consultas
    // SOBRECARGA: mesmo nome, parametros diferentes
    public static void gerarRelatorio(List<Consulta> consultas, List<Atendimento> atendimentos) {
        System.out.println("\n=== RELATORIO GERAL ===");
        for (Consulta c : consultas) {
            System.out.println(c.exibirResumo());
            String diag = buscarDiagnostico(c, atendimentos);
            if (!diag.isEmpty()) {
                System.out.println("  Diagnostico: " + diag);
            }
            System.out.println("---");
        }
    }

    // versao 2: filtra por nome do profissional
    // SOBRECARGA: mesmo nome, parametros diferentes
    public static void gerarRelatorio(List<Consulta> consultas, List<Atendimento> atendimentos,
                                      String nomeProfissional) {
        System.out.println("\n=== RELATORIO - " + nomeProfissional + " ===");
        boolean achou = false;
        for (Consulta c : consultas) {
            if (c.getProfissional() != null &&
                c.getProfissional().getNome().equals(nomeProfissional)) {
                System.out.println(c.exibirResumo());
                String diag = buscarDiagnostico(c, atendimentos);
                if (!diag.isEmpty()) {
                    System.out.println("  Diagnostico: " + diag);
                }
                System.out.println("---");
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhuma consulta encontrada para esse profissional.");
        }
    }

    // versao 3: filtra por periodo (data inicio e fim)
    // SOBRECARGA: mesmo nome, parametros diferentes
    public static void gerarRelatorio(List<Consulta> consultas, List<Atendimento> atendimentos,
                                      String dataInicio, String dataFim) {
        System.out.println("\n=== RELATORIO - " + dataInicio + " a " + dataFim + " ===");
        for (Consulta c : consultas) {
            if (estaNoIntervalo(c.getData(), dataInicio, dataFim)) {
                System.out.println(c.exibirResumo());
                String diag = buscarDiagnostico(c, atendimentos);
                if (!diag.isEmpty()) {
                    System.out.println("  Diagnostico: " + diag);
                }
                System.out.println("---");
            }
        }
    }

    // resumo financeiro usando List e calcularValorFinal() via polimorfismo
    public static void gerarResumoFinanceiro(List<Consulta> consultas,
                                             List<Pagamento> pagamentos,
                                             List<Double> multas) {
        int realizadas = 0;
        int canceladas = 0;
        double totalFaturado = 0;
        double totalEmMultas = 0;

        for (Consulta c : consultas) {
            if (c.getStatus().equals("realizada")) realizadas++;
            if (c.getStatus().equals("cancelada")) canceladas++;
        }

        // LIGACAO DINAMICA: calcularValorFinal() e chamado em cada subclasse de Pagamento
        for (Pagamento p : pagamentos) {
            totalFaturado += p.calcularValorFinal();
        }

        for (double multa : multas) {
            totalEmMultas += multa;
        }

        System.out.println("\n=== RESUMO FINANCEIRO ===");
        System.out.println("Atendimentos realizados: " + realizadas);
        System.out.println("Total faturado: R$" + Math.round(totalFaturado * 100.0) / 100.0);
        System.out.println("Cancelamentos: " + canceladas);
        System.out.println("Total em multas: R$" + Math.round(totalEmMultas * 100.0) / 100.0);
    }

    // LIGACAO DINAMICA: percorre List<Pessoa> e chama exibirResumo() em cada elemento.
    // O metodo executado depende do tipo REAL do objeto, nao do tipo da referencia.
    // Jornada 15: relatorio unificado de cadastros com polimorfismo
    public static void gerarRelatorioUnificado(List<Pessoa> pessoas) {
        System.out.println("\n=== RELATORIO UNIFICADO DE CADASTROS ===");
        int totalPacientes = 0;
        int totalProfissionais = 0;

        for (Pessoa p : pessoas) {
            // LIGACAO DINAMICA: exibirResumo() do tipo real e chamado automaticamente
            System.out.println(p.exibirResumo());

            // DYNAMIC CASTING: identifica o tipo real para exibir informacoes extras
            if (p instanceof Paciente) {
                Paciente pac = (Paciente) p;
                if (!pac.isAtivo()) {
                    System.out.println("  [ATENCAO] Paciente inativo");
                }
                totalPacientes++;
            } else if (p instanceof Profissional) {
                Profissional prof = (Profissional) p;
                System.out.println("  Especialidade: " + prof.getEspecialidade());
                totalProfissionais++;
            }
            System.out.println("---");
        }

        System.out.println("Total de pacientes: " + totalPacientes);
        System.out.println("Total de profissionais: " + totalProfissionais);
    }

    // Jornada 26: exportacao de dados operacionais
    // LIGACAO DINAMICA: exportarDados() e chamado polimorficamente em cada Exportavel
    // Consulta, Atendimento e Pagamento implementam a interface Exportavel
    public static void exportarDados(List<Consulta> consultas,
                                     List<Atendimento> atendimentos,
                                     List<Pagamento> pagamentos) {
        System.out.println("\n=== EXPORTACAO DE DADOS OPERACIONAIS ===");

        System.out.println("\n-- Consultas --");
        for (Consulta c : consultas) {
            // polimorfismo via interface Exportavel
            System.out.println(c.exportarDados());
        }

        System.out.println("\n-- Atendimentos --");
        for (Atendimento a : atendimentos) {
            System.out.println(a.exportarDados());
        }

        System.out.println("\n-- Pagamentos --");
        for (Pagamento p : pagamentos) {
            System.out.println(p.exportarDados());
        }

        System.out.println("\n=== FIM DA EXPORTACAO ===");
    }

    // Relatorio detalhado de pagamentos com tipo identificado via instanceof
    // LIGACAO DINAMICA: calcularValorFinal() retorna valor diferente para cada subclasse
    public static void gerarRelatorioPagamentos(List<Pagamento> pagamentos) {
        System.out.println("\n=== RELATORIO DE PAGAMENTOS ===");
        double totalGeral = 0;

        for (Pagamento p : pagamentos) {
            // DYNAMIC CASTING: identifica tipo real do pagamento para exibir detalhes
            if (p instanceof PagamentoDinheiro) {
                System.out.println("Tipo: Dinheiro/Pix");
            } else if (p instanceof PagamentoCartao) {
                PagamentoCartao pc = (PagamentoCartao) p;
                System.out.println("Tipo: Cartao | Parcelas: " + pc.getParcelas() + "x");
            } else if (p instanceof PagamentoConvenio) {
                PagamentoConvenio pconv = (PagamentoConvenio) p;
                System.out.println("Tipo: Convenio | Convenio: " + pconv.getNomeConvenio());
            }

            // LIGACAO DINAMICA: cada subclasse calcula o valor final de forma diferente
            double valorFinal = p.calcularValorFinal();
            System.out.println("  Valor base: R$" + p.getValorBase());
            System.out.println("  Valor final: R$" + Math.round(valorFinal * 100.0) / 100.0);
            System.out.println("---");
            totalGeral += valorFinal;
        }

        System.out.println("Total geral recebido: R$" + Math.round(totalGeral * 100.0) / 100.0);
    }

    // busca o diagnostico pelo objeto Consulta, acessando prontuario via getters
    public static String buscarDiagnostico(Consulta consulta, List<Atendimento> atendimentos) {
        for (Atendimento a : atendimentos) {
            if (a.getConsulta() == consulta) {
                return a.getProntuario().getDiagnostico();
            }
        }
        return "";
    }

    // compara datas convertendo para numero inteiro (AAAAMMDD)
    public static boolean estaNoIntervalo(String data, String inicio, String fim) {
        int valorData   = converterDataParaNumero(data);
        int valorInicio = converterDataParaNumero(inicio);
        int valorFim    = converterDataParaNumero(fim);
        return valorData >= valorInicio && valorData <= valorFim;
    }

    // metodo auxiliar privado - nao precisa ser visivel fora da classe
    // converte DD/MM/AAAA para numero tipo 20260519 para poder comparar
    private static int converterDataParaNumero(String data) {
        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(3, 5));
        int ano = Integer.parseInt(data.substring(6, 10));
        return ano * 10000 + mes * 100 + dia;
    }
}