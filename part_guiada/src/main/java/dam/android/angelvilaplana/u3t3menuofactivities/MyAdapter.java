package dam.android.angelvilaplana.u3t3menuofactivities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(String activityName);
    }

    private String[] myDataSet;

    private OnItemClickListener listener;

    // Class for each list item: contains only a TextView
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(TextView textView) {
            super(textView);
            this.textView = textView;
        }

        // Sets viewHolder with data
        public void bind(String activityName, final OnItemClickListener listener) {
            this.textView.setText(activityName);

            // Call listener when click on TextView
            this.textView.setOnClickListener(v -> listener.onItemClick(textView.getText().toString()));
        }
    }

    // Constructor to set list data
    MyAdapter(String[] myDataSet, OnItemClickListener listener) {
        this.myDataSet = myDataSet;

        // Set listener for onItemClick
        this.listener = listener;
    }

    // Creates new view item: Layout Manager calls this method
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create item View:
        // Use a simple TextView
        // predefined layout (sdk/platforms/android-xx/data/res/layout)
        // that contains only TextView
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).
                        inflate(android.R.layout.simple_list_item_1, parent, false);

        return new MyViewHolder(tv);
    }

    // Replaces the data content of a viewholder: Layout Manager calls this method
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
        // Gets data from dataset at: position
        viewHolder.bind(myDataSet[position], listener);
    }

    // Returns the size of dataSet: Layout Manager calls this method
    @Override
    public int getItemCount() {
        return myDataSet.length;
    }

}
