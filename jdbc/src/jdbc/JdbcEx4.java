package jdbc;

/*
PrepareStatement 객체 사용하기
   => Statement의 하위 인터페이스
      Statement : 명령전달 객체
      값 전달시 ? 파라미터를 사용하여 전달 (권장사항 임)


Statement > PreparedStatement
*/

import java.sql.*;

public class JdbcEx4 {
    public static void main(String[] args) throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection
                ("jdbc:mariadb://127.0.0.1:3306/bigdb", "scott", "tiger");
        String sql =
                "create table temp1 (no int, text varchar(30))";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        int result = pstmt.executeUpdate(); // 실행됨
        System.out.println("temp1 테이블 생성 : " + result);

        sql = "insert into temp1 values (?, ?)";
        pstmt = conn.prepareStatement(sql); // 실행준비
        pstmt.setInt(1, 1); // 첫번째 ?, 값
        pstmt.setString(2, "홍길동");
        result = pstmt.executeUpdate(); // 실행됨
        System.out.println("temp1 레코드 insert1 : " + result);

        pstmt.setInt(1, 2);
        pstmt.setString(2, "김삿갓");
        result = pstmt.executeUpdate();
        System.out.println("temp1 레코드 insert2 : " + result);

        sql = "update temp1 set text = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "임시파일");
        result = pstmt.executeUpdate(); // 실행됨
        System.out.println("temp1 레코드 변경 : " + result);

        sql = "select * from temp1";
        pstmt = conn.prepareStatement(sql);

        ResultSet rs  = pstmt.executeQuery(sql);
        while (rs.next()) {
            System.out.print("no = " + rs.getString(1));
            System.out.println(", text = " + rs.getString(2));
        }
        sql = "drop table temp1";
        result = pstmt.executeUpdate(sql);
        System.out.println("temp1 테이블 제거 : " + result);
    }
}
