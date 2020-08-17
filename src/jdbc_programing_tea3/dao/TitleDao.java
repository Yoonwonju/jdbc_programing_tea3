package jdbc_programing_tea3.dao;

import java.util.List;

import jdbc_programing_tea3.dto.Title;

public interface TitleDao {
    List<Title> selectTitleByAll();

    Title selectTitleByNo(Title title);

    int insertTitle(Title title);

    int updateTitle(Title title);

    int deleteTitle(Title title);

    Title selectSameTitleEmployeeByTitleNo(Title title);
}
