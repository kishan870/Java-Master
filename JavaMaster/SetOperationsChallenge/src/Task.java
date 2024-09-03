import java.sql.SQLData;

enum Status {
    IN_QUEUE, ASSIGNED, IN_PROGRESS;

    @Override
    public String toString() {
        return this.name().replace("_", " ");
    }
}

enum Priority {
    HIGH, MEDIUM, LOW
}

public class Task implements Comparable<Task> {

    private String project;
    private String description;
    private String assignee;
    private Status status;
    private Priority priority;

    public Task(String project, String assignee, String description, Priority priority, Status status) {
        this.project = project;
        this.assignee = assignee;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public Task(String project, String description, String assignee, Priority priority) {
        this(project, description, assignee, priority,
                assignee == null? Status.IN_QUEUE : Status.ASSIGNED);
    }

    public Task(String project, String description, Priority priority) {
        this(project, description, null, priority);
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "%-20s %-25s %-10s %s".formatted(project, description, priority,
                assignee, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;
        return getProject().equals(task.getProject()) && getDescription().equals(task.getDescription());
    }

    @Override
    public int hashCode() {
        int result = getProject().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }

    @Override
    public int compareTo(Task o) {
        int result = this.getProject().compareTo(o.getProject());

        if(result == 0) {
            result = this.getDescription().compareTo(o.getDescription());
        }

        return result;
    }
}
