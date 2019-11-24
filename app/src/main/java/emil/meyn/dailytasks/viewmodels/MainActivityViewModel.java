package emil.meyn.dailytasks.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import emil.meyn.dailytasks.repositories.TaskRepository;
import emil.meyn.dailytasks.models.Task;

public class MainActivityViewModel extends AndroidViewModel {


    private TaskRepository taskRepository;

    public MainActivityViewModel(@NonNull Application app){
        super(app);
        taskRepository = new TaskRepository(app);
    }

    public void insert(Task task){
        taskRepository.insert(task);
    }

    public void update(Task task){
        taskRepository.update(task);
    }

    public void delete(Task task){
        taskRepository.delete(task);
    }

    public void deleteAll(){
        taskRepository.deleteAllTasks();
    }

    public LiveData<List<Task>> getAllTasks(){
        return taskRepository.getAlltasks();
    }

    public List<Task> getListAllTasks() {return taskRepository.getListAllTasks(); }


    /**
     * With the new initilation maybe this is not needed.?

    public void init(){
        if(tasks != null) {
            return;
        }
        taskRepository.getInstance();
        tasks = taskRepository.getTasks();
    }
     */
}
