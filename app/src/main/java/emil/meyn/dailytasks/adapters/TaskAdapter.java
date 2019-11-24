package emil.meyn.dailytasks.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import emil.meyn.dailytasks.R;
import emil.meyn.dailytasks.models.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    //
    private List<Task> taskList = new ArrayList<>();
    final private onClickItemListener mOnClickItemListener;
    //private Context mContext;

    public TaskAdapter(onClickItemListener onClickItemListener){
        mOnClickItemListener = onClickItemListener;
    }
    /*
    public TaskAdapter(Context context, List<Task> taskList, onClickItemListener OnClickItemListener) {
        this.taskList = taskList;
        this.mOnClickItemListener = OnClickItemListener;
        this.mContext = context;
    }*/

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_list_item_main_activity,parent, false);
        return new ViewHolder(view);
    }

    /**
     * This is where the data from the Java Object into the view of the noteholder.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        holder.name.setText(taskList.get(position).getName());
        holder.time.setText("" + taskList.get(position).getTime());

        holder.description.setText("" + taskList.get(position).getDescription());
    }

    public String getName(int index){
        return taskList.get(index).getName();
    }
    @Override
    public int getItemCount() {
        return taskList.size();
    }

    /**
     * This is used to insert the observed list of tasks to the recyclerview.
     * The notifyDataSetChanged() can be changed to be more specific e.g. notifyItemInserted.
     * @param list
     */
    public void setTaskList(List<Task> list){
        this.taskList = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name;
        TextView time;
        TextView description;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.taskName);
            time = itemView.findViewById(R.id.taskTime);
            description = itemView.findViewById(R.id.taskDescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickItemListener.onItemClick(getAdapterPosition());
        }
    }


}
