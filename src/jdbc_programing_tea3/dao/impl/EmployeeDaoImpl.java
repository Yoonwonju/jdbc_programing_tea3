package jdbc_programing_tea3.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc_programing_tea3.conn.JdbcUtil;
import jdbc_programing_tea3.dao.EmployeeDao;
import jdbc_programing_tea3.dto.Department;
import jdbc_programing_tea3.dto.Employee;
import jdbc_programing_tea3.dto.Title;

public class EmployeeDaoImpl implements EmployeeDao {
    private static final EmployeeDaoImpl instance = new EmployeeDaoImpl();

    private EmployeeDaoImpl() {
    }

    public static EmployeeDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Employee> selectEmployeeByAll() {
        String sql = "SELECT EMP_NO, EMP_NAME, MANAGER, SALARY, MGR_NAME, "
                + "       TNO, TITLE_NAME, DNO, DEPT_NAME, FLOOR " + "  FROM VW_EMPLOYEE";
        try (Connection con = JdbcUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                List<Employee> list = new ArrayList<>();
                do {
                    list.add(getEmployee(rs));
                } while (rs.next());
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Employee getEmployee(ResultSet rs) throws SQLException {

        int no = rs.getInt("EMP_NO");
        String name = rs.getString("EMP_NAME");
        Title tno = new Title(rs.getInt("TNO"));
        Employee manager = new Employee(rs.getInt("MANAGER"));
        int salary = rs.getInt("SALARY");
        Department dno = new Department(rs.getInt("DNO"));

        try {
            dno.setName(rs.getString("DEPT_NAME"));
            dno.setFloor(rs.getInt("FLOOR"));
            tno.setName(rs.getString("TITLE_NAME"));
            manager.setName(rs.getString("MGR_NAME"));
        } catch (SQLException e) {
        }

        return new Employee(no, name, tno, manager, salary, dno);
    }

    @Override
    public Employee selectEmployeeByNo(Employee emp) {
        String sql = "SELECT EMP_NO, EMP_NAME, MANAGER, SALARY, MGR_NAME, "
                + "       TNO, TITLE_NAME, DNO, DEPT_NAME, FLOOR " + "  FROM VW_EMPLOYEE " + " WHERE EMP_NO = ?";
        try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setInt(1, emp.getNo());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return getEmployee(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int insertEmployee(Employee emp) {
        String sql = "INSERT INTO EMPLOYEE(EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO) values(?, ?, ?, ?, ?, ?)";
        try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, emp.getNo());
            pstmt.setString(2, emp.getName());
            pstmt.setInt(3, emp.getTno().getNo());
            pstmt.setInt(4, emp.getManager().getNo());
            pstmt.setInt(5, emp.getSalary());
            pstmt.setInt(6, emp.getDno().getNo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateEmployee(Employee emp) {
        String sql = "UPDATE EMPLOYEE SET EMP_NAME=?, TNO=?, MANAGER=?, SALARY=?, DNO=? WHERE EMP_NO=?";

        try (Connection con = JdbcUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql.toString())) {

            pstmt.setString(1, emp.getName());
            pstmt.setInt(2, emp.getTno().getNo());
            pstmt.setInt(3, emp.getManager().getNo());
            pstmt.setInt(4, emp.getSalary());
            pstmt.setInt(5, emp.getDno().getNo());
            pstmt.setInt(6, emp.getNo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteEmployee(Employee emp) {
        String sql = "DELETE FROM EMPLOYEE WHERE EMP_NO=?";
        try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, emp.getNo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> selectEmployeeByAllSimple() {
        String sql = "SELECT EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO FROM EMPLOYEE";
        try (Connection con = JdbcUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                List<Employee> list = new ArrayList<>();
                do {
                    list.add(getEmployee(rs));
                } while (rs.next());
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
