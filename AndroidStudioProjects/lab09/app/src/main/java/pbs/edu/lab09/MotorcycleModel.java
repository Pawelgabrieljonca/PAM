package pbs.edu.lab09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MotorcycleModel {
    private List<String> motorcycles;

    public MotorcycleModel() {
        motorcycles = new ArrayList<>(Arrays.asList(
                "Honda CB500F", "Yamaha MT-03", "Kawasaki Z400", "KTM Duke 390", "BMW G310R"
        ));
    }

    public List<String> getMotorcycles() {
        return motorcycles;
    }
}