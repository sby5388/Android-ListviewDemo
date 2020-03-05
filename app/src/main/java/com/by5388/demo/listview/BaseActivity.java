package com.by5388.demo.listview;

import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Administrator  on 2020/3/5.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public abstract void onSubmit();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_submit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_submit) {
            onSubmit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
