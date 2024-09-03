import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Set<Task> allTasks = TaskData.getTasks("All");
        sortAndPrint("All Tasks", allTasks);

        System.out.println("Sorting tasks by priority");
        Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority)
                        .thenComparing(Task::compareTo);

        sortAndPrint("All tasks sorted by priority from high to low", allTasks, sortByPriority);

    }

    public static void sortAndPrint(String header, Collection<Task> collection) {
        sortAndPrint(header, collection, null);
    }

    public static void sortAndPrint(String header, Collection<Task> collection,
                                     Comparator<Task> sorter) {

        String lineSeperator = "-".repeat(90);
        System.out.println(lineSeperator);
        System.out.println(header);
        System.out.println(lineSeperator);

        List<Task> list = new ArrayList<>(collection);
        list.sort(sorter);
        list.forEach(System.out::println);
    }
}