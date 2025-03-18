package com.example.taxi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SetPathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.setpassactivity);


        EditText startPoint = findViewById(R.id.startPoint);
        EditText endPoint = findViewById(R.id.endPoint);
        EditText distance = findViewById(R.id.distance);
        EditText time = findViewById(R.id.time);
        EditText cost = findViewById(R.id.cost);
        EditText comment = findViewById(R.id.comment);


        Button okButton = findViewById(R.id.okButton);

        okButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("startPoint", startPoint.getText().toString());
            resultIntent.putExtra("endPoint",endPoint.getText().toString());
            resultIntent.putExtra("distance", distance.getText().toString());
            resultIntent.putExtra("time", time.getText().toString());
            resultIntent.putExtra("cost", cost.getText().toString());
            resultIntent.putExtra("comment", comment.getText().toString());


            setResult(RESULT_OK,resultIntent);
            finish();
        });


    }
}
