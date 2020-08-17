package test.jdbc_programming_tea3.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_programing_tea3.dao.TitleDao;
import jdbc_programing_tea3.dao.impl.TitleDaoImpl;
import jdbc_programing_tea3.dto.Employee;
import jdbc_programing_tea3.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
    private TitleDao dao;
    
    @Before
    public void setUp() throws Exception {
        dao = TitleDaoImpl.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        dao = null;
    }

    @Test
    public void test04SelectTitleByAll() {
        System.out.printf("%s()%n", "testSelectTitleByAll");
        List<Title> list = dao.selectTitleByAll();
        Assert.assertNotNull(list);
        list.stream().forEach(System.out::println);
    }

    @Test
    public void test05SelectTitleByNo() {
        System.out.printf("%s()%n", "testSelectTitleByNo");
        Title selectTitle = dao.selectTitleByNo(new Title(5));
        Assert.assertNotNull(selectTitle);
        System.out.println(selectTitle);
    }

    @Test
    public void test01InsertTitle() {
        System.out.printf("%s()%n", "testInsertTitle");
        Title newTitle = new Title(6, "인턴");
        int res = dao.insertTitle(newTitle);
        Assert.assertEquals(1, res);
    }

    @Test
    public void test02UpdateTitle() {
        System.out.printf("%s()%n", "testUpdateTitle");
        Title updateTitle = new Title(6, "계약직");
        int res = dao.updateTitle(updateTitle);
        Assert.assertEquals(1, res);
    }

    @Test
    public void test03DeleteTitle() {
        System.out.printf("%s()%n", "test03DeleteTitle");
        Title deleteTitle = new Title(6);
        int res = dao.deleteTitle(deleteTitle);
        Assert.assertEquals(1, res);
    }

    @Test
    public void test06selectSameTitleEmployeeByTitleNo() {
        System.out.printf("%s()%n", "test06selectSameTitleEmployeeByTitleNo");
        Title title3 = dao.selectSameTitleEmployeeByTitleNo(new Title(3));
        List<Employee> list = title3.getList();
        Assert.assertNotNull(list);
        list.stream().forEach(System.out::println);
    }
}
