package jdbc_programing_tea3.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc_programing_tea3.conn.JdbcUtil;
import jdbc_programing_tea3.dao.DepartmentDao;
import jdbc_programing_tea3.dto.Department;

public class DepartmentDaoImpl implements DepartmentDao {
    private static final DepartmentDaoImpl instance = new DepartmentDaoImpl();

    private DepartmentDaoImpl() {
    }

    public static DepartmentDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Department> selectDepartmentByAll() {
        String sql = "SELECT DEPT_NO, DEPT_NAME, FLOOR FROM DEPARTMENT";
        try(Connection con = JdbcUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()){
            if (rs.next()) {
                List<Department> list = new ArrayList<Department>();
                do {
                    list.add(getDepartment(rs));
                }while(rs.next());
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Department getDepartment(ResultSet rs) throws SQLException {
        int no = rs.getInt("DEPT_NO");
        String name = rs.getString("DEPT_NAME");
        int floor = rs.getInt("FLOOR");
        return new Department(no, name, floor);
    }

    @Override
    public Department selectDepartmentByNo(Department dept) {
        String sql = "SELECT DEPT_NO, DEPT_NAME, FLOOR FROM DEPARTMENT WHERE DEPT_NO = ?";
        try(Connection con = JdbcUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);){
            pstmt.setInt(1, dept.getNo());
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    return getDepartment(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int insertDepartment(Department dept) {
        String sql = "INSERT INTO DEPARTMENT VALUES(?, ?, ?)";
        try(Connection con = JdbcUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, dept.getNo());
            pstmt.setString(2, dept.getName());
            pstmt.setInt(3, dept.getFloor());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateDepartment(Department dept) {
        String sql = "UPDATE DEPARTMENT SET DEPT_NAME = ?, FLOOR = ? WHERE DEPT_NO = ?";
        try(Connection con = JdbcUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, dept.getName());
            pstmt.setInt(2, dept.getFloor());
            pstmt.setInt(3, dept.getNo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteDepartment(Department dept) {
        String sql = "DELETE FROM DEPARTMENT WHERE DEPT_NO = ?";
        try(Connection con = JdbcUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, dept.getNo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
