
//A simple banking simulations to add new customers, and print transaction for selected customer
//Uses autoboxing and unboxing between primitive "double" and its wrapper class "Double"

public class Main {
    public static void main(String[] args) {
//        Customer bob = new Customer("Bob S", 1000);
//        System.out.println(bob);

        Bank bank = new Bank("Chase");
        bank.addNewCustomer("Jane A", 500.0);
        bank.addTransaction("Jane A", -10.25);
        bank.addTransaction("Jane A", -75.21);
        bank.addTransaction("Jane A", 50.22);
        bank.printStatement("Jane A");

        bank.addNewCustomer("Bob S", 100.0);
        bank.addTransaction("Bob S", 100);
        bank.printStatement("Bob S");
    }
}