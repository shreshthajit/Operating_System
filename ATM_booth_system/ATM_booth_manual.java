
import java.util.logging.Level;
import java.util.logging.Logger;

class Bank_1 {

    private int Bank_balance = 100000;
    private boolean check = false;

    public void withDraw(int amount){

        while(check){

            System.out.println(Thread.currentThread().getName()+"   Wait the system is busy now");
        }
        check = true;

        System.out.println(Thread.currentThread().getName()+"  going to withdraw...");
        System.out.println("Current Bank's balance is "+Bank_balance);
        if(Bank_balance < amount){
            System.out.println("Current Bank's balance too low");
        }
        Bank_balance -= amount;

        System.out.println("withdraw completed...");
        System.out.println(Thread.currentThread().getName()+"   Current Bank's balance is after withdraw "+Bank_balance);

        check = false;
    }

}
 class Customer extends Thread {

    Bank bank;
    int amount;
    public Customer(int amount, Bank bank){
        this.amount = amount;
        this.bank = bank;
    }

    @Override
    public void run(){
        bank.withDraw(amount);
    }
}

public class ATM_booth_manual {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Customer customer1 = new Customer(1000,bank);
        Customer customer2 = new Customer(1500,bank);
        Customer customer3 = new Customer(500,bank);
        Customer customer4 = new Customer(2000,bank);

        customer1.start();
        customer2.start();
        customer3.start();
        customer4.start();
    }

}
