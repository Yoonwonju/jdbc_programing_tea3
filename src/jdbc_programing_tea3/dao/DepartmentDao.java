package jdbc_programing_tea3.dao;

import java.util.List;

import jdbc_programing_tea3.dto.Department;

public interface DepartmentDao {
    List<Department> selectDepartmentByAll();

    Department selectDepartmentByNo(Department dept);

    int insertDepartment(Department dept);

    int updateDepartment(Department dept);

    int deleteDepartment(Department dept);

}
