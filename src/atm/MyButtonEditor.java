package atm;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MyButtonEditor extends DefaultCellEditor { //自定义的表格编辑器
	private MyButton bt;
	private MyEvent event;
    public MyButtonEditor() {
        super(new JTextField());
        bt=new MyButton("查看");
    	bt.setFont(new Font("黑体",0,20));
    	bt.setForeground(Color.red.darker());
    	bt.setContentAreaFilled(false);
    	bt.setBorderPainted(false);
    	bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //调用自定义的事件处理方法
            	event.invoke(e);
            }
        });
    }
    public MyButtonEditor(MyEvent e) {
        this();
        this.event=e;
    }
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) { //重写编辑器方法，返回一个按钮给JTable
    	setClickCountToStart(0); //点击1次触发事件
    	//将这个被点击的按钮所在的行和列放进button里面
        bt.setRow(row);
        bt.setColumn(column);
        return bt;
    }
}