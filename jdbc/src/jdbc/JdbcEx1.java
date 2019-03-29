package jdbc;

/*
jdbc
1. java.sql 패키지
2. mariadb 드라이버 선택하기
   class.forName("org.mariadb.jdbc.Driver")
3. 연결을 위한 url 설정
4. Statement 객체
5. ResultSet 객체 결과 받기
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcEx1 {
    public static void main(String[] args) throws Exception {
        // 드라이버 설정하기 : 드라이버 클래스를 클래스영역에 로드
        Class.forName("org.mariadb.jdbc.Driver");
        // Connection 객체 생성하기
        Connection conn = DriverManager.getConnection
                ("jdbc:mariadb://127.0.0.1:3306/bigdb", "scott", "tiger");
        // Statement : sql 명령 전달 객체
        Statement stmt = conn.createStatement();
        // ResultSet : select 구문을 실행한 결과 저장
        // executeQuery : sql 명령 실행
        ResultSet rs = stmt.executeQuery("select * from student");
        while(rs.next()) { // 읽어낸 레코드 1개를 리턴
            System.out.print("학번 : " + rs.getString("studno"));
            System.out.print(", 이름 : " + rs.getString("name"));
            System.out.print(", 학년 : " + rs.getInt("grade"));
            System.out.print(", 전공코드 : " + rs.getString("major1"));
            System.out.println(", 지도교수 : " + rs.getString("profno"));
        }
    }
}
