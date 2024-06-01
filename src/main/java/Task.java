import java.time.LocalDate;
import java.util.UUID;

/**
 * I am a task class , I describe what a task is.
 * */
public class Task {
    private String description;
    private Priority priority;
    private LocalDate dueDate;
    private String title;
    private UUID id;


    public Task(String description, String title ,
                Priority priority, LocalDate dueDate){
        this.dueDate = dueDate;
        this.description = description;
        this.priority = priority;
        this.title = title;
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString(){
        return "{Id: " + getId() +
                "\nTitle: " + getTitle()+
                "\nDescription: "+getDescription()+
                "\nPriority: " + getPriority().toString()+
                "\nDue date: " + getDueDate()+ "}";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
