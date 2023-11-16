package Vista;

import Modelo.DAOEmpleados;
import Modelo.Empleados;
import Conexion.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import java.sql.Time;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

public class EmpleadosJInternalFrame extends javax.swing.JInternalFrame {

    public EmpleadosJInternalFrame() {
        initComponents();
        obtenerDatos();
    }

    
    // metodo limpiar campos
    public void limpiarCamposEmpleados() {
        jTextNombreEmpleado.setText("");
        jTextRol.setText("");
        jTextHorarioInicio.setText("");
        jTextHorarioFin.setText("");
        jTextDiasTrabajo.setText("");
        jTextIdEmpleado.setText("");
    }

    
    // Método auxiliar para obtener LocalDateTime desde una cadena de texto
private LocalDateTime obtenerLocalDateTimeDesdeString(String cadena) {
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(cadena, formatter);
    } catch (DateTimeParseException e) {
        return null; // Manejar el error según sea necesario
    }
}

    //obtener cambios desde la interfaz, metodo necesario para lo que estoy haciendo en actualizar precios
private Map<String, Object> obtenerCambiosDesdeInterfaz() {
    Map<String, Object> cambios = new HashMap<>();

    // Obtener los nuevos valores de las cajas de texto para los campos relevantes
    String nuevoHorarioInicio = jTextHorarioInicio.getText();
    String nuevoHorarioFin = jTextHorarioFin.getText();
    String nuevoDiasTrabajo = jTextDiasTrabajo.getText();

    // Agregar los nuevos valores al mapa de cambios
    cambios.put("horario_inicio", nuevoHorarioInicio);
    cambios.put("horario_fin", nuevoHorarioFin);
    cambios.put("dias_trabajo", nuevoDiasTrabajo);

    return cambios;
}

    
  // método obtenerDatos
public void obtenerDatos() {
    // Crear una instancia de DAOEmpleados con la conexión
    DAOEmpleados daoEmpleados = new DAOEmpleados(new database().getConnection());

    // Obtener la lista de empleados
    List<Empleados> empleados = daoEmpleados.obtenerDatos();

    DefaultTableModel modelo = new DefaultTableModel();
    String[] columnas = {"ID Empleado", "Nombre", "Rol", "Horario Inicio", "Horario Fin", "Días Trabajo"};
    modelo.setColumnIdentifiers(columnas);

    for (Empleados empleado : empleados) {
        // Verificar si horario_inicio o horario_fin es null antes de mostrarlos
        String horarioInicioStr = (empleado.getHorario_inicio() != null) ? empleado.getHorario_inicio().toString() : "No disponible";
        String horarioFinStr = (empleado.getHorario_fin() != null) ? empleado.getHorario_fin().toString() : "No disponible";

        String[] renglon = {
                String.valueOf(empleado.getId_empleado()),
                empleado.getNombre(),
                empleado.getRol(),
                horarioInicioStr,
                horarioFinStr,
                empleado.getDias_trabajo()
        };
        modelo.addRow(renglon);
    }

    jTableEmpleados.setModel(modelo);
}

