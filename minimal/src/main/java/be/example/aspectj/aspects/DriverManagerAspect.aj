import java.sql.DriverManager;

public aspect DriverManagerAspect {

    pointcut callDriver() : 
     call(* DriverManager.getConnection(..));

    before() : callDriver() {
        System.out.println("---------------------- Opening pandoras box");
    }

}