package dam.android.angelvilaplana.u3t3menuofactivities;

import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener {

    private RecyclerView recyclerView;

    private RecyclerView.Adapter mAdapter;

    private RecyclerView.LayoutManager layoutManager;

    private String[] myDataset = {"Activity1", "Activity2", "Activity3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
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
        mAdapter = new MyAdapter(myDataset, this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(String activityName) {
        Toast.makeText(this, activityName, Toast.LENGTH_LONG).show();
        try {
            startActivity(new Intent(this, Class.forName(getPackageName() + "." + activityName)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}