package jdbc_programing_tea3.dao;

import java.util.List;

import jdbc_programing_tea3.dto.Employee;

public interface EmployeeDao {
    List<Employee> selectEmployeeByAllSimple();
    
    List<Employee> selectEmployeeByAll();

    Employee selectEmployeeByNo(Employee emp);

    int insertEmployee(Employee emp);

    int updateEmployee(Employee emp);

    int deleteEmployee(Employee emp);

}
