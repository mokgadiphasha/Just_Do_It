import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TestTaskManager {

    TaskManager taskManager;

    @BeforeEach
    public void before(){
        taskManager = new TaskManager();
    }

    @Test
    public void testAddTask(){
        taskManager.addTask(new Task("Running the first add task test.",
                "test  tasks",Priority.HIGH, LocalDate.now()));
        taskManager.addTask(new Task("Running second add task test.",
                "test task 2.0",Priority.MILD,LocalDate.now()));

        assertEquals(2,taskManager
                .listAllTasks().size(),"List should have 2 elements in it.");

    }

    @Test
    public void testUpdateTask() throws CustomException {
        taskManager.addTask(new Task("Running test to update task.",
                "update tasks",Priority.MILD,LocalDate.now()));

        Task taskFromList = taskManager.listAllTasks().get(0);
        String id = taskFromList.getId().toString();
        Task task = new Task("Creating tests to update an existing task",
                "update task",Priority.LOW,LocalDate.now());

        taskManager.updateTask(id,task);

        Task updatedTask = taskManager.listAllTasks().get(0);

        assertEquals(id,updatedTask.getId().toString());
        assertEquals(taskFromList.getDueDate(),task.getDueDate());
        assertNotEquals(taskFromList.getDescription(),task.getDescription());
        assertNotEquals(taskFromList.getTitle(),task.getTitle());
        assertNotEquals(taskFromList.getPriority(),task.getPriority());
    }

    @Test
    public void testDeleteTask() throws CustomException {
        taskManager.addTask(new Task("Running test to delete task.",
                "update tasks",Priority.MILD,LocalDate.now()));
        Task taskFromList = taskManager.listAllTasks().get(0);

        taskManager.deleteTask(taskFromList.getId().toString());

        assertEquals(0,taskManager.listAllTasks().size());

    }

    @Test
    public void testGetTasksByPriority(){
        taskManager.addTask(new Task("Running test to prioritize tasks.",
                "prioritize tasks",Priority.HIGH,LocalDate.now()));
        taskManager.addTask(new Task("Running test to prioritize tasks.",
                "prioritize tasks",Priority.MILD,LocalDate.now()));
        taskManager.addTask(new Task("Running test to prioritize tasks.",
                "prioritize tasks",Priority.LOW,LocalDate.now()));
        taskManager.addTask(new Task("Running test to prioritize tasks.",
                "prioritize tasks",Priority.MILD,LocalDate.now()));

        int highCount=0;
        int middleCount=0;
        int lowCount=0;

        for (Task task:taskManager.listAllTasks()){
            if(task.getPriority() == Priority.HIGH){
                highCount+=1;
            } else if (task.getPriority() == Priority.MILD) {
                middleCount+=1;
            } else if(task.getPriority() == Priority.LOW){
                lowCount+=1;
            }
        }

        assertEquals(highCount,1);
        assertEquals(middleCount,2);
        assertEquals(lowCount,1);

    }

    @Test
    public void testTaskNotUpdated(){
        String id = UUID.randomUUID().toString();
        Task task = new Task("Running test to check " +
                "what happens if a task being updated does not exist tasks.",
                "prioritize tasks",Priority.HIGH,LocalDate.now());
        assertThrows(CustomException.class,() ->{
            taskManager.updateTask(id,task);
        });

    }

    @Test
    public void testTaskNotDeleted(){
        String id = UUID.randomUUID().toString();

        assertThrows(CustomException.class,()->{
            taskManager.deleteTask(id);
        });
    }



}
