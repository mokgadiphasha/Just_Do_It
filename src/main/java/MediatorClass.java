import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * I am a mediator class that performs actions based on what the Application
 * class wants me to do, I gather enough information to perform these actions
 * and allow for the specific action to take place.
 *
 * */
public class MediatorClass {
    private Scanner input = new Scanner(System.in);

    private TaskManager taskManager = new TaskManager();

    public TaskManager getTaskManager() {
        return taskManager;
    }


    public void addTask(){
        System.out.println("* To add a task , answer the following" +
                " questions: \n");

        Task task = taskBuilder();

        if(task != null){
            taskManager.addTask(task);
            System.out.println("* Task info: \n" + task);
            System.out.println("* Task has been added.\n");
        } else{
            System.out.println("* Task can't be added.\n");
        }

    }


    public void updateTask(){
        System.out.println("* To update a task , Enter the id of " +
                "the task you wish to update in the displayed tasks below.\n");

        getAllTasks();
        String id = input.nextLine();

        System.out.println("* Provide entries for the following required " +
                "fields.");
        Task task = taskBuilder();

        try {
            if(task != null){
                taskManager.updateTask(id, task);
                System.out.println("* Task updated.\n");
            } else{
                System.out.println("* Task can't be updated.\n");
            }

        } catch (CustomException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteTask(){
        System.out.println("* To delete a task , Enter the id of " +
                "the task you wish to update in the displayed tasks below.\n");
        getAllTasks();
        String id = input.nextLine();

        try{
            taskManager.deleteTask(id);
            System.out.println("Task deleted.");
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }
    }


    public void getTasksFilteredByPriority(){
            List<Task> tasks = taskManager.getTasksByPriority();

            if(!tasks.isEmpty()){
                for (Task task : tasks){
                    System.out.println(task + "\n");
                }
            } else{
                System.out.println("* You have no tasks.\n");
            }
    }


    public void getTasksFilteredByDueDate(){
        List<Task> tasks = taskManager.getTaskByDueDate();

        if(!tasks.isEmpty()){
            for (Task task : tasks){
                System.out.println(task + "\n");
            }
        } else{
            System.out.println("* You have no tasks.\n");
        }
    }


    public void getAllTasks(){
        List<Task> tasks = taskManager.listAllTasks();

        if(!tasks.isEmpty()){
            for (Task task: tasks){
                System.out.println(task + "\n");
            }
        } else {
            System.out.println("* You have no tasks.\n");
        }

    }


    public Task taskBuilder(){
        System.out.println("* Please enter a title for the task: ");
        String title = input.nextLine();

        System.out.println("* Please enter a description for the task: ");
        String description = input.nextLine();

        System.out.println("* Please enter a priority level" +
                " e.g (HIGH,LOW,MILD) for the task: ");
        String priority = input.nextLine();

        Task task = null;
        if (priority.equalsIgnoreCase("HIGH")){
            task = new Task(description,title,Priority.HIGH, LocalDate.now());
        } else if (priority.equalsIgnoreCase("MILD")){
            task = new Task(description,title,Priority.MILD, LocalDate.now());
        } else if (priority.equalsIgnoreCase("LOW")) {
            task = new Task(description,title,Priority.LOW, LocalDate.now());
        }
        return task;
    }






}
