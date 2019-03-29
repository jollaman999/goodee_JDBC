package jdbc;

/*
교수 테이블을 읽어서 교수번호, 교수이름, 학과코드, 급여, 보너스를 출력하기
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcEx2 {
    public static void main(String[] args) throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection
                ("jdbc:mariadb://127.0.0.1:3306/bigdb", "scott", "tiger");
        Statement statement = conn.createStatement();

        ResultSet rs = statement.executeQuery
                ("select no, name, deptno, salary, ifnull(bonus, 0) bonus, hiredate" +
                        " from professor order by name");
        while (rs.next()) {
            int i = 0;
            System.out.print("교수 번호 : " + rs.getString(++i));
            System.out.print(", 교수 이름 : " + rs.getString(++i));
            System.out.print(", 학과 코드 : " + rs.getString(++i));
            System.out.print(", 급여 : " + rs.getString(++i));
            System.out.print(", 보너스 : " + rs.getString(++i));
            System.out.print(", 입사일 : " + rs.getString(++i));
            System.out.println(", 입사일 : " + rs.getTimestamp(i));
        }
    }
}
