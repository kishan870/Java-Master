public class Printer {
    private int tonerLevel;
    private int pagesPrinted;
    private boolean duplex;

    public Printer(int tonerLevel, boolean duplex) {
        if(tonerLevel > 100) {
            this.tonerLevel = 100;
        } else if(tonerLevel < 0) {
            this.tonerLevel = 0;
        }
        else {
            this.tonerLevel = tonerLevel;
        }
        this.pagesPrinted = 0;
        this.duplex = duplex;
    }

    public int addToner(int tonerQuantity) {
        int tempAmount = tonerLevel + tonerQuantity;
        if(tempAmount < 0 || tempAmount > 100) {
            System.out.println("Toner level exceeds 100 percent or falls below zero");
            return -1;
        }

        tonerLevel = tempAmount;
        return tonerLevel;
    }

    public int printPages(int numPages) {
        if(numPages < 0) {
            System.out.println("Invalid argument");
            return -1;
        }

        pagesPrinted += numPages;
        int numSheets;

        if(duplex) {
            numSheets = numPages/2 + (numPages%2);
            System.out.println("This is a duplex printer");
        }

        else {
            System.out.println("This is not a duplex printer.");
            numSheets = numPages;
        }

        System.out.printf(" Number of sheets used = %d\n", numSheets);
        return numSheets;
    }

    public int getPagesPrinted() {
        return pagesPrinted;
    }
}
