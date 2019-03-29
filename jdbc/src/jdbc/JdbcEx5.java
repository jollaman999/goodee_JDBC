package jdbc;

import java.sql.*;

public class JdbcEx5 {
    public static void main(String[] args) throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection
                ("jdbc:mariadb://127.0.0.1:3306/bigdb", "scott", "tiger");
        PreparedStatement pstmt = conn.prepareStatement
                ("select * from dept");
        ResultSet rs = pstmt.executeQuery();
        // ResultSetMetaData : 조회된 정보 저장
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++)
            System.out.print(rsmd.getColumnName(i) + "\t");
        System.out.println();
        System.out.println("========================");
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
                System.out.print(rs.getString(i) + "\t");
            System.out.println();
        }
    }
}
