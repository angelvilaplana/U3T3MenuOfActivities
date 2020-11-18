package dam.android.angelvilaplana.u3t3menuofactivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dam.android.angelvilaplana.u3t3menuofactivities.model.Item;

import java.time.Year;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener {

    private RecyclerView recyclerView;

    private RecyclerView.Adapter mAdapter;

    private RecyclerView.LayoutManager layoutManager;

    private ItemTouchHelper itemTouchHelper;

    private Button btnAdd;

    private Button btnDeleteAll;

    private Button btnRestore;

    private ImageView imgNoItems;

    private ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ex2 - Set data
        initializeData();
        setUI();
    }

    /**
     * Set items in the ArrayList
     */
    public void initializeData() {
        items = new ArrayList<>();
        items.add(new Item(R.mipmap.android_jelly_bean, "4.1", "Jelly Bean", Year.of(2012), 18,"https://es.wikipedia.org/wiki/Android_Jelly_Bean"));
        items.add(new Item(R.mipmap.android_kitkat, "4.4", "KitKat", Year.of(2013), 20,"https://es.wikipedia.org/wiki/Android_KitKat"));
        items.add(new Item(R.mipmap.android_lollipop, "5.0", "Lollipop", Year.of(2014), 22,"https://es.wikipedia.org/wiki/Android_Lollipop"));
        items.add(new Item(R.mipmap.android_marshmallow, "6.0", "Marshmallow", Year.of(2015), 23,"https://es.wikipedia.org/wiki/Android_Marshmallow"));
        items.add(new Item(R.mipmap.android_nougat, "7.0", "Nougat", Year.of(2016), 25,"https://es.wikipedia.org/wiki/Android_Nougat"));
        items.add(new Item(R.mipmap.android_oreo, "8.0", "Oreo", Year.of(2017), 27,"https://es.wikipedia.org/wiki/Android_Oreo"));
        items.add(new Item(R.mipmap.android_pie, "9.0", "Pie", Year.of(2018), 28,"https://es.wikipedia.org/wiki/Android_Pie"));
        items.add(new Item(R.mipmap.android_10, "10.0", "Android 10", Year.of(2019), 29,"https://es.wikipedia.org/wiki/Android_10"));
        items.add(new Item(R.mipmap.android_11, "11.0", "Android 11", Year.of(2020), 30,"https://es.wikipedia.org/wiki/Android_11"));
    }

    public void setUI() {
        recyclerView = findViewById(R.id.recyclerViewActivities);

        // Use this settings to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // Set recyclerView with a linear layout manager
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Set an adapter to recyclerView
        mAdapter = new MyAdapter(items, this);
        recyclerView.setAdapter(mAdapter);

        // Ex3 - Set image
        imgNoItems = findViewById(R.id.imgNoItems);

        // Ex3 - Set buttons

        // Add future version
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            items.add(new Item(R.mipmap.android_12, "12.0", "Android 12", Year.of(2021), 31,"https://es.wikipedia.org/wiki/Android_12"));
            imgNoItems.setVisibility(-1);
            int position = items.size() - 1;
            mAdapter.notifyItemInserted(position);
            recyclerView.smoothScrollToPosition(position);
        });

        // Delete all
        btnDeleteAll = findViewById(R.id.btnDeleteAll);
        btnDeleteAll.setOnClickListener(v -> {
            items.clear();
            mAdapter.notifyDataSetChanged();
            imgNoItems.setVisibility(1);
        });

        // Restore data
        btnRestore = findViewById(R.id.btnRestore);
        btnRestore.setOnClickListener(v -> {
            initializeData();
            imgNoItems.setVisibility(-1);
            mAdapter = new MyAdapter(items, this);
            recyclerView.setAdapter(mAdapter);
        });

        // Ex3 - Swipe
        itemTouchHelper = new ItemTouchHelper(itemTouchHelpCallBack);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    /**
     * Recover data
     * @param savedInstanceState where the data is retrieved
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        recyclerView.scrollTo(0, savedInstanceState.getInt("positionY"));
        items = savedInstanceState.getParcelableArrayList("items");
        mAdapter = new MyAdapter(items, this);
        recyclerView.setAdapter(mAdapter);

        if (items.isEmpty()) {
            imgNoItems.setVisibility(1);
        }
    }

    /**
     * Save data
     * @param outState Where save the data
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        int[] location = new int[2];
        recyclerView.getLocationOnScreen(location);
        outState.putInt("positionY", location[1]);
        outState.putParcelableArrayList("items", items);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onItemClick(Item item) {
        // Ex1 - ItemDetailActivity
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }

    // Ex3 - Swipe RecycleView
    ItemTouchHelper.SimpleCallback itemTouchHelpCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @SuppressLint("WrongConstant")
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            items.remove(position);
            mAdapter.notifyItemRemoved(position);
            if (items.isEmpty()) {
                imgNoItems.setVisibility(1);
            }
        }
    };

}