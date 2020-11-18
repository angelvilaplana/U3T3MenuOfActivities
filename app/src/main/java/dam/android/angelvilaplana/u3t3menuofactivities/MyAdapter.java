package dam.android.angelvilaplana.u3t3menuofactivities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import dam.android.angelvilaplana.u3t3menuofactivities.model.Item;

import java.util.ArrayList;

/**
 * Big modifications
 * Change a TextView for a CardView and the main info Item
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    private ArrayList<Item> items;

    private OnItemClickListener listener;

    // Class for each list item: contains only a TextView
    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView image;
        TextView version;
        TextView api;
        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.cardView = itemView.findViewById(R.id.cardview);;
            this.image = itemView.findViewById(R.id.image);
            this.version = itemView.findViewById(R.id.version);
            this.api = itemView.findViewById(R.id.api);
            this.name = itemView.findViewById(R.id.name);
        }

        // Sets viewHolder with data
        public void bind(Item item, final OnItemClickListener listener) {
            image.setImageResource(item.getIdImage());
            version.setText("Versión: " + item.getVersion());
            api.setText("API: " + item.getApiNumber());
            name.setText(item.getVersionName());

            // Call listener when click on CardView
            cardView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }

    // Constructor to set list data
    MyAdapter(ArrayList<Item> items, OnItemClickListener listener) {
        this.items = items;

        // Set listener for onItemClick
        this.listener = listener;
    }

    // Creates new view item: Layout Manager calls this method
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    // Replaces the data content of a viewholder: Layout Manager calls this method
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
        // Gets data from items at: position
        viewHolder.bind(items.get(position), listener);
    }

    // Returns the size of items: Layout Manager calls this method
    @Override
    public int getItemCount() {
        return items.size();
    }

}
