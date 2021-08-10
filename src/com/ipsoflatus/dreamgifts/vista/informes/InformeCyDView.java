package com.ipsoflatus.dreamgifts.vista.informes;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.ipsoflatus.dreamgifts.controlador.informes.InformeCyDController;
import com.ipsoflatus.dreamgifts.modelo.tabla.informes.CyDTableModel;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class InformeCyDView extends javax.swing.JPanel {

    private final InformeCyDController controlador;
    
    public InformeCyDView() {
        initComponents();
        controlador = new InformeCyDController(this);
        
        // Configura el date picker
        DatePickerSettings desdeSettings = new DatePickerSettings();
        desdeSettings.setFormatForDatesCommonEra("dd/MM/yyyy");
        desdeSettings.setFormatForDatesBeforeCommonEra("dd/MM/uuuu");
        dpDesde.setSettings(desdeSettings);
        desdeSettings.setDateRangeLimits(controlador.obtenerFechaMinima(), controlador.obtenerFechaMaxima());
        
        DatePickerSettings hastaSettings = new DatePickerSettings();
        hastaSettings.setFormatForDatesCommonEra("dd/MM/yyyy");
        hastaSettings.setFormatForDatesBeforeCommonEra("dd/MM/uuuu");
        dpHasta.setSettings(hastaSettings);
        hastaSettings.setDateRangeLimits(controlador.obtenerFechaMinima(), controlador.obtenerFechaMaxima());
        
        Image dateImage = Toolkit.getDefaultToolkit().getImage("images/datepickerbutton.png");
        ImageIcon dateExampleIcon = new ImageIcon(dateImage);
        
        JButton datePickerButton = dpDesde.getComponentToggleCalendarButton();
        datePickerButton.setText("");
        datePickerButton.setIcon(dateExampleIcon);
        
        datePickerButton = dpHasta.getComponentToggleCalendarButton();
        datePickerButton.setText("");
        datePickerButton.setIcon(dateExampleIcon);
        
        dpDesde.setDate(controlador.obtenerFechaMinima());
        dpHasta.setDate(controlador.obtenerFechaMaxima());
        dpDesde.addDateChangeListener(controlador);
        dpHasta.addDateChangeListener(controlador);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane3 = new javax.swing.JDesktopPane();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txfRut = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        dpDesde = new com.github.lgooddatepicker.components.DatePicker();
        dpHasta = new com.github.lgooddatepicker.components.DatePicker();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        btnExportarExcel = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(850, 500));

        jDesktopPane3.setBackground(new java.awt.Color(240, 240, 240));
        jDesktopPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Búsqueda Ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel15.setText("Desde");
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel16.setText("RUT");
        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel17.setText("Hasta");
        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel1.setText("Fecha Venta");
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jDesktopPane3.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(txfRut, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(btnBuscar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(dpDesde, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(dpHasta, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane3Layout = new javax.swing.GroupLayout(jDesktopPane3);
        jDesktopPane3.setLayout(jDesktopPane3Layout);
        jDesktopPane3Layout.setHorizontalGroup(
            jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dpDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dpHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfRut, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jDesktopPane3Layout.setVerticalGroup(
            jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane3Layout.createSequentialGroup()
                .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dpDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Listado Ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jTable.setModel(new CyDTableModel());
        jScrollPane3.setViewportView(jTable);

        btnExportarExcel.setText("Exportar a Excel");
        btnExportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExportarExcel)
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExportarExcel)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDesktopPane3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        System.out.println(evt.paramString());
        controlador.buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarExcelActionPerformed
        System.out.println(evt.paramString());
        controlador.exportarAExcel();
    }//GEN-LAST:event_btnExportarExcelActionPerformed

    public DatePicker getDpDesde() {
        return dpDesde;
    }

    public DatePicker getDpHasta() {
        return dpHasta;
    }

    public JTable getjTable() {
        return jTable;
    }

    public JTextField getTxfRut() {
        return txfRut;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExportarExcel;
    private com.github.lgooddatepicker.components.DatePicker dpDesde;
    private com.github.lgooddatepicker.components.DatePicker dpHasta;
    private javax.swing.JDesktopPane jDesktopPane3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField txfRut;
    // End of variables declaration//GEN-END:variables
}