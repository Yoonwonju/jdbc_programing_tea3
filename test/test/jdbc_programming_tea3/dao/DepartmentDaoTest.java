package test.jdbc_programming_tea3.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_programing_tea3.dao.DepartmentDao;
import jdbc_programing_tea3.dao.impl.DepartmentDaoImpl;
import jdbc_programing_tea3.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
    private DepartmentDao dao;
    
    @Before
    public void setUp() throws Exception {
        dao = DepartmentDaoImpl.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        dao = null;
    }

    @Test
    public void test04SelectDepartmentByAll() {
        System.out.printf("%s()%n", "testSelectDepartmentByAll");
        List<Department> list = dao.selectDepartmentByAll();
        Assert.assertNotNull(list);
        list.stream().forEach(System.out::println);
    }

    @Test
    public void test04SelectDepartmentByNo() {
        System.out.printf("%s()%n", "test04SelectDepartmentByNo");
        Department selectDept = dao.selectDepartmentByNo(new Department(1));
        Assert.assertNotNull(selectDept);
        System.out.println(selectDept);
    }

    @Test
    public void test01InsertDepartment() {
        System.out.printf("%s()%n", "testInsertTitle");
        Department newDept = new Department(5, "마케팅", 20);
        int res = dao.insertDepartment(newDept);
        Assert.assertEquals(1, res);
    }

    @Test
    public void test02UpdateDepartment() {
        System.out.printf("%s()%n", "test02UpdateDepartment");
        Department updateDept = new Department(5, "인사", 10);
        int res = dao.updateDepartment(updateDept);
        Assert.assertEquals(1, res);
    }

    @Test
    public void test03DeleteDepartment() {
        System.out.printf("%s()%n", "test03DeleteDepartment");
        Department delDept = new Department(5);
        int res = dao.deleteDepartment(delDept);
        Assert.assertEquals(1, res);
    }

}
