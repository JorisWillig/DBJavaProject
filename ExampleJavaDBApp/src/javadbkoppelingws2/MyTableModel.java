/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbkoppelingws2;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {

        Object[][] data;
        String[] columns;
        
        public MyTableModel(Object[][] data, String[] columns) {
            this.data = data;
            this.columns = columns;
        }
        
        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }
        
        public void setNewData(Object[][] newData) {
            this.data = newData;
            fireTableDataChanged();
        }
        
        @Override
        public String getColumnName(int column) {
            return columns[column];
        }
        
        public void setColumnNames(String[] newColumns) {
            this.columns = columns;
            fireTableStructureChanged();
        }
        
        public Object[] getRowData(int index) {
            return data[index];
        }
        
    }
