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
                .option("url", "jdbc:mysql://localhost:3306/aspect").option("user", "root").option("password", "root");

        Dataset<Row> df = dfr.option("dbtable", "User").load();

        System.out.println(df.count());

    }
}
