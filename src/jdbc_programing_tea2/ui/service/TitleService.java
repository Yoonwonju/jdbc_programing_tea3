package jdbc_programing_tea2.ui.service;

import java.util.List;

import jdbc_programing_tea3.dao.TitleDao;
import jdbc_programing_tea3.dao.impl.TitleDaoImpl;
import jdbc_programing_tea3.dto.Title;

public class TitleService {
    private TitleDao dao = TitleDaoImpl.getInstance();
    
    public void addTitle(Title title) {
        dao.insertTitle(title);
    }
    
    public List<Title> getTitleList(){
        return dao.selectTitleByAll();
    }
    
    public void modifyTitle(Title title) {
        dao.updateTitle(title);
    }
    
    public void removeTitle(Title title) {
        dao.deleteTitle(title);
    }
}
