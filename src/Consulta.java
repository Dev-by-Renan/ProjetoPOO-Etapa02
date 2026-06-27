public class Consulta implements Agendavel, Exportavel {

    private Paciente paciente;
    private Profissional profissional;
    private String data;
    private String horario;
    private String status;

    public Consulta(Paciente paciente, Profissional profissional, Sting data, String horario) {
        this.paciente = paciente;
        this.profissional = profissional;
        this.data = data;
        this.horario = horario;
        this.status = "agendada";
    }

    @Override
    public void agendar() {
        this.status = "agendada";
    }

    @Override
    public void cancelar() {
        this.status = "cancelada";
    }

    @Override
    public void remarcar() {
        this.status = "remarcada";
    }

    @Override
    public String exportarDados() {
        String cpf = (paciente != null) ? paciente.getCpf() : "null";
        String reg = (profissional != null) ? profissional.getRegistroProfissional() : "null";
        return "CONSULTA;" + cpf + ";" + reg + ";" + data + ";" + horario + ";" + status;
    }
    
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Profissional getProfissional() { return profissional; }
    public void setProfissional(Profissional profissional) { this.profissional = profissional; }

    public String getData() { return data; }

    public void setData(String data) {
        if (data == null || data.trim().isEmpty()) {
            throw new IllegalArgumentException("Data invalida ou vazia.");
        }
        this.data = data;
    }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String exibirResumo() { 
        String nomeP = (paciente != null) ? paciente.getNome() : "N/A"
        String nomeProf = (profissional != null) ? profissional.getNome() : "N/A"
        return "Paciente: " + nomeP + " | Profissional: " + nomeProf + " | Data: " + data + " | Horario: " + horario + " | Status: " + status;
    }
}