package emil.meyn.dailytasks.models;

import androidx.room.*;

import emil.meyn.dailytasks.models.Goal;

@Entity(tableName = "task_table")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int time;
    private String description;
    //private Goal goal;
    private boolean doneToday;


    public Task(String name, int time, String description) {
        this.name = name;
        this.time = time;
        this.description = description;
    }

    @Ignore
    public Task(String name, String description){
        this.name = name;
        this.time = 10;
        this.description = description;
    }

    @Ignore
    public Task(String name, int time) {
        this.name = name;
        this.time = time;
        description = "not null";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 /*   public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }*/

    public boolean isDoneToday() {
        return doneToday;
    }

    public void setDoneToday(boolean doneToday) {
        this.doneToday = doneToday;
    }
}
