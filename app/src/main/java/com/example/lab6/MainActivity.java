package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button btnPuzzle;
    private Button btnMemory;
    public static int LEVEL=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPuzzle = findViewById(R.id.btnPuzzle);
        btnMemory = findViewById(R.id.btnMemory);

        btnPuzzle.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Puzzle.class);
            startActivity(intent);
        });

        btnMemory.setOnClickListener(view ->  {

            Intent intent = new Intent(MainActivity.this, Memory.class);
            startActivity(intent);

        });
    }
}