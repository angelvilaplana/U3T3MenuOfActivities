package dam.android.angelvilaplana.u3t3menuofactivities;

import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import dam.android.angelvilaplana.u3t3menuofactivities.model.Item;

/**
 * Ex1 - Create an Actvity Detail
 */
public class ItemDetailActivity extends AppCompatActivity {

    private ImageView imageView;

    private TextView tvVersion;

    private TextView tvApi;

    private TextView tvName;

    private TextView tvYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        setUI();

        // Ex1 - Back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(tvName.getText());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setUI() {
        imageView = findViewById(R.id.imageActivity);
        tvVersion = findViewById(R.id.versionActivity);
        tvApi = findViewById(R.id.apiActivity);
        tvName = findViewById(R.id.nameActivity);
        tvYear = findViewById(R.id.yearActivity);

        Item item = getIntent().getParcelableExtra("item");

        imageView.setImageResource(item.getIdImage());
        tvVersion.setText("Version: " + item.getVersion());
        tvApi.setText("API: " + item.getApiNumber());
        tvName.setText(item.getVersionName());
        tvYear.setText(item.getLaunchYear().toString());

        imageView.setOnClickListener(v -> {
            Intent wikiIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrlWikipedia()));
            startActivity(wikiIntent);
        });
    }

}