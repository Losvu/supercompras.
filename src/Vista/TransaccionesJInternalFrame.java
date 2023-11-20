package Vista;

import Modelo.DAOTransacciones;
import Modelo.Transacciones;
import Modelo.Cliente;
import Modelo.DAOCliente;
import Modelo.Proveedores;
import Modelo.DAOProveedores;
import java.sql.Connection;


import Conexion.database;
import Vista.ClientesJInternalFrame;
import Vista.ProveedoresJInternalFrame;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


public class TransaccionesJInternalFrame extends javax.swing.JInternalFrame {

    private DAOTransacciones daoTransacciones;
    private DAOCliente daoClientes;
    private DAOProveedores daoProveedores;

    public TransaccionesJInternalFrame() {
        initComponents();
        database db = new database();
        daoTransacciones = new DAOTransacciones(db.getConnection());
        daoClientes = new DAOCliente(db.getConnection());
        daoProveedores = new DAOProveedores(db.getConnection());

        cargarComboboxClientes();
        cargarComboboxProveedores();
        obtenerDatos();
    }

    //metodo para limpiar campos de transacciones
    public void limpiarCamposTransacciones() {
        jTextFechaTransaccion.setText("");
        jComboBoxTipoTransaccion.setSelectedIndex(0);
        jTextTotal.setText("");
        jComboBoxMetodoPago.setSelectedIndex(0);
        jComboBoxClientes.setSelectedIndex(0);
        jComboBoxProveedores.setSelectedIndex(0);
    }

    //metodo para cargar el JComboBox de clientes
  //metodo obtenerDatos
public void obtenerDatos() {
    List<Transacciones> transacciones = daoTransacciones.obtenerDatos();

    DefaultTableModel modelo = new DefaultTableModel();
    String[] columnas = {"ID Transacción", "Fecha y Hora", "Tipo", "Total", "Método de Pago", "Cliente", "Proveedor"};
    modelo.setColumnIdentifiers(columnas);

    for (Transacciones transaccion : transacciones) {
        String fechaHoraStr = transaccion.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Cliente cliente = daoClientes.obtenerClientePorId(transaccion.getClienteId());
        Proveedores proveedor = daoProveedores.obtenerProveedorPorId(transaccion.getProveedorId());

        String[] renglon = {
                String.valueOf(transaccion.getIdTransaccion()),
                fechaHoraStr,
                transaccion.getTipo(),
                String.valueOf(transaccion.getTotal()),
                transaccion.getMetodoPago(),
                cliente != null ? cliente.getNombre() : "", // Evita NPE
                proveedor != null ? proveedor.getNombre() : "" // Evita NPE
        };
        modelo.addRow(renglon);
    }

    jTableTransacciones.setModel(modelo);
}

    private void cargarComboboxClientes() {
        List<String> clientes = daoClientes.obtenerNombresClientes();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(clientes.toArray(new String[0]));
        jComboBoxClientes.setModel(model);
    }

    private void cargarComboboxProveedores() {
        List<String> proveedores = daoProveedores.obtenerNombresProveedores();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(proveedores.toArray(new String[0]));
        jComboBoxProveedores.setModel(model);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonAgregar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButtonAgregar1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextFechaTransaccion = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jTextTotal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jComboBoxClientes = new javax.swing.JComboBox<>();
        jComboBoxProveedores = new javax.swing.JComboBox<>();
        jComboBoxTipoTransaccion = new javax.swing.JComboBox<>();
        jComboBoxMetodoPago = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextIdTransaccion = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTransacciones = new javax.swing.JTable();

        jMenuItem1.setText("Enviar");
        jMenuItem1.setToolTipText("");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos/Inventario", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jButtonAgregar.setText("Agregar");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
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

        jButton8.setText("Agregar cliente");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Agregar proveedor");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButtonAgregar1.setText("Actualizar tabla");
        jButtonAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jButtonAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jButtonAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar transaccion", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Fecha transaccion:");

        jTextFechaTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFechaTransaccionActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tipo transaccion:");

        jButton10.setBackground(new java.awt.Color(204, 255, 204));
        jButton10.setFont(new java.awt.Font("Sitka Text", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(0, 0, 0));
        jButton10.setText("Agregar hora actual");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jTextTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTotalActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Monto total:");

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Metodo pago:");

        jComboBoxClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxProveedores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxTipoTransaccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Venta a cliente", "Compra a proveedor" }));

        jComboBoxMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta de credito", "Tarjeta de debido", "Transaccion" }));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Cliente:");

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Proveedor:");

        jTextIdTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextIdTransaccionActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("ID Transaccion:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(276, 276, 276)
                .addComponent(jLabel16)
                .addGap(300, 300, 300))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxTipoTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextFechaTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel17))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(12, 12, 12)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20)
                                    .addComponent(jComboBoxClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextIdTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTextFechaTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBoxTipoTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jComboBoxProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(jTextIdTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)))
                .addGap(49, 49, 49))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel5.setLayout(new javax.swing.OverlayLayout(jPanel5));

        jTableTransacciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        jScrollPane1.setViewportView(jTableTransacciones);

        jPanel5.add(jScrollPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(383, 383, 383)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1334, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
// Captura de datos de las cajas de texto y JComboBox
    String fechaHoraStr = jTextFechaTransaccion.getText(); // Ajusta según el formato de entrada
    String tipo = jComboBoxTipoTransaccion.getSelectedItem().toString();;
    String totalStr = jTextTotal.getText();
    String metodoPago = jComboBoxMetodoPago.getSelectedItem().toString();
    String clienteSeleccionadoNombre = (String) jComboBoxClientes.getSelectedItem();
    String proveedorSeleccionadoNombre = (String) jComboBoxProveedores.getSelectedItem();

    try {
        //comprobamos que las cajas de texto no estén vacías, que pendejo, obviamente no van a estar vacias si vamos a agregar algo jaja
        if (fechaHoraStr.isEmpty() || tipo.isEmpty() || totalStr.isEmpty() || metodoPago.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Todos los campos son obligatorios");
        } else {
            // Intentamos convertir las cadenas de tiempo y total a objetos LocalDateTime y Double, respectivamente, quien no sabe esto
            try {
                //define el formato esperado para la fecha y hora
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                //convertir la cadena de tiempo a objeto LocalDateTime, apache netbeans tiene el poder de hackiar mi compu
                LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);

                //convertimos la cadena de total a objeto Double, no se pq se hace esto pero se hace con frecuencia lo de convertir datos
                double total = Double.parseDouble(totalStr); //tmc ajustamos según el formato de entrada

                //obtiene el objeto Cliente y Proveedor a partir de los nombres, de locos
                Cliente clienteSeleccionado = daoClientes.obtenerClientePorNombre(clienteSeleccionadoNombre);
                Proveedores proveedorSeleccionado = daoProveedores.obtenerProveedorPorNombre(proveedorSeleccionadoNombre);

                //creamos una nueva transacción y esta madre llama al metodo insertar de DAOTransacciones, impresionante la tecnologia como funciona
              Transacciones transaccion = daoTransacciones.insertar(clienteSeleccionado, fechaHora, total, tipo);

                if (transaccion != null) {
                    JOptionPane.showMessageDialog(rootPane, "Registro agregado");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "No se pudo agregar el registro");
                }

                obtenerDatos();
                limpiarCamposTransacciones();
            } catch (DateTimeParseException | NumberFormatException e) {
                JOptionPane.showMessageDialog(rootPane,
                        "Error al procesar la fecha/hora o el total. Asegúrate de ingresar el formato correcto.");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(rootPane, "No se pudo agregar el registro");
    }
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

int fila = this.jTableTransacciones.getSelectedRow();

if (fila == -1) {
    JOptionPane.showMessageDialog(rootPane, "Seleccione una transacción de la tabla");
} else {
    int idTransaccion = Integer.parseInt((String) this.jTableTransacciones.getValueAt(fila, 0).toString());

    int confirmacion = JOptionPane.showConfirmDialog(rootPane, "¿Está seguro de eliminar esta transacción?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

    if (confirmacion == JOptionPane.YES_OPTION) {
        // Obtener la conexión
       Connection conexion = new database().getConnection();

        // Crear la instancia de DAOTransacciones con la conexión
        DAOTransacciones dao = new DAOTransacciones(conexion);

        dao.eliminar(idTransaccion);
        obtenerDatos();
        JOptionPane.showMessageDialog(rootPane, "Transacción eliminada con éxito");
    }
}

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
//limpia los campos
    limpiarCamposTransacciones();
    //deselecciona cualquier fila de la tabla si la tienes seleccionadas.
    jTableTransacciones.clearSelection();
    JOptionPane.showMessageDialog(rootPane, "Acción cancelada");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
//lo pongo con muchos comentarios para que se entienda mas o menos como trabaje
//el codigo, no es lo mejor para la opcion editar pero es bastante funcional
    try {
        //obtiene el ID de la transacción desde el campo de texto
        String idTransaccionStr = jTextIdTransaccion.getText().trim();

        //verifica si el texto es un número, nos ayuda a corregir problemas futuros
        if (!idTransaccionStr.matches("\\d+")) {
            //mostrar un mensaje de error si el ID no es válido
            JOptionPane.showMessageDialog(rootPane, "Por favor, ingrese un ID válido.");
            return;
        }

        int idTransaccion = Integer.parseInt(idTransaccionStr);

        //obtener los nuevos valores de todas las cajas de texto y combo boxes
        String fechaStr = jTextFechaTransaccion.getText();
        String tipo = jComboBoxTipoTransaccion.getSelectedItem().toString();
        double total = Double.parseDouble(jTextTotal.getText());
        String metodoPago = jComboBoxMetodoPago.getSelectedItem().toString();
        String cliente = jComboBoxClientes.getSelectedItem().toString();
        String proveedor = jComboBoxProveedores.getSelectedItem().toString();

        //verificamos si alguno de los campos está vacío
        if (fechaStr.isEmpty() || tipo.isEmpty() || metodoPago.isEmpty() || cliente.isEmpty() || proveedor.isEmpty()) {
            // Mostrar un mensaje de advertencia si algún campo está vacío
            JOptionPane.showMessageDialog(rootPane, "Todos los campos son obligatorios");
            return; // Salir del método si algún campo está vacío
        }

        //ahora convertir la cadena de fecha a objeto LocalDateTime
        LocalDateTime fecha = LocalDateTime.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        //obtener los IDs de cliente y proveedor desde los objetos DAO
        int idCliente = daoClientes.obtenerClientePorNombre(cliente).getId_cliente();
        int idProveedor = daoProveedores.obtenerProveedorPorNombre(proveedor).getId_proveedor();

        //actualiza los valores en la base de datos
        Map<String, Object> cambios = Map.of(
            "fecha_hora", fecha,
            "total", total,
            "metodo_pago", metodoPago,
            "tipo", tipo,
            "cliente_id", idCliente,
            "proveedor_id", idProveedor
        );

        DAOTransacciones daoTransacciones = new DAOTransacciones();
        
        //llamada al método para actualizar la transacción, metodo definido en
        //DAOTransacciones
        int res = daoTransacciones.actualizarTransaccion(idTransaccion, cambios);

        //verificamos el resultado de la actualización
        if (res == 1) {
            //mostrar un mensaje de éxito si la transacción se actualiza correctamente
            JOptionPane.showMessageDialog(rootPane, "Transacción actualizada con éxito");
            limpiarCamposTransacciones();
            obtenerDatos(); // Actualizar la tabla con los datos recién cambiados
        } else {
            //mostrar otro mensaje de error si ocurre un problema al actualizar la transacción
            JOptionPane.showMessageDialog(rootPane, "Ocurrió un ERROR al actualizar la transacción.");
        }
    } catch (NumberFormatException e) {
        //mostrar un mensaje de error si el ID no es un número válido
        JOptionPane.showMessageDialog(rootPane, "Por favor, ingrese un ID válido.");
    } catch (Exception e) {
        e.printStackTrace();
        //mostrar un mensaje de error genérico en caso de una excepción inesperada
        JOptionPane.showMessageDialog(rootPane, "Ocurrió un ERROR inesperado: " + e.getMessage());
    }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextFechaTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFechaTransaccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFechaTransaccionActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
// Obtener la fecha y hora actuales
    LocalDateTime fechaHoraActual = LocalDateTime.now();

    // Formatear la fecha y hora actuales en el formato deseado
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String fechaHoraFormateada = fechaHoraActual.format(formatter);

    // Establecer la fecha y hora actuales en jTextHorarioInicio
    jTextFechaTransaccion.setText(fechaHoraFormateada);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTotalActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    // Código para abrir el JInternalFrame de Clientes
    ClientesJInternalFrame clientesFrame = new ClientesJInternalFrame();
    clientesFrame.setVisible(true);
    clientesFrame.setSize(800, 600); // Establece el tamaño según tus preferencias
    clientesFrame.setMaximizable(true);
    clientesFrame.setResizable(true);
    clientesFrame.setClosable(true);

    // Agrega el JInternalFrame al JDesktopPane
    JDesktopPane desktopPane = getDesktopPane();
    desktopPane.add(clientesFrame);
    try {
        clientesFrame.setSelected(true); // Asegura que el nuevo frame esté seleccionado
    } catch (java.beans.PropertyVetoException e) {
        e.printStackTrace();
    }

    // Llama al método para cargar y mostrar datos en el JInternalFrame de Clientes
    clientesFrame.obtenerDatos();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
 // Código para abrir el JInternalFrame de Proveedores
    ProveedoresJInternalFrame proveedoresFrame = new ProveedoresJInternalFrame();
    proveedoresFrame.setVisible(true);
    proveedoresFrame.setSize(800, 600); // Establece el tamaño según tus preferencias
    proveedoresFrame.setMaximizable(true);
    proveedoresFrame.setResizable(true);
    proveedoresFrame.setClosable(true);

    // Agrega el JInternalFrame al JDesktopPane
    JDesktopPane desktopPane = getDesktopPane();
    desktopPane.add(proveedoresFrame);
    try {
        proveedoresFrame.setSelected(true); // Asegura que el nuevo frame esté seleccionado
    } catch (java.beans.PropertyVetoException e) {
        e.printStackTrace();
    }

    // Llama al método para cargar y mostrar datos en el JInternalFrame de Proveedores
    proveedoresFrame.obtenerDatos();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTextIdTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextIdTransaccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextIdTransaccionActionPerformed

    private void jButtonAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregar1ActionPerformed
obtenerDatos();
limpiarCamposTransacciones();
JOptionPane.showMessageDialog(rootPane, "Se ha actualizado la tabla exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButtonAgregar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonAgregar1;
    private javax.swing.JComboBox<String> jComboBoxClientes;
    private javax.swing.JComboBox<String> jComboBoxMetodoPago;
    private javax.swing.JComboBox<String> jComboBoxProveedores;
    private javax.swing.JComboBox<String> jComboBoxTipoTransaccion;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JMenuItem jMenuItem1;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTransacciones;
    private javax.swing.JTextField jTextFechaTransaccion;
    private javax.swing.JTextField jTextIdTransaccion;
    private javax.swing.JTextField jTextTotal;
    // End of variables declaration//GEN-END:variables
}
