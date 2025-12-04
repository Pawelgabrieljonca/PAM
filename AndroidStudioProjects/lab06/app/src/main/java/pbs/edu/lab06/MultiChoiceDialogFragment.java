package pbs.edu.lab06;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import java.util.ArrayList;

public class MultiChoiceDialogFragment extends DialogFragment {
    // Lista do przechowywania zaznaczonych elementów
    private ArrayList<String> selectedItems = new ArrayList<>();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String[] toppings = {"Ser", "Pieczarki", "Szynka", "Cebula"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Wybierz składniki")
                .setMultiChoiceItems(toppings, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            selectedItems.add(toppings[which]);
                        } else {
                            selectedItems.remove(toppings[which]);
                        }
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Wynik jako lista w Toast
                        Toast.makeText(getActivity(), "Wybrano: " + selectedItems.toString(), Toast.LENGTH_SHORT).show();
                        selectedItems.clear();
                    }
                })
                .setNegativeButton("Anuluj", null);

        return builder.create();
    }
}