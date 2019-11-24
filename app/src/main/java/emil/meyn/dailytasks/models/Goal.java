package emil.meyn.dailytasks.models;

import androidx.room.*;

import java.util.Date;

@Entity(tableName = "goal_table")
public class Goal {
    @PrimaryKey(autoGenerate = true)
    private String name;
    private int progress;
    private boolean reached;
    private String description;
    private Date startDate;

    public Goal(String name, int progress, boolean reached, String description, Date startDate) {
        this.name = name;
        this.progress = progress;
        this.reached = reached;
        this.description = description;
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isReached() {
        return reached;
    }

    public void setReached(boolean reached) {
        this.reached = reached;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
