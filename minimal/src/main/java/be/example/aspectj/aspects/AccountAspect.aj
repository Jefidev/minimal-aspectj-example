import be.example.aspectj.Account;

public aspect AccountAspect {
    final int MIN_BALANCE = 10;

    pointcut callWithDraw(int amount, Account acc) : 
     call(boolean Account.withdraw(int)) && args(amount) && target(acc);

    before(int amount, Account acc) : callWithDraw(amount, acc) {
        System.out.println("Aspect before");
    }

}