import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("Welcome to Just Do IT.\n" +
                "Enter 'help' for more info on what you can do.");
        runApp();
    }

    public static void runApp(){
        Scanner input = new Scanner(System.in);
        MediatorClass mediatorClass = new MediatorClass();
        UserHelper helper = new UserHelper();

        while (true){
            System.out.println("* What would you like to do today?");
            String userInput = input.nextLine();

            switch (userInput){
                case "add task":
                    mediatorClass.addTask();
                    break;
                case "help":
                    System.out.println(helper.help());
                    break;
                case "update task":
                    mediatorClass.updateTask();
                    break;
                case "delete task":
                    mediatorClass.deleteTask();
                    break;
                case "view all tasks":
                    mediatorClass.getAllTasks();
                    break;
                case "filter by priority":
                    mediatorClass.getTasksFilteredByPriority();
                    break;
                case "filter by due date":
                    mediatorClass.getTasksFilteredByDueDate();
                    break;
                case "Exit":
                    System.out.println("* We hope to see you" +
                            " again soon, bye ^_^!");
                    mediatorClass.getTaskManager()
                            .shutDownScheduledService();
                    System.exit(0);
                    break;
                default:
                    System.out.println("* Incorrect input. Please use 'help' " +
                            "for a list of acceptable commands.");


            }

        }
    }
}
