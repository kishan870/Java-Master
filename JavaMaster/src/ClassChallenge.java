public class ClassChallenge {
    public static void main(String[] args) {
        Bank account = new Bank();
        account.setAccountNumber(1234);
        account.setName("Mark");
        account.setAccountBalance(1000);
        account.setEmail("mark@email.com");
        account.setPhoneNumber(354657);

        System.out.println(account.getAccountNumber());
        System.out.println(account.getName());
        System.out.println(account.getEmail());
        System.out.println(account.getPhoneNumber());
        System.out.println(account.getAccountBalance());

        account.displayAccount();

        account.depositFunds(500);
        account.withdrawFunds(-23);
        account.withdrawFunds(3600);
        account.withdrawFunds(800);

        account.displayAccount();
    }
}
