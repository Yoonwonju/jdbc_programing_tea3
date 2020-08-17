package jdbc_programing_tea3.ui.content;

import javax.swing.SwingConstants;

import jdbc_programing_tea3.dto.Title;

@SuppressWarnings("serial")
public class TableTitle extends AbstractItemTable<Title> {

    @Override
    Object[] getColName() {
        return new String[] {"직책 번호", "직책명"};
    }

    @Override
    Object[] toArray(Title item) {
        return new Object[]{item.getNo(), item.getName()};
    }

    @Override
    void setWidthAndAlign() {
        // column width
        tableSetWidth(150, 200);
        // column alignment
        tableCellAign(SwingConstants.CENTER, 0, 1);
    }

}
