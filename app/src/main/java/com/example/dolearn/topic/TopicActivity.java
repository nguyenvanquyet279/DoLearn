package com.example.dolearn.topic;

import android.content.Intent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dolearn.MainActivity;
import com.example.dolearn.R;

import java.util.ArrayList;

public class TopicActivity extends AppCompatActivity {
    ListView listView_topic;
    Intent intent, intentTopicItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar();
        setContentView(R.layout.activity_topic);

        anhxa();
        ArrayAdapter<CharSequence> topicAdapter = ArrayAdapter.createFromResource(this,R.array.topic_list, android.R.layout.simple_list_item_1);
        listView_topic.setAdapter(topicAdapter);
        listView_topic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intentTopicItem = new Intent(TopicActivity.this, TopicItemActivity.class);
                intentTopicItem.putExtra("NumberTopic", i);
                startActivity(intentTopicItem);
            }
        });
    }

    private void anhxa() {
        listView_topic = findViewById(R.id.listView_topic);
    }

    //Handle click back in device
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            intent = new Intent(TopicActivity.this, MainActivity.class);
//            startActivity(intent);
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    public void actionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Topic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //Handle click backIcon
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
//                intent = new Intent(TopicActivity.this, MainActivity.class);
//                startActivity(intent);
                onBackPressed();
                return true;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}