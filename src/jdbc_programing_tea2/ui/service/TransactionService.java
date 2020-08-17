package jdbc_programing_tea2.ui.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc_programing_tea3.conn.JdbcUtil;
import jdbc_programing_tea3.dto.Department;
import jdbc_programing_tea3.dto.Title;

public class TransactionService {

    public void transAddTitleAndDepartment(Title title, Department dept) {
        String tSql = "INSERT INTO TITLE VALUES(?, ?)";
        String dSql = "INSERT INTO DEPARTMENT VALUES(?, ?, ?)";
        Connection con = null;
        try {
            con = JdbcUtil.getConnection();
            con.setAutoCommit(false);
            System.out.println(con.getAutoCommit());
            try(PreparedStatement tPstmt = con.prepareStatement(tSql)){
                tPstmt.setInt(1, title.getNo());
                tPstmt.setString(2, title.getName());
                tPstmt.executeUpdate();
            }
            
            try(PreparedStatement dstmt = con.prepareStatement(dSql)){
                dstmt.setInt(1, dept.getNo());
                dstmt.setString(2, dept.getName());
                dstmt.setInt(3, dept.getFloor());
                dstmt.executeUpdate();
//                System.out.println("예외 발생");
//                throw new SQLException("예외 발생 되었음");
            }
            con.commit();
            con.setAutoCommit(true);
            System.out.println(con.getAutoCommit());
        } catch (SQLException e) {
            processRollback(con, e);
        }
    }

    private void processRollback(Connection con, SQLException e) {
        try{
            System.out.println("rollback");
            con.rollback();
            con.setAutoCommit(true);
            System.out.println(con.getAutoCommit());
        }catch(SQLException ee){
            throw new RuntimeException(ee);
        }
        throw new RuntimeException(e);
    }
    
    public void transRemoveTitleAndDepartment(Title title, Department dept) {
        String tSql = "DELETE FROM TITLE WHERE TITLE_NO = ?";
        String dSql = "DELETE FROM DEPARTMENT WHERE DEPT_NO = ?";
        
        Connection con = null;
        try {
            con = JdbcUtil.getConnection();
            con.setAutoCommit(false);
            System.out.println(con.getAutoCommit());
            try(PreparedStatement tPstmt = con.prepareStatement(tSql)){
                tPstmt.setInt(1, title.getNo());
                tPstmt.executeUpdate();
            }
            
            try(PreparedStatement dstmt = con.prepareStatement(dSql)){
                dstmt.setInt(1, dept.getNo());
                dstmt.executeUpdate();
//                System.out.println("예외 발생");
//                throw new SQLException("예외 발생 되었음");
            }
            con.commit();
            con.setAutoCommit(true);
            System.out.println(con.getAutoCommit());
        } catch (SQLException e) {
            processRollback(con, e);
        }
    }
}
