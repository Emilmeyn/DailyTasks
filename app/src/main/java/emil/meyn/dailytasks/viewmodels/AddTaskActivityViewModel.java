package emil.meyn.dailytasks.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import emil.meyn.dailytasks.models.Task;
import emil.meyn.dailytasks.repositories.TaskRepository;
// TODO: 04/11/2019 Convert from using strings to using Tasks

public class AddTaskActivityViewModel extends AndroidViewModel {
    private MutableLiveData<List<Task>> tasks;
    private List<Task> tasksList;
    private TaskRepository taskRepository;

    public AddTaskActivityViewModel(Application app) {
        super(app);
        taskRepository = new TaskRepository(app);
        /*
        tasks = new MutableLiveData<>();
        List<Task> newList = new ArrayList<>();
        tasks.setValue(newList);
         */
    }

    public LiveData<List<Task>> getTasks() {
        return tasks;
    }

    public List<Task> getListTasks(){
        return tasksList;
    }

    // Watch out for name conventions can become a problem with
    public void saveNewTask(Task task){
        taskRepository.insert(task);
        /*
        List<Task> currentTasks = tasks.getValue();
        currentTasks.add(task);
        tasks.setValue(currentTasks);
         */
    }

    public void deleteTasks(){
    }
}
