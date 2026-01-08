package pbs.edu.lab09;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MotorcycleAdapter extends RecyclerView.Adapter<MotorcycleAdapter.ViewHolder> {
    private final List<String> localDataSet;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(android.R.id.text1);
        }
        public void bind(final String item, final OnItemClickListener listener) {
            textView.setText(item);
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }

    public MotorcycleAdapter(List<String> dataSet, OnItemClickListener listener) {
        this.localDataSet = dataSet;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.bind(localDataSet.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}