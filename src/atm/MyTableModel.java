package atm;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
public class MyTableModel extends AbstractTableModel { //交易记录表格、用户表格
	private Object[] columnNames={"账户","明细","时间","余额","操作人"};
    private Object[][] rowData;
    public boolean flag=false; //是否为用户表格
    public MyTableModel(Object[][] rowData) { //交易记录表格
    	this.rowData=rowData;
    }
    public MyTableModel(Object[] columnNames,Object[][] rowData) { //用户表格
    	flag=true;
    	this.columnNames=columnNames;
    	this.rowData=rowData;
    }
    public int getRowCount() {
        return rowData.length;
    }
    public int getColumnCount() {
        return columnNames.length;
    }
    public String getColumnName(int column) {
        return columnNames[column].toString();
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rowData[rowIndex][columnIndex];
    }
    public boolean isCellEditable(int row,int column) { //用户表格第4列可操作
        if (flag&&column==4)
        	return true; 
        else  
        	return false;
    }
}