package test.jdbc_programming_tea3.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_programing_tea3.dao.EmployeeDao;
import jdbc_programing_tea3.dao.impl.EmployeeDaoImpl;
import jdbc_programing_tea3.dto.Department;
import jdbc_programing_tea3.dto.Employee;
import jdbc_programing_tea3.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
    private EmployeeDao dao;
    
    @Before
    public void setUp() throws Exception {
        dao = EmployeeDaoImpl.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        dao = null;
        System.out.println();
    }

    @Test
    public void test4SelectEmployeeByAll() {
        System.out.printf("%s()%n", "test4SelectEmployeeByAll");
        List<Employee> list = dao.selectEmployeeByAll();
        Assert.assertNotNull(list);
        list.stream().forEach(System.out::println);
    }

    @Test
    public void test5SelectEmployeeByNo() {
        System.out.printf("%s()%n", "test5SelectEmployeeByNo");
        Employee selectedEmp = dao.selectEmployeeByNo(new Employee(1003));
        Assert.assertNotNull(selectedEmp);
        System.out.println(selectedEmp);
    }

    @Test
    public void test1InsertEmployee() {
        System.out.printf("%s()%n", "test1InsertEmployee");
        Employee newEmp = new Employee(1004, "박규영", new Title(5), new Employee(1003), 2000000, new Department(3));
        int res = dao.insertEmployee(newEmp);
        Assert.assertEquals(1, res);

        List<Employee> list = dao.selectEmployeeByAll();
        list.stream().forEach(System.out::println);
    }

    @Test
    public void test2UpdateEmployee() {
        System.out.printf("%s()%n", "test2UpdateEmployee");
        Employee updateEmp = new Employee(1004, "빛나라", new Title(3), new Employee(4377), 4000000, new Department(2));
        int res = dao.updateEmployee(updateEmp);
        Assert.assertEquals(1, res);
        
        List<Employee> list = dao.selectEmployeeByAll();
        list.stream().forEach(System.out::println);
    }

    @Test
    public void test3DeleteEmployee() {
        System.out.printf("%s()%n", "test3DeleteEmployee");
        Employee deleteEmp = new Employee(1004);
        int res = dao.deleteEmployee(deleteEmp);
        Assert.assertEquals(1, res);
        
        List<Employee> list = dao.selectEmployeeByAll();
        list.stream().forEach(System.out::println);
    }

    @Test
    public void test6SelectEmployeeByAllSimple() {
        System.out.printf("%s()%n", "test6SelectEmployeeByAllSimple");
        List<Employee> list = dao.selectEmployeeByAllSimple();
        Assert.assertNotNull(list);
        list.stream().forEach(System.out::println);
    }
}
