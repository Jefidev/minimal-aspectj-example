import java.sql.DriverManager;
import org.apache.spark.sql.Dataset;

public aspect DriverManagerAspect {

    pointcut executeDriver() : 
     call(* DriverManager.getConnection(..));
     //call(* Dataset.withAction(..));

    before() : executeDriver() {
        System.out.println("---------------------- Opening pandoras box");
    }

}