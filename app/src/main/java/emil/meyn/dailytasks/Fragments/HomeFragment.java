package emil.meyn.dailytasks.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import emil.meyn.dailytasks.Activities.AddTaskActivity;
import emil.meyn.dailytasks.Activities.ListOfTasks;
import emil.meyn.dailytasks.Activities.MainActivity;
import emil.meyn.dailytasks.Activities.StartDailyTasks;
import emil.meyn.dailytasks.Activities.TaskDetails;
import emil.meyn.dailytasks.R;
import emil.meyn.dailytasks.adapters.TaskAdapter;
import emil.meyn.dailytasks.adapters.onClickItemListener;
import emil.meyn.dailytasks.models.Task;
import emil.meyn.dailytasks.viewmodels.MainActivityViewModel;

public class HomeFragment extends Fragment implements onClickItemListener {

    private static final String TAG = "HomeFragment TAG";
    static final String TASK_NAME = "emil.meyn.dailytasks.TASK_NAME";
    RecyclerView mTasklist;
    TaskAdapter mTaskAdapter;
    MainActivityViewModel mainActivityViewModel;
    Button taskDetailsButton;
    Button startButton;
    FloatingActionButton floatingActionButton;
    BottomNavigationView bottomNavigationView;

    DrawerLayout drawerLayout;
    CoordinatorLayout coordinatorLayout;
    NavigationView navigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mTasklist = v.findViewById(R.id.recyclerViewToday);
        taskDetailsButton = v.findViewById(R.id.button_seeDetails);
        startButton = v.findViewById(R.id.startTimerButton);
        floatingActionButton = v.findViewById(R.id.addTask);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask(v);
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer(v);
            }
        });

        /*
        taskDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskDetails(v);
            }
        });
        */

        initRecyclerView();

        // This provides an instance of the viewmodel. The .of tell the scoop of the viewmodel instance. The .get tells which instance to make.
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        // .observe is a LiveData method, owner is this activity, second is an observer which in this case is an anonomous class.
        mainActivityViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                //Update the recyclerview.
                mTaskAdapter.setTaskList(tasks);

            }
        });

        return v;
    }


    public void startTimer(View v) {
        Intent intent = new Intent(getActivity().getApplicationContext(), StartDailyTasks.class);
        startActivity(intent);
    }

    public void taskDetails(View v) {
        Intent intent = new Intent(getActivity().getApplicationContext(), TaskDetails.class);
        //intent.putExtra("taskName", ;
        startActivity(intent);
    }

    public void addTask(View v) {
        Log.i(TAG, "addTask ran");
        Context context = getContext();
        Class destination = AddTaskActivity.class;
        // Making a variable for the text data:
        Intent intent = new Intent(context, destination);
        startActivity(intent);
    }


    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView ran");
        mTasklist.hasFixedSize();
        mTasklist.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        mTaskAdapter = new TaskAdapter(this);

        mTasklist.setAdapter(mTaskAdapter);

    }

    @Override
    public void onItemClick(int itemClickedIndex) {
        Intent intent = new Intent(getActivity().getApplicationContext(), TaskDetails.class);
        intent.putExtra(MainActivity.TASK_NAME, mTaskAdapter.getName(itemClickedIndex));
        startActivity(intent);
    }

    // Inner Classes for onClickListeners
}
