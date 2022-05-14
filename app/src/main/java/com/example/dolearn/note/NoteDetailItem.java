package com.example.dolearn.note;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.dolearn.HandleClass;
import com.example.dolearn.R;
import com.example.dolearn.topic.Item;

import java.util.ArrayList;

public class NoteDetailItem extends AppCompatActivity {
    TextView textView_engName,textView_vieName,textView_pronounce,textView_exampleEn,textView_exampleVi;
    CheckBox checkBox_star,checkBox_speaker;
    int itemNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_item);
        anhxa();
        Intent getI = getIntent();
        itemNumber = getI.getIntExtra("NoteItemNumber", 0);
        actionBar();
        ArrayList<Item> arrayList = (ArrayList<Item>) NoteActivity.listNote.clone();
        textView_engName.setText(arrayList.get(itemNumber).getEngName());
        textView_vieName.setText(arrayList.get(itemNumber).getVieName());
        textView_pronounce.setText(arrayList.get(itemNumber).getPronoun());
        textView_exampleEn.setText(arrayList.get(itemNumber).getExampleEn());
        textView_exampleVi.setText(arrayList.get(itemNumber).getExampleVi());
        checkBox_star.setChecked(true);
    }

    private void anhxa() {
        textView_engName = findViewById(R.id.textView_engName);
        textView_vieName = findViewById(R.id.textView_vieName);
        textView_pronounce = findViewById(R.id.textView_pronounce);
        textView_exampleEn = findViewById(R.id.textView_exampleEn);
        textView_exampleVi = findViewById(R.id.textView_exampleVi);
        checkBox_speaker = findViewById(R.id.checkBox_speaker);
        checkBox_star = findViewById(R.id.checkBox_star);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox_star:
                if (!checked) {
                    NoteActivity.listNote.remove(itemNumber);
                }
                //overwrite listNote to file
                HandleClass.loadDataToFile(NoteDetailItem.this);
                // Remove the meat
                break;
            case R.id.checkBox_speaker:
                if (checked) {
                    HandleClass.textToSpeech(NoteDetailItem.this, textView_engName);
                    checkBox_speaker.setChecked(false);
                }
                break;
            // TODO: Veggie sandwich
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(NoteDetailItem.this, NoteActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void actionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(NoteActivity.listNote.get(itemNumber).getEngName().split("\\(")[0]);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //Handle click backIcon
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent intent = new Intent(NoteDetailItem.this, NoteActivity.class);
                startActivity(intent);
                return true;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}