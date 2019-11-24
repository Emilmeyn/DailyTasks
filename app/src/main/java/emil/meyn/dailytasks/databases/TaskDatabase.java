package emil.meyn.dailytasks.databases;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteDatabase;

import emil.meyn.dailytasks.daos.*;
import emil.meyn.dailytasks.models.*;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase{

    private static TaskDatabase instance;

    // Room generates subclass implementing the Dao method.
    public abstract TaskDao taskDao();

    // synchronized means that only one thread can run the database - ensuring that another thread doesn't create another db
    public static synchronized TaskDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), TaskDatabase.class, "task_databse")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    // This is used for the methods onCreate (Prepopulating the db) and onOpen (on Open functions
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDatabaseAsyncTask(instance).execute();
        }


    };

    // This is used for initial db inputs.
    private static class PopulateDatabaseAsyncTask extends AsyncTask<Void, Void, Void>{
        private TaskDao taskDao;

        private PopulateDatabaseAsyncTask(TaskDatabase db){
            taskDao = db.taskDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.insert(new Task("Eat", 15, "Uh, a description"));
            taskDao.insert(new Task("School", 120));
            taskDao.insert(new Task("Sleep", 300));
            taskDao.insert(new Task("Eat2", 141));
            taskDao.insert(new Task("School2", 41));
            taskDao.insert(new Task("Sleep2", 123));
            return null;
        }
    }
}
