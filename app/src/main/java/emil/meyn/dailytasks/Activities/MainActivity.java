package emil.meyn.dailytasks.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import emil.meyn.dailytasks.Fragments.HomeFragment;
import emil.meyn.dailytasks.Fragments.MySettings;
import emil.meyn.dailytasks.Fragments.ProcessFragment;
import emil.meyn.dailytasks.Fragments.TaskListFragment;
import emil.meyn.dailytasks.R;
import emil.meyn.dailytasks.adapters.TaskAdapter;
import emil.meyn.dailytasks.adapters.onClickItemListener;
import emil.meyn.dailytasks.viewmodels.MainActivityViewModel;


/**
 * TODO QUESTIONS FOR TEACHER: When TaskRepo is creating a singleton, does the Viewmodel Repo also need to be a Singleton/instance????
 * <p>
 * TODO TASK Make todays task only display some --- which????
 * TODO TASK Make a Starttask page.
 * TODO TASK Make the add task, actually add a task.
 * TODO TASK Make a menu with settings ect.
 * TODO TASK Make a goals page - where all goals are availble to see + able to add a goal.
 * TODO TASK Make an add goal page with functionality
 * TODO TASK Implement goals into the database.
 * TODO TASK Check if all of the requirements are in the app.
 */

public class MainActivity extends AppCompatActivity implements onClickItemListener {

    /**
     * TODO Check which of these are actually needed with the new implementation methods.
     */
    private static final String TAG = "MainActivityTAG";
    RecyclerView mTasklist;
    TaskAdapter mTaskAdapter;
    MainActivityViewModel mainActivityViewModel;
    public static final String TASK_NAME = "emil.meyn.dailytasks.TASK_NAME";
    // IMPORTANT This should be androidX toolbar and not Android
    Toolbar toolbar;
    MenuItemCompat settings;
    BottomNavigationView bottomNavigationView;
    FirebaseUser user;

    private Menu menu;

    DrawerLayout drawerLayout;
    CoordinatorLayout coordinatorLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = FirebaseAuth.getInstance().getCurrentUser();

        // Find items in frame
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        mTasklist = findViewById(R.id.recyclerViewToday);
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        //settings = findViewById(R.id.action_settings);
/*        settings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Fragment preferencefragment = new MySettings();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, preferencefragment).commit();
                return true;
            }
        });
*/


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
        //onCreateOptionsMenu();
        // initiate recyclerview
        //initRecyclerView();

        /*
        // Drawer menu
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
*/

        // This provides an instance of the viewmodel. The .of tell the scoop of the viewmodel instance. The .get tells which instance to make.
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
/*
        // .observe is a LiveData method, owner is this activity, second is an observer which in this case is an anonomous class.
        mainActivityViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                //Update the recyclerview.
                mTaskAdapter.setTaskList(tasks);

            }
        });
        */


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_tasksList:
                    selectedFragment = new TaskListFragment();
                    break;
                case R.id.nav_process:
                    selectedFragment = new ProcessFragment();
                    break;
                /*default:
                    selectedFragment = new HomeFragment();
                    Toast.makeText(getApplicationContext(), "No fragment selected", Toast.LENGTH_LONG).show();*/
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();

            return true;
        }
    };

    /*
    public void startTimer(View v) {
        // Testing the onChanged Listener

    }

    public void taskDetails(View v) {
        Intent intent = new Intent(getApplicationContext(), ListOfTasks.class);
        startActivity(intent);
    }

    public void addTask(View v) {
        Log.i(TAG, "addTask ran");
        Context context = getApplicationContext();
        Class destination = AddTaskActivity.class;
        // Making a variable for the text data:
        Intent intent = new Intent(context, destination);
        startActivity(intent);
    }


    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView ran");
        mTasklist.hasFixedSize();
        mTasklist.setLayoutManager(new LinearLayoutManager(this));

        mTaskAdapter = new TaskAdapter();

        mTasklist.setAdapter(mTaskAdapter);
    } */

    @Override
    public void onItemClick(int index) {
        // implement something for if the onclicklistener needs

        //int taskNumber = itemClickedIndex + 1;
        //Toast.makeText(this, "You clicked item: " + taskNumber, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        this.menu = menu;
        if(user != null){
            menu.findItem(R.id.action_login).setTitle("Account");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                Toast.makeText(this, "You clicked favorite icon", Toast.LENGTH_SHORT).show();
                Intent intentFavorite = new Intent(getApplicationContext(), ListOfTasks.class);
                startActivity(intentFavorite);
                return true;
            case R.id.action_login:
                Intent intentLogin = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(intentLogin);
                return true;
            case R.id.action_settings:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new MySettings()).commit();
                // Should it return true here??
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