//no se que estoy haciendo, pero algo hice
private String obtenerNuevoValorParaColumna(String columna) {
    // Implementar la lógica para obtener el nuevo valor de la caja de texto para la columna dada
    // Devolver el nuevo valor como cadena
    if (columna.equals("nombre")) {
        return jTextNombreEmpleado.getText();
    } else if (columna.equals("rol")) {
        return jTextRol.getText();
    } else if (columna.equals("horario_inicio")) {
        return jTextHorarioInicio.getText();
    } else if (columna.equals("horario_fin")) {
        return jTextHorarioFin.getText();
    } else if (columna.equals("dias_trabajo")) {
        return jTextDiasTrabajo.getText();
    } else {
        // Manejar otras columnas según sea necesario
        return null;
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonAgregar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextNombreEmpleado = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextRol = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextHorarioInicio = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextHorarioFin = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jTextDiasTrabajo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextIdEmpleado = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmpleados = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Empleados", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jButtonAgregar.setText("Agregar");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });

        jButton2.setText("Actualizar horario laboral");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton7.setText("Cancelar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton4.setText("Editar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jButtonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar Empleados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nombre:");

        jTextNombreEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNombreEmpleadoActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Rol:");

        jTextRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextRolActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Horario Inicio:");

        jTextHorarioInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextHorarioInicioActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Horario Salida:");

        jTextHorarioFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextHorarioFinActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(204, 255, 204));
        jButton10.setFont(new java.awt.Font("Sitka Text", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(0, 0, 0));
        jButton10.setText("Agregar hora actual");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(204, 255, 204));
        jButton11.setFont(new java.awt.Font("Sitka Text", 1, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(0, 0, 0));
        jButton11.setText("Agregar hora actual");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTextDiasTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextDiasTrabajoActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Dias trabajados:");

        jTextIdEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextIdEmpleadoActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("ID Empleado:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextDiasTrabajo))
                            .addComponent(jTextHorarioInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(jTextHorarioFin, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel14)
                                        .addGap(81, 81, 81))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addComponent(jTextIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextNombreEmpleado)
                            .addComponent(jTextRol)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(178, 178, 178)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextRol, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextHorarioInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jTextHorarioFin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton11))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTextDiasTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16))
                            .addComponent(jLabel14))
                        .addGap(37, 37, 37))))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel5.setLayout(new javax.swing.OverlayLayout(jPanel5));

        jTableEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane1.setViewportView(jTableEmpleados);

        jPanel5.add(jScrollPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1234, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 7, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 7, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 838, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
//captura de datos de las cajas de texto
String nombre = jTextNombreEmpleado.getText();
String rol = jTextRol.getText();
String horarioInicioStr = jTextHorarioInicio.getText();
String horarioFinStr = jTextHorarioFin.getText();
String diasTrabajo = jTextDiasTrabajo.getText();

try {
    // Comprueba que las cajas de texto no estén vacías
    if (nombre.isEmpty() || rol.isEmpty() || horarioInicioStr.isEmpty() || horarioFinStr.isEmpty()
            || diasTrabajo.isEmpty()) {
        JOptionPane.showMessageDialog(rootPane, "Todos los campos son obligatorios");
    } else {
        // Intenta convertir las cadenas de tiempo a objetos LocalDateTime
        try {
            // Convertir las cadenas de tiempo a objetos LocalDateTime
            LocalDateTime horarioInicio = LocalDateTime.parse(horarioInicioStr); // Ajustar según el formato de entrada
            LocalDateTime horarioFin = LocalDateTime.parse(horarioFinStr); // Ajustar según el formato de entrada

            // Crea un nuevo empleado y llama al método Insertar de DAOEmpleados
            Empleados empleado = new DAOEmpleados(new database().getConnection()).Insertar(nombre, rol,
                    horarioInicio, horarioFin, diasTrabajo);

            if (empleado != null) {
                JOptionPane.showMessageDialog(rootPane, "Registro agregado");
            } else {
                JOptionPane.showMessageDialog(rootPane, "No se pudo agregar el registro");
            }

            obtenerDatos();
            limpiarCamposEmpleados();
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(rootPane,
                    "Error al procesar los horarios. Asegúrate de ingresar el formato correcto.");
        }
    }
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(rootPane, "No se pudo agregar el registro");
}
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// Obtener la fila seleccionada
    int filaSeleccionada = jTableEmpleados.getSelectedRow();

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(rootPane, "Seleccione un registro de la tabla para editar.");
    } else {
        // Obtener el ID de la fila seleccionada
        int id = Integer.parseInt(jTableEmpleados.getValueAt(filaSeleccionada, 0).toString());

        // Obtener los cambios desde la interfaz
        Map<String, Object> cambios = obtenerCambiosDesdeInterfaz();

        // Verificar si hay cambios
        if (!cambios.isEmpty()) {
            try {
                // Actualizar los valores en la base de datos
                int res = new DAOEmpleados(new database().getConnection()).Actualizar(id, cambios);

                // Verificar el resultado de la actualización
                if (res == 1) {
                    JOptionPane.showMessageDialog(rootPane, "Empleado con ID " + id + " actualizado con éxito.");
                    limpiarCamposEmpleados();
                    obtenerDatos(); // Actualizar la tabla con los datos recién cambiados
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Ocurrió un ERROR al actualizar el empleado.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(rootPane, "Por favor, ingrese un ID válido.");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, "Ocurrió un ERROR inesperado: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "No se realizaron cambios. Complete al menos un campo para editar.");
        }
    }
    //esto e muy complicado
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// evento borrar para empleados
    int fila = this.jTableEmpleados.getSelectedRow();

    if (fila == -1) {
        JOptionPane.showMessageDialog(rootPane, "Seleccione un registro de la tabla");
    } else {
        int id = Integer.parseInt((String) this.jTableEmpleados.getValueAt(fila, 0).toString());

        int confirmacion = JOptionPane.showConfirmDialog(rootPane, "¿Está seguro de eliminar este registro?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Obtener la conexión
            Connection conexion = new database().getConnection();

            // Crear la instancia de DAOEmpleados con la conexión
            DAOEmpleados dao = new DAOEmpleados(conexion);
            
            dao.Eliminar(id);
            obtenerDatos();
            JOptionPane.showMessageDialog(rootPane, "Empleado eliminado con éxito");
        }
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
//limpia los campos
    limpiarCamposEmpleados();
    //deselecciona cualquier fila de la tabla si la tienes seleccionadas.
    jTableEmpleados.clearSelection();
    JOptionPane.showMessageDialog(rootPane, "Acción cancelada");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextNombreEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNombreEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNombreEmpleadoActionPerformed

    private void jTextRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextRolActionPerformed

    private void jTextHorarioInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextHorarioInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextHorarioInicioActionPerformed

    private void jTextHorarioFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextHorarioFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextHorarioFinActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
// Obtener la fecha y hora actuales
    LocalDateTime fechaHoraActual = LocalDateTime.now();

    // Formatear la fecha y hora actuales en el formato deseado
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String fechaHoraFormateada = fechaHoraActual.format(formatter);

    // Establecer la fecha y hora actuales en jTextHorarioInicio
    jTextHorarioInicio.setText(fechaHoraFormateada);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
// Obtener la fecha y hora actuales
    LocalDateTime fechaHoraActual = LocalDateTime.now();

    // Formatear la fecha y hora actuales en el formato deseado
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String fechaHoraFormateada = fechaHoraActual.format(formatter);

    // Establecer la fecha y hora actuales en jTextHorarioInicio
    jTextHorarioFin.setText(fechaHoraFormateada);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTextDiasTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextDiasTrabajoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDiasTrabajoActionPerformed

    private void jTextIdEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextIdEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextIdEmpleadoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
int filaSeleccionada = this.jTableEmpleados.getSelectedRow();

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(rootPane, "Seleccione un registro de la tabla");
    } else {
        try {
            //obtener el ID ingresado en jTextId
            int id = Integer.parseInt(jTextIdEmpleado.getText());

            //obtener los nuevos valores de todas las cajas de texto
            String nombre = jTextNombreEmpleado.getText();
            String rol = jTextRol.getText();
            String horarioInicioStr = jTextHorarioInicio.getText();
            String horarioFinStr = jTextHorarioFin.getText();
            String diasTrabajo = jTextDiasTrabajo.getText();

            //verificar si alguno de los campos está vacío
            if (nombre.isEmpty() || rol.isEmpty() || horarioInicioStr.isEmpty() || horarioFinStr.isEmpty() || diasTrabajo.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Todos los campos son obligatorios");
                return; // Salir del método si algún campo está vacío
            }

            LocalDateTime horarioInicio = LocalDateTime.parse(horarioInicioStr);
            LocalDateTime horarioFin = LocalDateTime.parse(horarioFinStr);

            //actualizar los valores en la base de datos
            Map<String, Object> cambios = Map.of(
                "nombre", nombre,
                "rol", rol,
                "horario_inicio", horarioInicio,
                "horario_fin", horarioFin,
                "dias_trabajo", diasTrabajo
            );
            
DAOEmpleados daoEmpleados = new DAOEmpleados();
int res = daoEmpleados.Actualizar(id, cambios);

            //verificar el resultado de la actualización
            if (res == 1) {
                JOptionPane.showMessageDialog(rootPane, "Empleado actualizado con éxito");
                limpiarCamposEmpleados();
                obtenerDatos(); //actualizar la tabla con los datos recién cambiados
            } else {
                JOptionPane.showMessageDialog(rootPane, "Ocurrió un ERROR al actualizar el empleado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Ocurrió un ERROR inesperado: " + e.getMessage());
        }
    }
    //unas ganas de suicidarme con este proyecto
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEmpleados;
    private javax.swing.JTextField jTextDiasTrabajo;
    private javax.swing.JTextField jTextHorarioFin;
    private javax.swing.JTextField jTextHorarioInicio;
    private javax.swing.JTextField jTextIdEmpleado;
    private javax.swing.JTextField jTextNombreEmpleado;
    private javax.swing.JTextField jTextRol;
    // End of variables declaration//GEN-END:variables
}
