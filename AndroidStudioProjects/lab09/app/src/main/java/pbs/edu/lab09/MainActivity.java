package pbs.edu.lab09;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MotorcycleApp";
    private MotorcycleModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Po dodaniu pakietu 'R' powinno się naprawić

        model = new MotorcycleModel();
        List<String> data = model.getMotorcycles();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MotorcycleAdapter adapter = new MotorcycleAdapter(data, item -> {
            Log.d(TAG, "Wybrano model: " + item);
        });

        recyclerView.setAdapter(adapter);
    }
}