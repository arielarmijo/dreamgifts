package com.ipsoflatus.dreamgifts.modelo;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JTable;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelExporter implements Exporter {

    private final File file;
    private final JTable table;
    private final String fileName;

    public ExcelExporter(File file, JTable table, String fileName) {
        this.file = file;
        this.table = table;
        this.fileName = fileName;
    }

    @Override
    public boolean export() {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file))) {
            WritableWorkbook workbook = Workbook.createWorkbook(out);
            WritableSheet sheet = workbook.createSheet(fileName, 0);
            for (int i = 0; i < table.getColumnCount(); i++) {
                for (int j = 0; j < table.getRowCount(); j++) {
                    String column = table.getColumnName(i);
                    Object object = table.getValueAt(j, i);
                    sheet.addCell(new Label(i, 0, column));
                    sheet.addCell(new Label(i, j, String.valueOf(object)));
                }
            }
            workbook.write();
            workbook.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
