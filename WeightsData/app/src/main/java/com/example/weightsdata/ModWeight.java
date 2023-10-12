package com.example.weightsdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ModWeight extends AppCompatActivity {
    private ArrayList<WeightModel> weightsModelArrayList;
    private DatabaseHelperWeight databaseHelperWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_weight);

        ListView listView = (ListView) findViewById(R.id.weights_lvi);

        databaseHelperWeight = new DatabaseHelperWeight(this);

        weightsModelArrayList = databaseHelperWeight.getAllWeights();

        CustomMod customMod = new CustomMod(this, weightsModelArrayList);
        listView.setAdapter(customMod);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ModWeight.this,ModifyWeightsActivity.class);
                intent.putExtra("weights", weightsModelArrayList.get(position));
                startActivity(intent);
            }
        });
    }
}
