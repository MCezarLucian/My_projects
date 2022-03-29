package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {

    private static final Logger log = Logger.getLogger(ConnectionDB.class.getName());
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/schooldb";
    private static final String user = "root";
    private static final String pass = "Manamana1@";
    private static ConnectionDB singleInstance = new ConnectionDB();

    private ConnectionDB(){
        try{
            Class.forName(driver);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private Connection createConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, user, pass);

        } catch (SQLException throwables) {
            log.log(Level.WARNING, "Eroare! Nu s-a reusit conectarea la baza de date!");
            throwables.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection(){
        return singleInstance.createConnection();
    }

    public static void close(Connection connection){
        if(connection != null){
            try {
                connection.close();
            }catch (SQLException throwables){
                log.log(Level.WARNING, "Eroare! Nu s-a putut inchide baza de date!");
            }
        }
    }

    public static void close(Statement statement){
        if( statement != null){
            try{
                statement.close();
            }catch(SQLException throwables){
                log.log(Level.WARNING, "Eroare! Nu s-a putut inchide statement-ul!");
            }
        }
    }

    public static void close(ResultSet resultSet){
        if(resultSet != null){
            try{
                resultSet.close();
            }catch(SQLException throwables){
                log.log(Level.WARNING, "Eroare! Nu s-a putut inchid ResultSet!");
            }
        }
    }

}
