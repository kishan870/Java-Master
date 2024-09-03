import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetsChallenge {
    public static void main(String[] args) {

        Set<Task> tasks = TaskData.getTasks("all");
        Set<Task> annsTasks = TaskData.getTasks("Ann");
        Set<Task> bobsTasks = TaskData.getTasks("Bob");
        Set<Task> carolsTasks = TaskData.getTasks("Carol");

        List<Set<Task>> sets = List.of(annsTasks, bobsTasks, carolsTasks);

        Set<Task> assignedTasks = getUnion(sets);
        Main.sortAndPrint("Assigned Tasks", assignedTasks);

        Set<Task> everyTask = getUnion(List.of(tasks, assignedTasks));
        Main.sortAndPrint("The True All Tasks", everyTask);

        Set<Task> missingTasks = getDifference(everyTask, tasks);
        Main.sortAndPrint("Missing Tasks", missingTasks);

        Set<Task> unassignedTasks = getDifference(tasks, assignedTasks);
        Main.sortAndPrint("Unassigned Tasks", unassignedTasks);

    }

    private static Set<Task> getUnion(List<Set<Task>> sets) {
        Set<Task> unionSet = new HashSet<>();
        for(var set: sets) {
            unionSet.addAll(set);
        }
        return unionSet;
    }

    private static Set<Task> getIntersection(Set<Task> setA, Set<Task> setB) {
        Set<Task> intersectionSet = new HashSet<>(setA);
        intersectionSet.retainAll(setB);
        return intersectionSet;
    }

    private static Set<Task> getDifference(Set<Task> setA, Set<Task> setB) {
        Set<Task> differenceSet = new HashSet<>(setA);
        differenceSet.removeAll(setB);
        return differenceSet;
    }
}
