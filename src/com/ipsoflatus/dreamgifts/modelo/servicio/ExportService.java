package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.exporter.ExcelExporter;
import com.ipsoflatus.dreamgifts.modelo.exporter.Exporter;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExportService {
    
    public void exportarAExcel(JTable tabla) {
        if (tabla.getRowCount() > 0) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Guardar archivo");
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String file = chooser.getSelectedFile().toString().concat(".xls");
                try {
                    Exporter e = new ExcelExporter(new File(file), tabla, "Ventas");
                    if (e.export()) {
                        JOptionPane.showMessageDialog(null, "Datos exportados a " + file, "Mensaje de Informacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Hubo un error " + e.getMessage(), " Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay datos para exportar","Mensaje de error",JOptionPane.ERROR_MESSAGE);
        }
    }

}
