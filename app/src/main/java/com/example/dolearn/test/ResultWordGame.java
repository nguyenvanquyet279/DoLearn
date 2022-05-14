package com.example.dolearn.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dolearn.HandleClass;
import com.example.dolearn.MainActivity;
import com.example.dolearn.R;
import com.example.dolearn.note.NoteActivity;
import com.example.dolearn.topic.Dictionary;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultWordGame extends AppCompatActivity {
    TextView textViewResultPoint;
    Button buttonResultBackHome,buttonResultRedo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_result_word_game);
        textViewResultPoint = findViewById(R.id.textViewResultPoint);
        buttonResultBackHome=findViewById(R.id.buttonResultBackHome);
        Intent intent = getIntent();
        ArrayList<Integer> falseList = intent.getIntegerArrayListExtra("falseList");
        int point = intent.getIntExtra("Point",0);
        int maxpoint = intent.getIntExtra("MaxPoint",0);
        boolean check = intent.getBooleanExtra("check",false);
        textViewResultPoint.setText(point+"/"+ maxpoint);
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.victory);
        mp.start();
        buttonResultBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBackHome = new Intent(ResultWordGame.this, MainActivity.class);
                startActivity(intentBackHome);
            }
        });
     if(check){
         Handler handler = new Handler();
         handler.postDelayed(new Runnable() {
             public void run() {
                 AddItemToNoteDialog();
             }
         }, 1000);
     }
    }
    public void actionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void AddItemToNoteDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Thông báo");
        alertDialog.setMessage("Bạn có muốn thêm từ đã sai vào Note hay không?");
        alertDialog.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
           NoteActivity.listNote.addAll(Dictionary.wrongWordGame);
                Dictionary.wrongWordGame.clear();
                HandleClass.loadDataToFile(getApplicationContext());
            }
        });
        alertDialog.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dictionary.wrongWordGame.clear();
                HandleClass.loadDataToFile(getApplicationContext());

            }
        });
        alertDialog.show();
    }
}