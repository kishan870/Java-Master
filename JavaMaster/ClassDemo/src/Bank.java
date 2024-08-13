
public class Bank {
    private int accountNumber;
    private int accountBalance=0;
    private String name;
    private String email;
    private int phoneNumber;

    //Creating getters
    public int getAccountNumber() {
        return accountNumber;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void depositFunds(int amount) {
        if(amount < 1) {
            System.out.println("Deposit amount " + amount + " is not valid");
            return;
        }

        accountBalance = accountBalance + amount;
        System.out.println("Successfully deposited amount. Balance is " + accountBalance);

    }

    public void withdrawFunds(int amount) {

        if(amount < 1) {
            System.out.println("Withdrawl amount " + amount + " is not valid");
            return;
        }

        if(amount > accountBalance) {
            System.out.println("Amount greater than account balance. Withdrawl not allowed");
            return;
        }

        accountBalance = accountBalance - amount;
        System.out.println("Successfully withdrawn amount. Current balance is " + accountBalance);
    }

    public  void displayAccount() {
        System.out.println("Account number-" + accountNumber
                            + " Name-" + name
                            + " Email-" + email
                            + "PhoneNumber-" + phoneNumber
                            + " Balance-" + accountBalance);
    }
}
