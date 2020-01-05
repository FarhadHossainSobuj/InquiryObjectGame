package com.example.findobjectgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivVitamin, ivMinerals, ivDna, ivEnergy;
    private Button btnSelect;
    private TextView tvTimer, tvScore;
    private int count;
    private CountDownTimer countDownTimer;

    private List<String> object;
    private String currentObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivVitamin = findViewById(R.id.ivVitamin);
        ivMinerals = findViewById(R.id.ivMinerals);
        ivEnergy = findViewById(R.id.ivEnergy);
        ivDna = findViewById(R.id.ivDna);

        btnSelect = findViewById(R.id.btnSelect);

        object = new ArrayList<>();
        object.add("vitamin");
        object.add("minerals");
        object.add("energy");
        object.add("dna");

        tvTimer = findViewById(R.id.tvTimer);
        tvScore = findViewById(R.id.tvScore);

        ivVitamin.setOnClickListener(this);
        ivMinerals.setOnClickListener(this);
        ivEnergy.setOnClickListener(this);
        ivDna.setOnClickListener(this);

        countDownTimer = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(""+(millisUntilFinished/1000));
                tvScore.setText("Score : " + count);
                if(count==4){
                    onFinish();
                    cancel();

                }
            }

            @Override
            public void onFinish() {
                showDialog();
                tvTimer.setText("0");
            }
        }.start();
        selectObject();
        count = 0;
    }
    private void showDialog(){

        new AlertDialog.Builder(this).setTitle("Result")
                .setMessage("Your score: " + count)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recreate();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setCancelable(false)
                .show();

    }

    @Override
    public void onClick(View v) {
        String tag;
        switch (v.getId()){
            case R.id.ivVitamin:
                tag = ivVitamin.getTag().toString();
                Log.d("tag", tag);
                if(currentObject.equals(tag)) {
                    ivVitamin.setVisibility(View.GONE);
                    updateScore();
                }
                break;
            case R.id.ivMinerals:
                tag = ivMinerals.getTag().toString();
                if(currentObject.equals(tag)) {
                    ivMinerals.setVisibility(View.GONE);
                    updateScore();
                }
                break;
            case R.id.ivDna:
                tag = ivDna.getTag().toString();
                if(currentObject.equals(tag)) {
                    ivDna.setVisibility(View.GONE);
                    updateScore();
                }
                break;
            case R.id.ivEnergy:
                tag = ivEnergy.getTag().toString();
                if(currentObject.equals(tag)) {
                    ivEnergy.setVisibility(View.GONE);
                    updateScore();
                }
                break;
        }
    }

    private void updateScore() {
        count += 1;
        selectObject();
    }

    private void selectObject(){
        if(object.size() > 0){
            Random random = new Random();
            int position = random.nextInt(object.size());
            currentObject = object.get(position);
            btnSelect.setText(currentObject);
            object.remove(currentObject);
        } else {
            btnSelect.setText("Congratulations...");
        }
    }
}
