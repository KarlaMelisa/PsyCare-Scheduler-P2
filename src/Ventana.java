import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class Ventana {
    private JPanel principal;
    private JTabbedPane tabbedPane1;
    private JTextField txtNombrePaciente;
    private JSpinner spIDPaciente;
    private JSpinner spFechaNac;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JButton btnMostrarPacientes;
    private JList listPacientes;
    private JTextField textNuevoTelefono;
    private JTextField txtNuevoEmail;
    private JButton btnActualizarPaciente;
    private JTextField txtPacienteEliminar;
    private JButton btnEliminar;
    private JButton btnAgregarPaciente;
    private JComboBox cbMes;
    private JComboBox cbDia;
    private JComboBox cbAnio;
    Psicologo psicologo = new Psicologo(001, "MISHELL GUZMAN", "casafeliz123", "PSICOLOGO/A", "COGNITIVO CONDUCTUAL", "AB379221");

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void limpiarRegistro() {
        txtNombrePaciente.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        spFechaNac.setValue(1);
        cbAnio.setSelectedIndex(1);
        cbDia.setSelectedIndex(1);
        cbMes.setSelectedIndex(1);
    }

    public Ventana() {
        SpinnerNumberModel snm = new SpinnerNumberModel(1, 1, 100, 1);
        spIDPaciente.setModel(snm);
        for (int i = 1; i <= 31; i++) {
            cbDia.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            cbMes.addItem(i);
        }
        for (int i = 1925; i <= (LocalDate.now().getYear())-5; i++) {
            cbAnio.addItem(i);
        }
        btnAgregarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id= (int) spIDPaciente.getValue();
                for (Paciente p : psicologo.getPacientes()) {
                    if (p.getIdPaciente() == id) {
                        JOptionPane.showMessageDialog(null, "Ya existe un paciente con ese ID");
                        return;
                    } else if (id < 0 ) {
                        JOptionPane.showMessageDialog(null, "ID no valido");
                        return;
                    }
                }
                String nombre= (String) txtNombrePaciente.getText();

                String telefono= (String) txtTelefono.getText();
                String email= (String) txtEmail.getText();
                if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error en los datos");
                    return;
                }
                int dia = (int) cbDia.getSelectedItem();
                int mes = (int) cbMes.getSelectedItem();
                int anio = (int) cbAnio.getSelectedItem();
                LocalDate fechaNac = LocalDate.of(anio, mes, dia);
                Paciente paciente = new Paciente(id, nombre, telefono, email, fechaNac);
                psicologo.registrarPaciente(paciente);
                JOptionPane.showMessageDialog(null, "Paciente agregado exitosamente");
                limpiarRegistro();
            }
        });
    }
}
