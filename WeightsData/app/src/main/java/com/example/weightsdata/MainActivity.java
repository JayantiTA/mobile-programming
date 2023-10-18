package com.example.weightsdata;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addWeight;
    Button modifyWeight;

    private ListView listView;
    private CustomAdapterWeight customAdapterWeight;
    private DatabaseHelperWeight databaseHelperWeight;

    public void addWeightActivity(){
        addWeight = findViewById(R.id.btn_add_weight);
        addWeight.setOnClickListener(v -> {
            addWeight();
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addWeightActivity();

        listView = findViewById(R.id.weights_lv);

        databaseHelperWeight = new DatabaseHelperWeight(this);

        ArrayList<WeightModel> weightsModelArrayList = databaseHelperWeight.getAllWeights();

        customAdapterWeight = new CustomAdapterWeight(this, weightsModelArrayList);
        listView.setAdapter(customAdapterWeight);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            modWeight(weightsModelArrayList.get(position));
        });
    }

    private void addWeight() {
        AddWeightDialog addWeight = new AddWeightDialog(this, customAdapterWeight, listView);
        addWeight.show();
    }

    private void modWeight(WeightModel weightModel) {
        ModifyWeightDialog modWeight = new ModifyWeightDialog(this, customAdapterWeight, listView, weightModel);
        modWeight.show();
    }
}