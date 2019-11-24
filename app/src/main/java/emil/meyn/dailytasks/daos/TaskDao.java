package emil.meyn.dailytasks.daos;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

import emil.meyn.dailytasks.models.Task;

@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    // Doesn't take account for tasks with the same name but a different id.
    @Query("SELECT * FROM task_table WHERE name = :name")
    Task getTask(String name);

    @Query("DELETE FROM task_table")
    void deleteAllTasks();

    // Returned data as LiveData to get implementations throughout the Viewmodel, Views and Models
    @Query("SELECT * FROM task_table ORDER BY doneToday DESC")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM task_table")
    List<Task> getListTasks();
}




/**
 * Taken from exercises 6. Dao not an interface but a class

private MutableLiveData<List<Task>> allTasks;
private static TaskDao instance;

private TaskDao(){
    allTasks = new MutableLiveData<>();
    List<Task> currentTask = new ArrayList<>();
    allTasks.setValue(currentTask);
}

public static TaskDao getInstance(){
    if(instance == null){
        instance = new TaskDao();
    }
    return instance;
}
 public LiveData<List<task>> getAllTasks() {
 return allTasks;
 }

 public void insert(Task task) {
 List<Task> currentNotes = allTask.getValue();
 currentNotes.add(note);
 allNotes.setValue(currentNotes);
 }

 public void deleteAllTask() {
 List<Task> newList = new ArrayList<>();
 allTasks.setValue(newList);
 }
}
 */


