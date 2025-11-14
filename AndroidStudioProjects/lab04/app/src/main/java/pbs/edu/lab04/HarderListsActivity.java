package pbs.edu.lab04;

import android.os.Bundle;
import android.view.KeyEvent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Random;

public class HarderListsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CarsAdapter mAdapter;
    private final boolean wybor = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harder_lists);

        recyclerView = findViewById(R.id.recycler_view);

        String[] possibleCars = {"Toyota", "BMW", "Audi", "Mercedes", "Ford", "Honda", "Tesla", "Volkswagen", "Volvo", "Mazda"};
        String[] cars = new String[30];
        Random random = new Random();

        for (int i = 0; i < cars.length; i++) {
            cars[i] = possibleCars[random.nextInt(possibleCars.length)] + " " + (i + 1);
        }

        if (wybor) {
            GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            mAdapter = new CarsAdapter(cars);
            recyclerView.setAdapter(mAdapter);
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new CarsAdapter(cars));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ESCAPE) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
