package com.example.weightsdata;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class AddWeightDialog extends Dialog {
    private EditText etdate, etmax, etmin;
    private final DatabaseHelperWeight databaseHelperWeight;
    private final Context context;
    private final CustomAdapterWeight customAdapterWeight;
    private final ListView listView;

    public AddWeightDialog(@NonNull Context context, CustomAdapterWeight customAdapterWeight, ListView listView) {
        super(context);
        this.context = context;
        databaseHelperWeight = new DatabaseHelperWeight(context);
        this.customAdapterWeight = customAdapterWeight;
        this.listView = listView;
    }

    @Override
    @SuppressLint("InflateParams")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weight);

        etdate = findViewById(R.id.et_date);
        etmax = findViewById(R.id.et_max);
        etmin = findViewById(R.id.et_min);
        Button btnStore = findViewById(R.id.btn_store);
        Button btnCancel = findViewById(R.id.btn_cancel);

        btnStore.setOnClickListener(v -> {
            String date = etdate.getText().toString();
            if (TextUtils.isEmpty(date)){
                etdate.setError("Enter Date");
                etdate.requestFocus();
                return;
            }

            double max = Double.parseDouble(etmax.getText().toString());
            double min = Double.parseDouble(etmin.getText().toString());

            databaseHelperWeight.addWeightsDetail(date, max, min);

            etdate.setText("");
            etmax.setText("");
            etmin.setText("");

            updateListView();

            Toast.makeText(context, "Stored Successfully!", Toast.LENGTH_SHORT).show();
            dismiss();
        });

        btnCancel.setOnClickListener(v -> {
            cancel();
        });
    }

    private void updateListView() {
        // Get all data from the database
        ArrayList<WeightModel> weightList = databaseHelperWeight.getAllWeights();

        // Clear the adapter
        customAdapterWeight.clear();

        // Add the new data to the adapter
        customAdapterWeight.addAll(weightList);

        // Notify the adapter that the data set has changed
        customAdapterWeight.notifyDataSetChanged();

        // Scroll to the last item in the ListView
        listView.setSelection(customAdapterWeight.getCount() - 1);
    }
}
