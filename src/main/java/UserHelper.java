
/**
 * I am UserHelper class. I provide information about
 * what the application can do.
 * */


public class UserHelper {

    public String help(){
        return "You can do the following: \n" +
        "* Add tasks by entering 'add task'.\n"+
        "* Updating task by entering 'update task'\n"+
        "* Delete task by entering 'delete task'\n"+
        "* View all tasks by entering 'view all tasks'\n" +
        "* View tasks by priority by entering 'filter by priority'\n" +
        "* View tasks by due date by entering 'filter by due date'\n" +
        "* You can exit the program by entering 'Exit'.\n";
    }
}
