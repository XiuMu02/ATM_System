package atm;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
public class MyTableModel extends AbstractTableModel { //���׼�¼����û����
	private Object[] columnNames={"�˻�","��ϸ","ʱ��","���","������"};
    private Object[][] rowData;
    public boolean flag=false; //�Ƿ�Ϊ�û����
    public MyTableModel(Object[][] rowData) { //���׼�¼���
    	this.rowData=rowData;
    }
    public MyTableModel(Object[] columnNames,Object[][] rowData) { //�û����
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
    public boolean isCellEditable(int row,int column) { //�û�����4�пɲ���
        if (flag&&column==4)
        	return true; 
        else  
        	return false;
    }
}