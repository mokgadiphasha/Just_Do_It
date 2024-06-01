import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * I am a TaskManager class, I perform actions on tasks.
 * I add , update , remove and delete tasks.
 * I also sort them based on certain criteria.
 * I also periodically add existing user task data to a Json file.
 * */

public class TaskManager {

    private List<Task> tasks = new ArrayList<>();
    private ScheduledExecutorService scheduledService = Executors
            .newSingleThreadScheduledExecutor();


    public TaskManager(){
        periodicallySaveTasksToFile();
    }


    public void addTask(Task task){
        tasks.add(task);
    }


    public void updateTask(String id,Task updatedTask) throws CustomException {
        Optional<Task> optionalTask = tasks.stream()
                .filter(task-> task.getId().toString().equals(id))
                .findFirst();

        if(optionalTask.isPresent()){
            Task oldTask = optionalTask.get();
            updatedTask.setDueDate(oldTask.getDueDate());
            updatedTask.setId(oldTask.getId());
            tasks.remove(oldTask);
            tasks.add(updatedTask);

        } else{
            throw new CustomException("Sorry, Task cant be updated," +
                    " Task does not exist.");
        }


    }


    public void deleteTask(String id) throws CustomException {
        Optional<Task> optionalTask = tasks.stream()
                .filter(task-> task.getId().toString().equals(id))
                .findFirst();
        Task taskToaDelete= null;

        if(optionalTask.isPresent()){
           taskToaDelete = optionalTask.get();
           tasks.remove(taskToaDelete);
       } else{
           throw new CustomException("Sorry, Task can't be deleted, " +
                   "Task does not exist.");
       }
    }


    public List<Task> listAllTasks(){
        return tasks;
    }


    public List<Task> getTasksByPriority(){
       return tasks.stream().sorted(Comparator.comparing(task -> {
            Priority currentPriority = task.getPriority();

            if(currentPriority == Priority.HIGH) return 0;
            else if (currentPriority == Priority.MILD) return 1;
            else if(currentPriority == Priority.LOW) return 2;
            else return 3;

        })).toList();

    }


    public List<Task> getTaskByDueDate(){
        return tasks.stream().sorted(Comparator
                .comparing(Task::getDueDate)).toList();
    }


    public void periodicallySaveTasksToFile(){

        Runnable task = this::saveTasksToFile;
        scheduledService.scheduleAtFixedRate(task,
                5,5, TimeUnit.MINUTES);
    }


    public void saveTasksToFile(){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                false);
        List<Task> tasksToBeSaved = tasks;

        try{
            File file = new File("Tasks.json");

            if (file.exists()){
                objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValue(file,tasksToBeSaved);
            } else{
                throw new CustomException("File does not exist.");
            }

        } catch (StreamWriteException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }


    }

    public void shutDownScheduledService(){
        scheduledService.shutdown();
    }
}
