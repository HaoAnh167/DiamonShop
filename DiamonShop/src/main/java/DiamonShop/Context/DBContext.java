//package DiamonShop.Context;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//import com.microsoft.sqlserver.jdbc.SQLServerDriver;
//
//public class DBContext {
//    private static String dbPrefix = "jdbc:mysql://localhost";
//    private static String user = "root";
//    private static String pass = "hao12471";
//    private static String dbPort = "3306";
//    private static String databaseName = "diamonshop";
//
//    public Connection getConnection() {
//        Connection conn = null;
//        String dbURL = dbPrefix + ":" + dbPort + ";" + "databaseName=" + databaseName  ;
//        try {
//            DriverManager.registerDriver(new SQLServerDriver());
//            conn = DriverManager.getConnection(dbURL, user, pass);
//            System.out.println("Oki bae <3");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("U noob!");
//        }
//        return conn;
//    }
//    
//    public static void main(String[] args) {
//		System.out.println(new DBContext().getConnection());
//	}
//}
//
//
//
//
