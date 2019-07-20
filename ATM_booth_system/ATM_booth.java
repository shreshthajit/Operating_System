

import java.util.logging.Level;
import java.util.logging.Logger;

 class Bank{

    private int Bank_balance = 100000;

    synchronized void withDraw(int amount){

        System.out.println("going to withdraw...");
        System.out.println("Current Bank's balance is "+Bank_balance);
        if(Bank_balance < amount){

            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Bank_balance -= amount;

        System.out.println("withdraw completed...");
        System.out.println("Current Bank's balance is "+Bank_balance);

    }

}


class Customer1 extends Thread{
    Bank bank;
    int amount;

    public Customer1(Bank bank, int amount){
        this.bank = bank;
        this.amount = amount;
    }

    public void run(){
        bank.withDraw(amount);
    }
}

public class ATM_booth {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Customer1 customer1 = new Customer1(bank,1000);
        Customer1 customer2 = new Customer1(bank,500);
        Customer1 customer3 = new Customer1(bank,1500);
        Customer1 customer4 = new Customer1(bank,2000);

        customer1.start();
        customer2.start();
        customer3.start();
        customer4.start();
    }
}
