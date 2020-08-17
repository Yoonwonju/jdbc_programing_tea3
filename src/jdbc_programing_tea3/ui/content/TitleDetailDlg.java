package jdbc_programing_tea3.ui.content;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import jdbc_programing_tea3.dto.Title;

@SuppressWarnings("serial")
public class TitleDetailDlg extends JDialog implements ActionListener {

    private final JPanel contentPanel = new JPanel();
    private PanelTitle pTitle;
    private JButton okButton;

    public TitleDetailDlg() {
        initComponents();
    }

    public void setDepartment(Title title) {
        pTitle.setTitle(title);
    }
    
    public void setTfNotEditable() {
        pTitle.setTfEditable(false);
    }
    
    private void initComponents() {
        setTitle("직책 세부 정보");
        setBounds(100, 100, 450, 341);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        pTitle = new PanelTitle();
        contentPanel.add(pTitle, BorderLayout.CENTER);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        okButton = new JButton("확인");
        okButton.addActionListener(this);
        okButton.setActionCommand("확인");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            actionPerformedOkButton(e);
        }
    }
    protected void actionPerformedOkButton(ActionEvent e) {
        dispose();
    }
}




