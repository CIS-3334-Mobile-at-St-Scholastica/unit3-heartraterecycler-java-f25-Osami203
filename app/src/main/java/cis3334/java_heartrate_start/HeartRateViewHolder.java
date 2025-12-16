package cis3334.java_heartrate_start;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HeartRateViewHolder extends RecyclerView.ViewHolder {

    TextView valuePulse;
    TextView valueRange;
    TextView valueDescription;

    public HeartRateViewHolder(@NonNull View itemView) {
        super(itemView);

        valuePulse = itemView.findViewById(R.id.valuePulse);
        valueRange = itemView.findViewById(R.id.valueRange);
        valueDescription = itemView.findViewById(R.id.valueDescription);
    }
}
