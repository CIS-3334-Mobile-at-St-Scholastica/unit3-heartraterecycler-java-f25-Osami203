package cis3334.java_heartrate_start;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HeartRateAdapter extends RecyclerView.Adapter<HeartRateViewHolder> {

    private final Application application;
    private final MainViewModel mainViewModel;

    public HeartRateAdapter(Application application, MainViewModel mainViewModel) {
        this.application = application;
        this.mainViewModel = mainViewModel;
    }

    @Override
    @NonNull
    public HeartRateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.heartrate_row, parent, false);
        return new HeartRateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeartRateViewHolder holder, int position) {
        Heartrate hr = mainViewModel.getHeartrateAt(position);

        if (hr == null) {
            holder.valuePulse.setText("");
            holder.valueRange.setText("");
            holder.valueDescription.setText("");
            return;
        }

        holder.valuePulse.setText(hr.pulse == null ? "" : hr.pulse.toString());
        holder.valueRange.setText(hr.getRangeName());
        holder.valueDescription.setText(hr.getRangeDescription());
    }

    @Override
    public int getItemCount() {
        return mainViewModel.getHeartrateCount();
    }

    public void setAllHeartrates(List<Heartrate> allHeartrates) {
        mainViewModel.setCachedHeartrates(allHeartrates);
    }
}
