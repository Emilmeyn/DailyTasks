package emil.meyn.dailytasks.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import emil.meyn.dailytasks.R;
import emil.meyn.dailytasks.models.Task;
import emil.meyn.dailytasks.viewmodels.AddTaskActivityViewModel;

public class AddTaskActivity extends AppCompatActivity {

    AddTaskActivityViewModel viewModel;
    EditText editTextName;
    EditText editTextTime;
    EditText editTextDescription;
    TextView name;
    TextView time;
    TextView description;
    TextView content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        //Finding labels and linking them
        editTextName = findViewById(R.id.newTaskName);
        editTextTime = findViewById(R.id.newTaskTime);
        editTextDescription  = findViewById(R.id.newTaskDescription);
        name  = findViewById(R.id.taskName);
        time  = findViewById(R.id.taskTime);
        description  = findViewById(R.id.newTaskDescription);
        content = findViewById(R.id.DisplayContent);



        viewModel = ViewModelProviders.of(this).get(AddTaskActivityViewModel.class);
        // TODO delete this?????
        /*viewModel.getTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                content.setText("");
                for(Task task : tasks){
                    content.append(task.getName() + "\n");
                }
            }
        });*/


    }

    public void saveTask(View v){
        Log.i("Message", "saveTask ran");
        if(editTextName != null){
            int taskTime = Integer.parseInt(editTextTime.getText().toString());
            String taskName = editTextName.getText().toString();
            String taskDescription = editTextDescription.getText().toString();
            Task newTask = new Task(taskName, taskTime, taskDescription);

            viewModel.saveNewTask(newTask);

            finish();
        }

    }
}
