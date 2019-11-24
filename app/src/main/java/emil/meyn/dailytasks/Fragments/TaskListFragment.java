package emil.meyn.dailytasks.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import emil.meyn.dailytasks.Activities.TaskDetails;
import emil.meyn.dailytasks.R;
import emil.meyn.dailytasks.ServiceGenerator;
import emil.meyn.dailytasks.TaskAPI;
import emil.meyn.dailytasks.TaskResponse;
import emil.meyn.dailytasks.adapters.TaskAdapter;
import emil.meyn.dailytasks.adapters.onClickItemListener;
import emil.meyn.dailytasks.models.Task;
import emil.meyn.dailytasks.viewmodels.MainActivityViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskListFragment extends Fragment implements onClickItemListener {



    private final static String TAG = "List of Tasks Fragment";
    RecyclerView mTasklist;
    TaskAdapter mTaskAdapter;
    MainActivityViewModel viewModel;
    TextView responseCode_TextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tasklist, container, false);

        mTasklist = v.findViewById(R.id.taskList_recycler_view);
        responseCode_TextView = v.findViewById(R.id.API_feedBack);


        initRecyclerView();
// This provides an instance of the viewmodel. The .of tell the scoop of the viewmodel instance. The .get tells which instance to make.
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);


        // .observe is a LiveData method, owner is this activity, second is an observer which in this case is an anonomous class.
        viewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                //Update the recyclerview.
                mTaskAdapter.setTaskList(tasks);

            }
        });
        //requestTask("test");

        return v;
    }

    public void requestTask(String taskName){
        TaskAPI taskAPI = ServiceGenerator.getTaskAPI();
        Call<TaskResponse> call = taskAPI.getTask();
        call.enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                if(!response.isSuccessful()){
                    responseCode_TextView.setText("Code: " + response.code());
                    Log.i("Response code", " " + response.code());
                    Log.i("Response", " " + call.request().url().toString());
                    return;
                }
                Task newTask = response.body().getTask();
                responseCode_TextView.setText("Code: " + response.code());
                Log.i("Response code", " " + response.code());
                Log.i("Response", " " + response.body().toString());
                viewModel.insert(newTask);
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {
                responseCode_TextView.setText(t.getMessage());
                Log.i("Error Message", " " + t.getStackTrace());
                //Log.i("Response", " " + response.body().toString());
                //responseCode_TextView.append("\n" + );
            }
        });
    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView ran");
        mTasklist.hasFixedSize();
        mTasklist.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        mTaskAdapter = new TaskAdapter(this);

        mTasklist.setAdapter(mTaskAdapter);

    }

    @Override
    public void onItemClick(int itemClickedIndex) {
        Intent intent = new Intent(getActivity().getApplicationContext(), TaskDetails.class);
        intent.putExtra("name", mTaskAdapter.getName(itemClickedIndex));
        startActivity(intent);
    }
}
