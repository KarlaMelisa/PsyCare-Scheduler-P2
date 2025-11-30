import java.time.LocalDateTime;

public class Cita {
    private int idCita;
    private LocalDateTime fechaHora;
    private String estado;
    private String motivo;

    public Cita(int idCita, LocalDateTime fechaHora, String estado, String motivo) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.motivo = motivo;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void reprogramar(LocalDateTime nuevaFecha){
        this.fechaHora= nuevaFecha;
        this.estado= "REPROGRAMADA";
    }

    public void cancelar(){
        this.estado= "CANCELADA";
    }

    public void completar(){
        this.estado= "COMPLETADA";
    }
    @Override
    public String toString() {
        return "\nCita " + idCita +
                "\nFecha y hora: " + fechaHora +
                "\nEstado: " + estado +
                "\nMotivo: " + motivo;
    }
}
