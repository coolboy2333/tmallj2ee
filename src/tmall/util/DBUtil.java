package tmall.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Danger on 2017/10/16.
 */
public class DBUtil {
    static String ip;
    static int port;
    static String database="tmall";
    static String encoding="UTF-8";
    static String loginName="root";
    static String password="admin";

    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        return DriverManager.getConnection(url, loginName, password);
    }

    public static void main(String[] args) throws SQLException{
        System.out.println(getConnection());
    }
}
