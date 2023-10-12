package com.example.weightsdata;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addWeight;
    Button modifyWeight;

    private ListView listView;
    private ArrayList<WeightModel> weightsModelArrayList;
    private CustomAdapterWeight customAdapterWeight;
    private DatabaseHelperWeight databaseHelperWeight;

    public void addWeightActivity(){
        addWeight = findViewById(R.id.btn_add_weight);
        addWeight.setOnClickListener(v -> {
            Intent addWeight = new Intent(MainActivity.this, AddWeight.class);
            startActivity(addWeight);
        });
    }

    public void modWeightActivity(){
        modifyWeight = findViewById(R.id.btn_modify_weight);
        modifyWeight.setOnClickListener(v -> {
            Intent modWeight = new Intent(MainActivity.this, ModWeight.class);
            startActivity(modWeight);
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addWeightActivity();
        modWeightActivity();

        listView = findViewById(R.id.weights_lv);

        databaseHelperWeight = new DatabaseHelperWeight(this);

        weightsModelArrayList = databaseHelperWeight.getAllWeights();

        customAdapterWeight = new CustomAdapterWeight(this, weightsModelArrayList);
        listView.setAdapter(customAdapterWeight);
    }

}