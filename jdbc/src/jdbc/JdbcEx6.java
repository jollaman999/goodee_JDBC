package jdbc;

/*
학생의 학번, 이름, 키, 몸무게, 자기학년의 최대키, 평균키, 최대 몸무게, 평균 몸무게 출력
 */

import java.sql.*;

public class JdbcEx6 {
    public static void main(String[] args) throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection
                ("jdbc:mariadb://127.0.0.1:3306/bigdb", "scott", "tiger");
//        PreparedStatement preparedStatement = conn.prepareStatement
//                ("select studno, name, height, weight,\n" +
//                        " (select max(s2.height) from student s2 where s1.grade = s2.grade) max_height,\n" +
//                        " (select avg(s2.height) from student s2 where s1.grade = s2.grade) avg_height,\n" +
//                        " (select max(s2.weight) from student s2 where s1.grade = s2.grade) max_weight,\n" +
//                        " (select avg(s2.weight) from student s2 where s1.grade = s2.grade) avg_weight\n" +
//                        "from student s1;");
        PreparedStatement preparedStatement = conn.prepareStatement
                ("select studno, name, height, weight, a.max_height, a.avg_height, a.max_weight, a.avg_weight\n" +
                        "from student s,\n" +
                        " (select grade, max(height) max_height, avg(height) avg_height, max(weight) max_weight, avg(weight) avg_weight\n" +
                        " from student group by grade) a\n" +
                        "where s.grade = a.grade;");

        ResultSet rs = preparedStatement.executeQuery();
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
