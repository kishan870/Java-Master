//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer(50, true);
        System.out.println("Initial page count: " + printer.getPagesPrinted());

        int pagesPrinted = printer.printPages(5);

        System.out.printf("Current job pages: %d\n Total pages printed: %d\n", pagesPrinted,
                printer.getPagesPrinted());

        pagesPrinted = printer.printPages(11);

        System.out.printf("Current job pages: %d\n Total pages printed: %d\n", pagesPrinted,
                printer.getPagesPrinted());
    }
}