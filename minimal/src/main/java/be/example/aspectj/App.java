package be.example.aspectj;

import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        Account ac = new Account();
        ac.withdraw(100);

        System.out.println(ac.balance);

        SparkSession session = SparkSession.builder().appName("new-name").master("local")
                .config("spark.driver.bindAddress", "127.0.0.1").getOrCreate();

        DataFrameReader dfr = session.sqlContext().read().format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/test").option("user", "root").option("password", "root");

        Dataset<Row> df = dfr.option("dbtable", "apsect").load();

        System.out.println(df.count());
        Connection conn = null;

        try {
            System.out.println("Before explicitly calling the connexion");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?" + "user=root&password=root");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
}
