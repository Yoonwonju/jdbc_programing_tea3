package jdbc_programing_tea3.ui.content;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import jdbc_programing_tea2.ui.service.TitleService;
import jdbc_programing_tea3.dto.Title;

@SuppressWarnings("serial")
public class FrameTitle extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JPanel pContent;
    private JPanel pTable;
    private PanelTitle pTitle;
    private JPanel pBtns;
    private JButton btnAdd;
    private JButton btnCancel;
    private JScrollPane scrollPane;
    private TableTitle tableTitle;

    private TitleService tService;
    private ArrayList<Title> titleList;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrameTitle frame = new FrameTitle();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrameTitle() {
        tService = new TitleService();
        titleList = (ArrayList<Title>) tService.getTitleList();
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 498, 272);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        
        pContent = new JPanel();
        contentPane.add(pContent);
        pContent.setLayout(new BoxLayout(pContent, BoxLayout.Y_AXIS));
        
        pTitle = new PanelTitle();
        pContent.add(pTitle);
        
        pBtns = new JPanel();
        pContent.add(pBtns);
        
        btnAdd = new JButton("추가");
        btnAdd.addActionListener(this);
        pBtns.add(btnAdd);
        
        btnCancel = new JButton("취소");
        btnCancel.addActionListener(this);
        pBtns.add(btnCancel);
        
        pTable = new JPanel();
        contentPane.add(pTable);
        pTable.setLayout(new BorderLayout(0, 0));
        
        scrollPane = new JScrollPane();
        pTable.add(scrollPane, BorderLayout.CENTER);
        
        tableTitle = new TableTitle();
        tableTitle.setItems(titleList);
        
        CustomPopupMenu popMenu = new CustomPopupMenu(this);
        tableTitle.setComponentPopupMenu(popMenu);
        scrollPane.setViewportView(tableTitle);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == btnCancel) {
                actionPerformedBtnCancel(e);
            }
            if (e.getSource() == btnAdd) {
                if (e.getActionCommand().equals("추가")) {
                    actionPerformedBtnAdd(e);
                }else {
                    actionPerformedBtnUpdate();
                }
            }
            if (e.getSource() instanceof JMenuItem) {
                if (e.getActionCommand().equals("수정")) {
                    actionPerformedMenuUpdate();
                }
                if (e.getActionCommand().equals("삭제")) {
                    actionPerformedMenuDelete();
                }
                if (e.getActionCommand().equals("세부 정보")) {
                    actionPerformedMenuDetail();
                }
            }
        }catch (Exception ee) {
            JOptionPane.showMessageDialog(null, ee.getMessage());
            ee.printStackTrace();
        }
    }
    
    private void actionPerformedBtnUpdate() {
        Title updateTitle = pTitle.getTitle();
        tService.modifyTitle(updateTitle);
        
        int idx = titleList.indexOf(updateTitle);
        titleList.set(idx, updateTitle);
        tableTitle.updateRow(idx, updateTitle);
        
        pTitle.clearTf();
        pTitle.setEditableNoTf(true);
        btnAdd.setText("추가");
        
        JOptionPane.showMessageDialog(null, String.format("%s(%d) 수정 완료!!", updateTitle.getName(), updateTitle.getNo()));        
    }

    private void actionPerformedBtnCancel(ActionEvent e) {
        pTitle.clearTf();
        if (btnAdd.getText().equals("수정")) {
            btnAdd.setText("추가");
            pTitle.setEditableNoTf(true);
            tableTitle.clearSelection();
        }        
    }

    private void actionPerformedMenuDetail() {
        int selIdx = tableTitle.getSelectedRow();
        if (selIdx == -1) {
            JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요.");
            return;
        }
        Title detailTitle = titleList.get(selIdx);
        TitleDetailDlg dlg = new TitleDetailDlg();
        dlg.setDepartment(detailTitle);
        dlg.setTfNotEditable();
        dlg.setVisible(true);        
    }

    private void actionPerformedMenuDelete() {
        int selIdx = tableTitle.getSelectedRow();
        if (selIdx == -1) {
            JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요.");
            return;
        }
        Title deleteTitle = titleList.get(selIdx);
        
        tService.removeTitle(deleteTitle);
        titleList.remove(deleteTitle);
        
        tableTitle.removeRow(selIdx);
        JOptionPane.showMessageDialog(null, String.format("%s(%d) 삭제 완료!!", deleteTitle.getName(), deleteTitle.getNo()));        
    }

    private void actionPerformedMenuUpdate() {
        int selIdx = tableTitle.getSelectedRow();
        if (selIdx == -1) {
            JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요.");
            return;
        }
        Title selDept = titleList.get(selIdx);
        pTitle.setTitle(selDept);
        btnAdd.setText("수정");
        pTitle.setEditableNoTf(false);
    }

    protected void actionPerformedBtnAdd(ActionEvent e) {
        Title newTitle = pTitle.getTitle();
        tService.addTitle(newTitle);
        titleList.add(newTitle);
        tableTitle.addRow(newTitle);
        JOptionPane.showMessageDialog(null, "추가완료");
    }
}
