package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;

public class Puzzle extends AppCompatActivity {

    static final int IMAGECAPTURE = 1;
    public static boolean GANASTE;
    private static final int REQUEST_OPEN_GALLERY = 1;
    private Bitmap imageBitmap = null;
    private PuzzleBoardView boardView;
    private ImageButton solveButton;
    private static TextView score;
    private static SharedPreferences sharedpreferences;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        score = (TextView) findViewById(R.id.score2);
        GANASTE=false;
        RelativeLayout container = (RelativeLayout) findViewById(R.id.puzzle_container);
        boardView = new PuzzleBoardView(this);
        boardView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        container.addView(boardView);


        sharedpreferences = getSharedPreferences("BestScore", Context.MODE_PRIVATE);


        container.post(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.crash);
                boardView.initialize(bitmap);
                if (GANASTE) {
                    Intent intent = new Intent(Puzzle.this, MainActivity.class);
                    GANASTE = false;
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    public void modifyAttributeFromMyClass(boolean newValue) {
        this.GANASTE = newValue;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_puzzle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void dispatchTakePictureIntent(View view) {
        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
        openGalleryIntent.setType("image/*");
        startActivityForResult(openGalleryIntent, REQUEST_OPEN_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_OPEN_GALLERY && resultCode == RESULT_OK) {
            if (data != null) {
                Uri selectedImageUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    boardView.initialize(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public void shuffleImage(View view) {
        boardView.shuffle();
    }

    public void solve(View view) {

        solveButton.setClickable(false);
        boardView.solve();
        solveButton.setClickable(true);

    }

    public void home(View view){
        Intent in = new Intent(this,MainActivity.class);
        startActivity(in);
        finish();
    }

    public static void setScore(int Score){
        score.setText(""+Score);
        return ;
    }

}