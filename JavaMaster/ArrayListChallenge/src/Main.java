import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

//Program to work on arraylist, adding and removing items1
public class Main {
    public static void main(String[] args) {
        ArrayList<String> groceryItems = new ArrayList<>();
        int choice;

        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("Enter choice\n0 - shutdown\n1 - add items\n 2-remove items");
            choice = sc.nextInt();
            sc.nextLine();

            if(choice == 0)
                break;

            else if(choice == 1) {
                System.out.println("Enter comma seperated items to add");
                String items = sc.nextLine();
                addList(groceryItems, items);
                System.out.println("Grocery list after adding items (ignored duplicates): " + groceryItems);
            }

            else if(choice == 2) {
                System.out.println("Enter comma seperated items to remove");
                String items = sc.nextLine();
                removeList(groceryItems, items);
                System.out.println("Grocery list after removing items: " + groceryItems);
            }

            else
                System.out.println("Invalid choice");
        }

    }

    public static void addList(ArrayList<String> arrList, String items) {
        String[] itemList = items.split(",");

        for(String item:itemList) {
            item = item.trim();
            if(!arrList.contains(item)) {
                arrList.add(item);
            }
        }

        arrList.sort(Comparator.naturalOrder());
    }

    public static void removeList(ArrayList<String> arrList, String items) {
        String[] itemList = items.split(",");

        for(String item:itemList) {
            item = item.trim();
            arrList.remove(item);
        }

        arrList.sort(Comparator.naturalOrder());
    }

}