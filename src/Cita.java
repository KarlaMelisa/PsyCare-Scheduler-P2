import java.time.LocalDateTime;

public class Cita {
    private int idCita;
    private LocalDateTime fechaHora;
    //private String estado;
    private String motivo;
    private Paciente paciente;

    public Cita(int idCita, LocalDateTime fechaHora, String motivo, Paciente paciente) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        //this.estado = "CONFIRMADA";
        this.motivo = motivo;
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void reprogramar(LocalDateTime nuevaFecha){
        this.fechaHora= nuevaFecha;
    }

    @Override
    public String toString() {
        return "Cita " + idCita +
                "\n   Fechay hora: " + fechaHora +
                "\n   Motivo: " + motivo +
                "\n   Paciente" + paciente;
    }
}
