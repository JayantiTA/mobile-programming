package com.example.weightsdata;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ModifyWeightDialog extends Dialog {
    private final WeightModel weightsModel;
    private EditText etdate, etmax, etmin;
    private final DatabaseHelperWeight databaseHelperWeight;
    private final Context context;
    private final CustomAdapterWeight customAdapterWeight;
    private final ListView listView;

    public ModifyWeightDialog(@NonNull Context context, CustomAdapterWeight customAdapterWeight, ListView listView, WeightModel weightModel) {
        super(context);
        this.context = context;
        this.weightsModel = weightModel;
        this.customAdapterWeight = customAdapterWeight;
        this.listView = listView;
        databaseHelperWeight = new DatabaseHelperWeight(context);
    }

    @SuppressLint({"SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_weight);

        etdate = findViewById(R.id.etdate);
        etmax = findViewById(R.id.etmax);
        etmin = findViewById(R.id.etmin);
        Button btndelete = findViewById(R.id.btn_delete);
        Button btnupdate = findViewById(R.id.btn_update);
        Button btncancel = findViewById(R.id.btn_cancel);

        etdate.setText(weightsModel.getDate());
        etmax.setText(Double.toString(weightsModel.getMax()));
        etmin.setText(Double.toString(weightsModel.getMin()));

        btnupdate.setOnClickListener(v -> {
            double max = Double.parseDouble(etmax.getText().toString());
            double min = Double.parseDouble(etmin.getText().toString());
            databaseHelperWeight.updateWeights(weightsModel.getId(), etdate.getText().toString(), max, min);
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            updateListView();
            dismiss();
        });

        btndelete.setOnClickListener(v -> {
            databaseHelperWeight.deleteUSer(weightsModel.getId());
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
            updateListView();
            dismiss();
        });

        btncancel.setOnClickListener(v -> {
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
