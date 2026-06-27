public abstract class Pagamento implements Exportavel {
    private double valorBase;

    public Pagamento(double valorBase) {
        this.valorBase = valorBase;
    }

    public double getValorBase() { return valorBase; }
    public void setValorBase(double valorBase) { this.valorBase = valorBase; }

    public abstract double calcularValorFinal();

    @Override
    public String exportarDados() {
        return "PAGAMENTO;" + valorBase + ";" + calcularValorFinal();
    } 
}

class PagamentoDinheiro extends Pagamento {
    public PagamentoDinheiro(double valorBase) {
        super(valorBase);
    }

    @Override
    public double calcularValorFinal() {
        return getValorBase() * 0.95;
    }
}

class PagamentoCartao extends Pagamento {
    private int parcelas;

    public PagamentoCartao(double valorBase, int parcelas) {
        super(valorBase);
        this.parcelas = parcelas;
    }

    @Override
    public double calcularValorFinal() {
        if (this.parcelas > 3) {
            return getValorBase() * (1 + (0.025 * (parcelas - 3)));
        }
        return getValorBase();
    }
}