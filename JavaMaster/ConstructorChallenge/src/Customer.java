public class Customer {
    private String name;
    private double credit_limit;
    private String email;

    public Customer(String name, String email, double credit_limit) {
        this.name = name;
        this.email = email;
        this.credit_limit = credit_limit;
    }

    public Customer() {
        this("default name", "default email", 1.0);
        System.out.println("Empty constructor called");
    }

    public Customer(String name, String email) {
        this(name, email, 1.0);
        System.out.println("Constructor with two args called");
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getCredit_limit() {
        return credit_limit;
    }

    public void displayResult() {
        System.out.println("Name-" + name + ", Email-" + email + ", Credit Limit-" + credit_limit);
    }
}
