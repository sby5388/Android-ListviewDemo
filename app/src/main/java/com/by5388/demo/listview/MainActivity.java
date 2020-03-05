package com.by5388.demo.listview;

import android.content.Intent;
import android.os.Bundle;

import com.by5388.demo.listview.choice.mode.ListViewMultipleActivity;
import com.by5388.demo.listview.choice.mode.ListViewMultipleWithActionBarActivity;
import com.by5388.demo.listview.choice.mode.ListViewNoneActivity;
import com.by5388.demo.listview.choice.mode.ListViewSingleActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_model_none).setOnClickListener(v -> none());
        findViewById(R.id.button_model_single).setOnClickListener(v -> single());
        findViewById(R.id.button_model_multiple).setOnClickListener(v -> multiple());
        findViewById(R.id.button_model_multiple_with_actionbar).setOnClickListener(v -> multipleWithActionBar());
    }

    private void none() {
        startActivity(new Intent(this, ListViewNoneActivity.class));
    }

    private void single() {
        startActivity(new Intent(this, ListViewSingleActivity.class));
    }

    private void multiple() {
        startActivity(new Intent(this, ListViewMultipleActivity.class));
    }

    private void multipleWithActionBar() {
        startActivity(new Intent(this, ListViewMultipleWithActionBarActivity.class));
    }

}
