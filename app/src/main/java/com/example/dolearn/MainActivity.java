package com.example.dolearn;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import com.example.dolearn.irregularVerbs.IrregularVerbs;
import com.example.dolearn.note.NoteActivity;
import com.example.dolearn.setting.SettingActivity;
import com.example.dolearn.topic.Dictionary;
import com.example.dolearn.topic.Item;
import com.example.dolearn.topic.TopicActivity;
import com.example.dolearn.translate.TranslateActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    CardView cardView_topic, cardView_translate, cardView_note, cartView_setting, cardView_irregular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_main);
        anhxa();

        loadDataFromFile();
        Item item = new Item("Example (Noun)", "Ví dụ", "/ig´za:mp(ə)l/", "We study some examples.", "Chúng tôi nghiên cứu một số ví dụ.");
        if (NoteActivity.listNote.isEmpty()) {
            NoteActivity.listNote.add(item);
        }
        for (Item item1 : NoteActivity.listNote) {
            NoteActivity.listEngName.add(item1.getEngName());
        }

        cardView_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TranslateActivity.class);
                startActivity(intent);
                System.out.println(Dictionary.listItem.size());

            }
        });

        cardView_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TopicActivity.class);
                startActivity(intent);
            }
        });

        cardView_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(intent);
            }
        });

        cartView_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        cardView_irregular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IrregularVerbs.class);
                startActivity(intent);
            }
        });
    }

    private void anhxa() {
        cardView_translate = findViewById(R.id.cardView_translate);
        cardView_topic = findViewById(R.id.cardView_topic);
        cardView_note = findViewById(R.id.cardView_note);
        cartView_setting = findViewById(R.id.cardView_setting);
        cardView_irregular = findViewById(R.id.cardView_irregular);
    }

    //Load data from file in device to listNote
    public void loadDataFromFile() {
        NoteActivity.listNote.clear();
        try {
            FileInputStream fis = openFileInput("fileNote.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            String data[];
            while ((line = bufferedReader.readLine()) != null) {
                data = line.split("\t");
                NoteActivity.listNote.add(new Item(data[0], data[1], data[2], data[3], data[4]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //onclick Back --> Close App
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finishAffinity();
                                System.exit(0);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void actionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}