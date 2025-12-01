import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Psicologo extends Usuario{
    private String especialidad;
    private String numeroLicencia;
    private ArrayList<Paciente> pacientes = new ArrayList<>();
    //private ArrayList<Reporte> reportes = new ArrayList<>();


    public Psicologo(int idUsuario, String nombre, String password, String rol, String especialidad,
                     String numeroLicencia) {
        super(idUsuario, nombre, password, "PSICÓLOGO/A");
        this.especialidad = especialidad;
        this.numeroLicencia = numeroLicencia;
    }


    public void registrarPaciente(Paciente p){
        pacientes.add(p);
    }
    public ArrayList<Cita> buscarCitasPorFecha(LocalDate fechaBuscada) {
        ArrayList<Cita> resultado = new ArrayList<>();

        for (Paciente p : pacientes) {
            for (Cita c : p.verHistorial()) {
                if (c.getFechaHora().toLocalDate().equals(fechaBuscada)) {
                    resultado.add(c);
                }
            }
        }
        return resultado;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }
}

