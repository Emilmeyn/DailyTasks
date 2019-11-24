package emil.meyn.dailytasks.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import emil.meyn.dailytasks.daos.TaskDao;
import emil.meyn.dailytasks.databases.TaskDatabase;
import emil.meyn.dailytasks.models.Task;

public class TaskRepository {
    /**
     *  Singleton Pattern
     */
    private static TaskRepository instance;
    private TaskDao taskDao;
    private LiveData<List<Task>> alltasks;
    private List<Task> listAllTasks;

    // Application makes sure there is a context for the database
    public TaskRepository(Application app){
        TaskDatabase database = TaskDatabase.getInstance(app);
        taskDao = database.taskDao();
        alltasks = taskDao.getAllTasks();
        listAllTasks = taskDao.getListTasks();
    }

    // Inserting all the database operations with AsynsTasks
    public void insert(Task task){
        new InsertTaskAsyncTask(taskDao).execute(task);
    }

    public void update(Task task){
        new UpdateTaskAsyncTask(taskDao).execute(task);
    }

    public void delete(Task task){
        new DeleteTaskAsyncTask(taskDao).execute(task);
    }

    public void deleteAllTasks(){
        new DeleteAllTaskAsyncTask(taskDao).execute();
    }

    public LiveData<List<Task>> getAlltasks() {
        return alltasks;
    }

    public List<Task> getListAllTasks() {return listAllTasks; }

    // Inner class to create Async db operation ---> a class for each operation???
    // TODO Ask about the way to do less async classes??
    // Static to avoid a reference to the repository creating memory leak.
    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void>{

        private TaskDao taskDao;

        public InsertTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insert(tasks[0]);
            return null;
        }
    }

    private static class UpdateTaskAsyncTask extends AsyncTask<Task, Void, Void>{

        private TaskDao taskDao;

        public UpdateTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.update(tasks[0]);
            return null;
        }
    }
    private static class DeleteTaskAsyncTask extends AsyncTask<Task, Void, Void>{

        private TaskDao taskDao;

        public DeleteTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.delete(tasks[0]);
            return null;
        }
    }
    private static class DeleteAllTaskAsyncTask extends AsyncTask<Void, Void, Void>{

        private TaskDao taskDao;

        public DeleteAllTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.deleteAllTasks();
            return null;
        }
    }
}
