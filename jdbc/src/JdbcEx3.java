/*
select 구문 : ResultSet executeQuery()
그외 sql 구문 : int executeUpdate()
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcEx3 {
    public static void main(String[] args) throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection
                ("jdbc:mariadb://127.0.0.1:3306/bigdb", "scott", "tiger");
        Statement stmt = conn.createStatement();
        String sql =
                "create table temp1 (no int, text varchar(30))";
        int result = stmt.executeUpdate(sql);
        System.out.println("temp1 테이블 생성 : " + result);
        sql = "insert into temp1 values(1, '홍길동')";
        result = stmt.executeUpdate(sql);
        System.out.println("temp1 레코드 insert1 : " + result);
        sql = "insert into temp1 values(2, '김삿갓')";
        result = stmt.executeUpdate(sql);
        System.out.println("temp1 레코드 insert2 : " + result);
        result = stmt.executeUpdate("update temp1 set text ='임시파일'");
        System.out.println("temp1 레코드 변경 : " + result);
        sql = "select * from temp1";

        ResultSet rs  = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.print("no = " + rs.getString(1));
            System.out.println(", text = " + rs.getString(2));
        }
        sql = "drop table temp1";
        result = stmt.executeUpdate(sql);
        System.out.println("temp1 테이블 제거 : " + result);
    }
}
