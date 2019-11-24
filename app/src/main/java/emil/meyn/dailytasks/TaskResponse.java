package emil.meyn.dailytasks;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import emil.meyn.dailytasks.models.Task;

public class TaskResponse {
    // id is a STRING in the API
    //private int id;

    @SerializedName("title")
    private String description;

    private int time;

    @SerializedName("slug")
    private String name;

    public Task getTask() {
        return new Task(name, description);
    }
/*
    public List<Task> getTasks(){
        ArrayList<Task> tasks = new ArrayList<>();
        //(name, time, description);
        return tasks;
    }
 */
}
