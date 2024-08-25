import dev.model.LPAStudent;
import dev.model.LPAStudentComparator;
import dev.util.QueryList;

import java.util.Comparator;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        QueryList<LPAStudent> queryList = new QueryList<>();

        for(int i=0; i<25; i++) {
            queryList.add(new LPAStudent());
        }

        System.out.println("Ordered");
        queryList.sort(Comparator.naturalOrder());
        printList(queryList);

        System.out.println("\nMatches: ");
        var matches = queryList.getMatches("PercentComplete", "40")
                .getMatches("Course", "Java");

        matches.sort(new LPAStudentComparator());
        printList(matches);

        System.out.println("\nOrdered: ");
        matches.sort(null);
        printList(matches);

    }

    public static void printList(List<?> students) {
        for(var student: students) {
            System.out.println(student);
        }
    }
}