import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Paciente {
    private int idPaciente;
    private String nombre;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;

    private ArrayList<Cita> citas = new ArrayList<>();

    public Paciente(int idPaciente, String nombre, String telefono, String email, LocalDate fechaNacimiento) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void actualizarDatos(String telefono, String email){
        this.telefono = telefono;
        this.email = email;
    }

    public ArrayList<Cita> verHistorial(){
        return citas;
    }

    public void agregarCita(Cita c){
        citas.add(c);
    }

    @Override
    public String toString() {
        return "\nPaciente " + idPaciente +
                "\n Nombre: " + nombre +
                "\n Telefono: " + telefono +
                "\n Email: " + email +
                "\n Fecha de nacimiento: " + fechaNacimiento;
    }
}
