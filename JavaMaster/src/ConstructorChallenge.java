public class ConstructorChallenge {
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.displayResult();

        Customer customer1 = new Customer("Mark", "mark@gmail.com", 5235.67);
        customer1.displayResult();

        Customer customer2 = new Customer("Mark", "mark@hotmail.com");
        customer2.displayResult();
    }
}
