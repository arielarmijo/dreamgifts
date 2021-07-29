package com.ipsoflatus.dreamgifts.vista.admin;

import com.github.lgooddatepicker.components.DatePicker;
import com.ipsoflatus.dreamgifts.controlador.admin.ClienteController;
import com.ipsoflatus.dreamgifts.modelo.combobox.ComunaComboBoxModel;
import com.ipsoflatus.dreamgifts.modelo.servicio.ClienteService;
import com.ipsoflatus.dreamgifts.modelo.servicio.ComunaService;
import com.ipsoflatus.dreamgifts.modelo.table.ClienteTableModel;
import com.ipsoflatus.dreamgifts.vista.DreamGifts;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ClienteView extends JPanel {

    private final ClienteController controlador;
    private DreamGifts root;
    
    /**
     * Creates new form PanelCliente
     */
    public ClienteView(DreamGifts root) {
        this.root = root;
        initComponents();
        controlador = new ClienteController();
        controlador.setView(this); 
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelRegistro = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldRut = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldApellido = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldDireccion = new javax.swing.JTextField();
        jButtonCancelar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxComuna = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldTelefono = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldCelular = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        datePicker = new com.github.lgooddatepicker.components.DatePicker();
        jPanelListado = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonActivar = new javax.swing.JButton();
        jButtondesactivar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonVenta = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jTextFieldBuscar = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(850, 500));
        setPreferredSize(new java.awt.Dimension(850, 500));

        jPanelRegistro.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Registro Clientes");
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel6.setText("Rut");
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextFieldRut.setName("Rut 1"); // NOI18N

        jLabel2.setText("Nombre");
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextFieldNombre.setName("CT Nombre"); // NOI18N

        jTextFieldApellido.setName("CT Telefono"); // NOI18N

        jLabel1.setText("Email");
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setText("Dirección");
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextFieldEmail.setName("CT Apellido"); // NOI18N

        jTextFieldDireccion.setName("CT Direccion"); // NOI18N

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonCancelar.setToolTipText("");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jLabel7.setText("Apellido");
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setText("Comuna");
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jComboBoxComuna.setModel(new ComunaComboBoxModel(ComunaService.getInstance()));

        jLabel4.setText("Telefono");
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel10.setText("Celular");
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel11.setText("Fecha Nac.");
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanelRegistroLayout = new javax.swing.GroupLayout(jPanelRegistro);
        jPanelRegistro.setLayout(jPanelRegistroLayout);
        jPanelRegistroLayout.setHorizontalGroup(
            jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRegistroLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanelRegistroLayout.createSequentialGroup()
                        .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1))
                        .addGap(81, 81, 81)
                        .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldRut, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(153, 153, 153)
                        .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                .addComponent(jComboBoxComuna, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldTelefono)
                                .addComponent(jTextFieldCelular))
                            .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(132, 132, 132))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegistroLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonGuardar)
                        .addGap(38, 38, 38))))
        );
        jPanelRegistroLayout.setVerticalGroup(
            jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistroLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBoxComuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRegistroLayout.createSequentialGroup()
                        .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelRegistroLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegistroLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonGuardar)
                            .addComponent(jButtonCancelar))
                        .addContainerGap())
                    .addGroup(jPanelRegistroLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextFieldCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanelListado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable2.setModel(new ClienteTableModel(ClienteService.getInstance()));
        jScrollPane2.setViewportView(jTable2);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Listado Clientes");
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jButtonActivar.setText("Activar");
        jButtonActivar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActivarActionPerformed(evt);
            }
        });

        jButtondesactivar.setText("Desactivar");
        jButtondesactivar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtondesactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtondesactivarActionPerformed(evt);
            }
        });

        jButtonEditar.setText("Editar");
        jButtonEditar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jButtonVenta.setText("Venta");
        jButtonVenta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonVenta)
                .addGap(18, 18, 18)
                .addComponent(jButtonEditar)
                .addGap(18, 18, 18)
                .addComponent(jButtonActivar)
                .addGap(18, 18, 18)
                .addComponent(jButtondesactivar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonActivar)
                    .addComponent(jButtondesactivar)
                    .addComponent(jButtonEditar)
                    .addComponent(jButtonVenta))
                .addContainerGap())
        );

        jButtonBuscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonBuscar.setText("Buscar");
        jButtonBuscar.setToolTipText("");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelListadoLayout = new javax.swing.GroupLayout(jPanelListado);
        jPanelListado.setLayout(jPanelListadoLayout);
        jPanelListadoLayout.setHorizontalGroup(
            jPanelListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelListadoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
                    .addGroup(jPanelListadoLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)))
                .addContainerGap())
        );
        jPanelListadoLayout.setVerticalGroup(
            jPanelListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListadoLayout.createSequentialGroup()
                .addGroup(jPanelListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelListado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelListado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
controlador.grabar();
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
    controlador.cancelar();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVentaActionPerformed
    controlador.vender();
    }//GEN-LAST:event_jButtonVentaActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
      controlador.editar();
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActivarActionPerformed
controlador.activarSeleccionados();
    }//GEN-LAST:event_jButtonActivarActionPerformed

    private void jButtondesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtondesactivarActionPerformed
   controlador.desactivarSeleccionados();
    }//GEN-LAST:event_jButtondesactivarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
   controlador.buscar();
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    public DreamGifts getRoot() {
        return root;
    }

    public JComboBox getjComboBoxComuna() {
        return jComboBoxComuna;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public JTable getjTable2() {
        return jTable2;
    }

    public JTextField getjTextFieldBuscar() {
        return jTextFieldBuscar;
    }

    public JTextField getjTextFieldApellido() {
        return jTextFieldApellido;
    }

    public JTextField getjTextFieldCelular() {
        return jTextFieldCelular;
    }

    public JTextField getjTextFieldDireccion() {
        return jTextFieldDireccion;
    }

    public JTextField getjTextFieldEmail() {
        return jTextFieldEmail;
    }

    public JTextField getjTextFieldNombre() {
        return jTextFieldNombre;
    }

    public JTextField getjTextFieldRut() {
        return jTextFieldRut;
    }

    public JTextField getjTextFieldTelefono() {
        return jTextFieldTelefono;
    }

    public void setjButtonActivar(JButton jButtonActivar) {
        this.jButtonActivar = jButtonActivar;
    }

    public void setjButtonCancelar(JButton jButtonCancelar) {
        this.jButtonCancelar = jButtonCancelar;
    }

    public void setjButtonEditar(JButton jButtonEditar) {
        this.jButtonEditar = jButtonEditar;
    }

    public void setjButtonGuardar(JButton jButtonGuardar) {
        this.jButtonGuardar = jButtonGuardar;
    }

    public void setjButtonVenta(JButton jButtonVenta) {
        this.jButtonVenta = jButtonVenta;
    }

    public void setjButtondesactivar(JButton jButtondesactivar) {
        this.jButtondesactivar = jButtondesactivar;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.lgooddatepicker.components.DatePicker datePicker;
    private javax.swing.JButton jButtonActivar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonVenta;
    private javax.swing.JButton jButtondesactivar;
    private javax.swing.JComboBox jComboBoxComuna;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelListado;
    private javax.swing.JPanel jPanelRegistro;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldBuscar;
    private javax.swing.JTextField jTextFieldCelular;
    private javax.swing.JTextField jTextFieldDireccion;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldRut;
    private javax.swing.JTextField jTextFieldTelefono;
    // End of variables declaration//GEN-END:variables

}
