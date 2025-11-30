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

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }
}

