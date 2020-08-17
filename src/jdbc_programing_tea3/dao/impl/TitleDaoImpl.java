package jdbc_programing_tea3.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc_programing_tea3.conn.JdbcUtil;
import jdbc_programing_tea3.dao.TitleDao;
import jdbc_programing_tea3.dto.Department;
import jdbc_programing_tea3.dto.Employee;
import jdbc_programing_tea3.dto.Title;

public class TitleDaoImpl implements TitleDao {
    private static final TitleDaoImpl instance = new TitleDaoImpl();

    private TitleDaoImpl() {}

    public static TitleDaoImpl getInstance() {
        return instance;
    }
    
    @Override
    public List<Title> selectTitleByAll() {
        String sql = "SELECT TITLE_NO, TITLE_NAME FROM TITLE";
        try(Connection con = JdbcUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()){
            if (rs.next()) {
                List<Title> list = new ArrayList<Title>();
                do {
                    list.add(getTitle(rs));
                }while(rs.next());
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Title selectTitleByNo(Title title) {
        String sql = "SELECT TITLE_NO, TITLE_NAME FROM TITLE WHERE TITLE_NO = ?";
        try(Connection con = JdbcUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, title.getNo());
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    return getTitle(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int insertTitle(Title title) {
        String sql = "INSERT INTO TITLE values(?, ?)";
        try(Connection con = JdbcUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, title.getNo());
            pstmt.setString(2, title.getName());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateTitle(Title title) {
        String sql = "UPDATE TITLE SET TITLE_NAME = ? WHERE TITLE_NO = ?";
        try(Connection con = JdbcUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, title.getName());
            pstmt.setInt(2, title.getNo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteTitle(Title title) {
        String sql = "DELETE FROM TITLE WHERE TITLE_NO = ?";
        try(Connection con = JdbcUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)){
            
            pstmt.setInt(1, title.getNo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Title getTitle(ResultSet rs) throws SQLException {
        int no = rs.getInt("TITLE_NO");
        String name = rs.getString("TITLE_NAME");
        return new Title(no, name);
    }

    @Override
    public Title selectSameTitleEmployeeByTitleNo(Title title) {
        String sql = "SELECT TITLE_NO, TITLE_NAME, e.EMP_NO, e.EMP_NAME, e.DNO " + 
                     "  FROM TITLE t JOIN EMPLOYEE e ON t.TITLE_NO = e.TNO " + 
                     " WHERE TNO = ?";
        try(Connection con = JdbcUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, title.getNo());
            
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    Title item = getTitle(rs);
                    if (rs.getInt("EMP_NO") != 0) {
                        List<Employee> list = new ArrayList<>();
                        do {
                            list.add(getEmployee(rs));
                        }while(rs.next());
                        item.setList(list);
                    }
                    return item;
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Employee getEmployee(ResultSet rs) throws SQLException {
        Employee emp = new Employee();
        emp.setNo(rs.getInt("EMP_NO"));
        emp.setName(rs.getString("EMP_NAME"));
        emp.setDno(new Department(rs.getInt("DNO")));
        return emp;
    }
}
