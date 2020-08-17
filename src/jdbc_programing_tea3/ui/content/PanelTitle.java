package jdbc_programing_tea3.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import jdbc_programing_tea3.dto.Title;

@SuppressWarnings("serial")
public class PanelTitle extends JPanel {
    private JTextField tfNo;
    private JTextField tfName;

    public PanelTitle() {

        initComponents();
    }
    private void initComponents() {
        setBorder(new TitledBorder(null, "직책 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        setLayout(new GridLayout(0, 2, 10, 10));
        
        JLabel lblNo = new JLabel("번호");
        lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
        add(lblNo);
        
        tfNo = new JTextField();
        add(tfNo);
        tfNo.setColumns(10);
        
        JLabel lblName = new JLabel("번호");
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        add(lblName);
        
        tfName = new JTextField();
        tfName.setColumns(10);
        add(tfName);
    }
    
    public void setTitle(Title title) {
        tfNo.setText(title.getNo()+"");
        tfName.setText(title.getName());
    }

    public Title getTitle() {
        int no = Integer.parseInt(tfNo.getText().trim());
        String name = tfName.getText().trim();
        return new Title(no, name);
    }
    
    public void setEditableNoTf(boolean isEditable) {
        tfNo.setEditable(isEditable);
    }
    
    public void clearTf() {
        tfNo.setText("");
        tfName.setText("");
    }
    
    public void setTfEditable(boolean isEditable) {
        tfNo.setEditable(isEditable);
        tfName.setEditable(isEditable);
    }
}
