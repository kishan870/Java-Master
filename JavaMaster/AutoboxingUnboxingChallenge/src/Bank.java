import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Customer> customers = new ArrayList<>(5000);

    public Bank(String name) {
        this.name = name;
    }

    private Customer getCustomer(String customerName) {
        for(var customer: customers) {
            if(customer.name().equalsIgnoreCase(customerName)) {
                return customer;
            }
        }

        System.out.printf("Customer %s wasn't found %n", customerName);
        return null;
    }

    public void addNewCustomer(String customerName, double initialDeposit) {
        if(getCustomer(customerName) == null) {
            customers.add(new Customer(customerName, initialDeposit));
            System.out.printf("Customer %s added successfully %n", customerName);
            return;
        }

        System.out.printf("Customer %s already exists %n", customerName);
    }

   public void addTransaction(String customerName, double transactionAmount) {
        Customer customer = getCustomer(customerName);

        if(customer != null) {
            customer.transactions().add(transactionAmount);
        }
   }

    public void printStatement(String customerName) {
       Customer customer = getCustomer(customerName);

       if(customer == null) {
           return;
       }

       System.out.println("-".repeat(30));
       System.out.println("Customer Name: " + customer.name());
       System.out.println("Transactions: ");

       for(double d: customer.transactions()) {
           System.out.printf("$%10.2f (%s)%n", d, d < 0? "Debit" : "Credit");
       }
   }
}
