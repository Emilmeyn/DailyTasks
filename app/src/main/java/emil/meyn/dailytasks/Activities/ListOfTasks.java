package emil.meyn.dailytasks.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import emil.meyn.dailytasks.R;
import emil.meyn.dailytasks.adapters.TaskAdapter;
import emil.meyn.dailytasks.adapters.onClickItemListener;
import emil.meyn.dailytasks.models.Task;
import emil.meyn.dailytasks.viewmodels.MainActivityViewModel;

public class ListOfTasks extends AppCompatActivity implements onClickItemListener {

    // TODO QUESTIONS Should all different activities have their own adapters?
    private final static String TAG = "List of Tasks";
    RecyclerView mTasklist;
    TaskAdapter mTaskAdapter;
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_tasks);

        mTasklist = findViewById(R.id.taskList_recycler_view);
        initRecyclerView();

        // testing if the tasklist works
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                //Update the recyclerview.
                mTaskAdapter.setTaskList(tasks);

            }
        });

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView ran");
        mTasklist.hasFixedSize();
        mTasklist.setLayoutManager(new LinearLayoutManager(this));

        mTaskAdapter =  new TaskAdapter(this);

        mTasklist.setAdapter(mTaskAdapter);

    }

    @Override
    public void onItemClick(int itemClickedIndex) {
        Intent intent = new Intent(getApplicationContext(), TaskDetails.class);
        intent.putExtra("name", mTaskAdapter.getName(itemClickedIndex));
        startActivity(intent);
    }
}
