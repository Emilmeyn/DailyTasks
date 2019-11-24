package emil.meyn.dailytasks.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import emil.meyn.dailytasks.Fragments.HomeFragment;
import emil.meyn.dailytasks.R;
import emil.meyn.dailytasks.daos.TaskDao;
import emil.meyn.dailytasks.models.Task;
import emil.meyn.dailytasks.viewmodels.AddTaskActivityViewModel;
import emil.meyn.dailytasks.viewmodels.MainActivityViewModel;

public class TaskDetails extends AppCompatActivity {

    TextView textView_taskName;
    TextView textView_taskTime;
    TextView textView_taskDescription;
    MainActivityViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        textView_taskDescription = findViewById(R.id.task_details_taskDescriptionTextView);
        textView_taskName = findViewById(R.id.task_details_NameTextView);
        textView_taskTime = findViewById(R.id.task_details_taskTimeTextView);
/*
        viewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                //currentTasks.clear();
                currentTasks.addAll(tasks);

            }
        });
 */
        List<Task> currentTasks = viewModel.getListAllTasks();
        Task currentTask = new Task("test", 0, "testing");



        Bundle bundle = getIntent().getExtras();


        // Maybe this is a bad way to do it, if the list becomes to big.
        if(bundle != null && bundle.containsKey(MainActivity.TASK_NAME)) {
            String taskname = bundle.getString(MainActivity.TASK_NAME);
            Log.i("Ran", "Bundle isn't null " + taskname + " " + currentTasks.toString());
            for (Task task : currentTasks) {
                Log.i("Ran", "For loop ran" + taskname);
                if(task.getName().equals(taskname)){
                    currentTask = task;
                    Log.i("Ran", "If statement" + taskname);
                    break;
                }
            }
        }

        textView_taskName.setText(currentTask.getName());
        textView_taskTime.setText(String.valueOf(currentTask.getTime()));
        textView_taskDescription.setText(currentTask.getDescription());






    }
}
