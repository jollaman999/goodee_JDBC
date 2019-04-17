package jdbc.test;

import java.sql.*;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("에러 : JDBC 드라이버 로드 실패!!");
            e.printStackTrace();
            return;
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection
                    ("jdbc:mariadb://127.0.0.1:3306/bigdb", "scott", "tiger");
        } catch (SQLException e) {
            System.out.println("에러 : mariadb 서버 접속 실패!!");
            e.printStackTrace();
            return;
        }

        System.out.println("mariadb 서버에 접속하였습니다!");
        StringBuilder input;

        loop:
        while (true) {
            System.out.println();
            System.out.print("sql 구문 입력 => ");
            input = new StringBuilder();

            while (true) {
                Scanner scanner = new Scanner(System.in);
                input.append(scanner.nextLine());
                input.append(" ");

                if (input.toString().substring(0, "exit".length()).equalsIgnoreCase("exit")) {
                    System.out.println("서버 접속을 종료합니다!");
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return;
                    }
                    break loop;
                } else if (input.charAt(input.length() - 2) == ';') {
                    break;
                }
            }

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(input.toString());
                ResultSet rs = preparedStatement.executeQuery();
                ResultSetMetaData resultSetMetaData = rs.getMetaData();

                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++)
                    System.out.print(resultSetMetaData.getColumnName(i) + "\t");
                System.out.println();
                System.out.println("====================================");
                while (rs.next()) {
                    for (int i = 1; i < resultSetMetaData.getColumnCount(); i++)
                        System.out.print(rs.getString(i) + "\t");
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
