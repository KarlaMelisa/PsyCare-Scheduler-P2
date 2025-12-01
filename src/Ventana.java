import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
    private JTextArea txtPacienteEliminar;
    private JButton btnEliminar;
    private JButton btnAgregarPaciente;
    private JComboBox cbMes;
    private JComboBox cbDia;
    private JComboBox cbAnio;
    private JLabel lbNombreEditado;
    private JSpinner spIDCita;
    private JComboBox cbMesCita;
    private JComboBox cbDiaCita;
    private JComboBox cbAnioCita;
    private JComboBox cbHoraCita;
    private JComboBox cbMotivo;
    private JComboBox cbBuscarDia;
    private JComboBox cbBuscarMes;
    private JComboBox cbBuscarAnio;
    private JButton btnBuscarFecha;
    private JButton btnBuscarPaciente;
    private JList listCita;
    private JComboBox cbAnioEditar;
    private JComboBox cbMesEditar;
    private JComboBox cbDiaEditar;
    private JComboBox cbHoraEditar;
    private JComboBox cbMotivoEditar;
    private JButton btnModificarCita;
    private JTextArea txtCitaCancelar;
    private JButton btnAgendarCita;
    private JList listPacientesParaCita;
    private JButton btnMostrarNombre;
    private JList listPacientesBuscar;
    private JList listPacientesEditar;
    private JButton btnCancelar;
    private JTextField txtPacienteCita;
    private JTextField txtNombreEditado;
    private boolean dobleClickP = false;
    private boolean dobleClickC = false;
    private Paciente seleccionado;
    private Cita seleccionada;
    Psicologo psicologo = new Psicologo(001, "MISHELL GUZMAN", "casafeliz123", "PSICOLOGO/A", "COGNITIVO CONDUCTUAL", "AB379221");

    public static void main(String[] args) {
        JFrame frame = new JFrame("Psycare Scheduler");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void limpiarRegistroPaciente() {
        txtNombrePaciente.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        spFechaNac.setValue(1);
        cbAnio.setSelectedIndex(1);
        cbDia.setSelectedIndex(1);
        cbMes.setSelectedIndex(1);
    }

    public void limpiarEdicionPaciente() {
        textNuevoTelefono.setText("");
        txtNuevoEmail.setText("");
    }

    public DefaultListModel crearDlm(){
        DefaultListModel dlm = new DefaultListModel();
        for (Paciente p : psicologo.getPacientes()) {
            dlm.addElement(p);
        }
        return dlm;
    }
    public void dlmCitas(ArrayList<Cita> citas){
        DefaultListModel citasEncontradas= new DefaultListModel<>();
        for (Cita c : citas) {
            citasEncontradas.addElement(c);
        }
        listCita.setModel(citasEncontradas);
    }
    public DefaultListModel crearDlmCitas(Paciente paciente){
        DefaultListModel dlm= new DefaultListModel<>();
        for (Cita c : paciente.verHistorial()) {
            dlm.addElement(c);
        }
        return dlm;
    }
    public void setCBPacientesFecha(){
        for (int i = 1; i <= 31; i++) {cbDia.addItem(i);}
        for (int i = 1; i <= 12; i++) {cbMes.addItem(i);}
        for (int i = 1925; i <= (LocalDate.now().getYear())-5; i++) {cbAnio.addItem(i);}
    }

    public void setCBCitas(){
        for (int i = 1; i <= 31; i++) cbDiaCita.addItem(i);
        for (int i = 1; i <= 12; i++) cbMesCita.addItem(i);
        for (int i = 2025; i <= 2026; i++) cbAnioCita.addItem(i);
        for (int i = 9; i < 18; i++) cbHoraCita.addItem(i);
    }
    public void setCBCitasEditar(){
        for (int i = 1; i <= 31; i++) {cbDiaEditar.addItem(i);}
        for (int i = 1; i <= 12; i++) {cbMesEditar.addItem(i);}
        for (int i = 2025; i <= 2026; i++) {cbAnioEditar.addItem(i);}
        for (int i = 9; i < 18; i++) {cbHoraEditar.addItem(i);}
    }
    public void setCBCitasBuscar(){
        for (int i = 1; i <= 31; i++) {cbBuscarDia.addItem(i);}
        for (int i = 1; i <= 12; i++) {cbBuscarMes.addItem(i);}
        for (int i = 2025; i <= 2026; i++) {cbBuscarAnio.addItem(i);}
    }
    public Ventana() {
        SpinnerNumberModel snm = new SpinnerNumberModel(1, 1, 100, 1);
        spIDPaciente.setModel(snm);
        spIDCita.setModel(snm);
        setCBPacientesFecha();
        setCBCitas();
        setCBCitasEditar();
        setCBCitasBuscar();
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
                limpiarRegistroPaciente();
                setCBPacientesFecha();

            }

        });
        btnMostrarPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel d= crearDlm();
                listPacientes.setModel(d);
            }
        });
        btnActualizarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paciente paciente = (Paciente) listPacientes.getSelectedValue();
                String telefono = (String) textNuevoTelefono.getText().trim();
                String email = (String) txtNuevoEmail.getText().trim();
                if (paciente == null) {
                    JOptionPane.showMessageDialog(null, "No hay paciente seleccionado");
                    return;
                }
                paciente.setEmail(email);
                if (!telefono.isEmpty()) {
                    paciente.setTelefono(telefono);
                }
                if (!email.isEmpty()){
                    paciente.setEmail(email);
                }
                JOptionPane.showMessageDialog(null, "Paciente modificado con éxito");
                limpiarEdicionPaciente();
            }
        });
        listPacientes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Paciente paciente= (Paciente) listPacientes.getSelectedValue();
                lbNombreEditado.setText(paciente.getNombre());
            }
        });
        listPacientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    seleccionado= (Paciente) listPacientes.getSelectedValue();
                    if (seleccionado != null){
                        txtPacienteEliminar.setText(seleccionado.toString());
                        dobleClickP = true;
                    }
                    txtNombreEditado.setText("");
                }
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel d= crearDlm();
                int index = listPacientes.getSelectedIndex();
                if (!dobleClickP || seleccionado == null) {
                    JOptionPane.showMessageDialog(null, "Seleccionar paciente con doble clic.");
                    return;
                }
                seleccionado = (Paciente) listPacientes.getSelectedValue();
                int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar al paciente " + seleccionado.getNombre() + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    d.remove(index);
                    psicologo.getPacientes().remove(seleccionado);
                        JOptionPane.showMessageDialog(null, "Paciente eliminado exitosamente");
                    }
                txtPacienteEliminar.setText("");
                dobleClickP = false;
            }
        });

        btnAgendarCita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paciente paciente = (Paciente) listPacientesParaCita.getSelectedValue();
                String nombre= paciente.getNombre();
                int id= (int) spIDCita.getValue();
                int dia = (int) cbDiaCita.getSelectedItem();
                int mes = (int) cbMesCita.getSelectedItem();
                int anio = (int) cbAnioCita.getSelectedItem();
                int hora = (int) cbHoraCita.getSelectedItem();
                LocalDateTime fechaHora = LocalDateTime.of(anio, mes, dia, hora, 0);
                String motivo = (String) cbMotivo.getSelectedItem();

                for (Cita c : paciente.verHistorial()) {
                    if (c.getIdCita() == id) {
                        JOptionPane.showMessageDialog(null, "Ya existe una cita con ese ID");
                        return;
                    } else if (id < 0 ) {
                        JOptionPane.showMessageDialog(null, "ID no valido");
                        return;
                    }
                }
                Cita cita = new Cita(id, fechaHora, motivo,  paciente);
                paciente.agregarCita(cita);
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error en los datos");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Cita agregada exitosamente");
                setCBCitas();
            }
        });

        btnMostrarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel d= crearDlm();
                listPacientesParaCita.setModel(d);
                listPacientesBuscar.setModel(d);
                listPacientesEditar.setModel(d);
            }
        });
        btnBuscarFecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dia = (int) cbBuscarDia.getSelectedItem();
                int mes = (int) cbBuscarMes.getSelectedItem();
                int anio = (int) cbBuscarAnio.getSelectedItem();
                LocalDate fechaBuscar = LocalDate.of(anio, mes, dia);
                dlmCitas(psicologo.buscarCitasPorFecha(fechaBuscar));
                setCBCitasBuscar();
            }
        });

        btnBuscarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paciente paciente = (Paciente) listPacientesBuscar.getSelectedValue();
                dlmCitas(paciente.verHistorial());
                if (paciente.getNombre().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Datos incompletos para la búsqueda");
                }
            }
        });

        btnModificarCita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cita cita = (Cita) listCita.getSelectedValue();
                Paciente paciente= (Paciente) listPacientesEditar.getSelectedValue();
                String nombre= paciente.getNombre();
                int dia = (int) cbDiaEditar.getSelectedItem();
                int mes = (int) cbMesEditar.getSelectedItem();
                int anio = (int) cbAnioEditar.getSelectedItem();
                int hora = (int) cbHoraEditar.getSelectedItem();
                LocalDateTime fechaHora = LocalDateTime.of(anio, mes, dia, hora, 0);
                String motivo = (String) cbMotivoEditar.getSelectedItem();
                cita.setFechaHora(fechaHora);

                if (!motivo.isEmpty()) {
                    cita.setMotivo(motivo);
                }else {
                    JOptionPane.showMessageDialog(null, "Datos incompletos");
                }
                if (!nombre.isEmpty()) {
                    cita.setPaciente(paciente);
                }else {
                    JOptionPane.showMessageDialog(null, "Datos incompletos");
                }
                JOptionPane.showMessageDialog(null, "Cita modificada exitosamente");
                setCBCitasEditar();
            }
        });
        listPacientesEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    seleccionada= (Cita) listCita.getSelectedValue();
                    if (seleccionada != null){
                        txtCitaCancelar.setText(seleccionada.toString());
                        dobleClickC = true;
                    }
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paciente paciente= seleccionada.getPaciente();
                DefaultListModel d= crearDlmCitas(paciente);
                int index = listCita.getSelectedIndex();
                if (!dobleClickC || seleccionada == null) {
                    JOptionPane.showMessageDialog(null, "Seleccionar cita con doble clic.");
                    return;
                }
                seleccionada = (Cita) listCita.getSelectedValue();
                int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar la cita " + seleccionada.toString() + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    d.remove(index);
                    paciente.verHistorial().remove(seleccionada);
                    JOptionPane.showMessageDialog(null, "Cita eliminado exitosamente");
                }
                dobleClickC = false;
            }
        });
    }

}
