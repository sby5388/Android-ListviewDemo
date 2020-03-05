package com.by5388.demo.listview.choice.mode;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.widget.ListView;

import com.by5388.demo.listview.BaseActivity;
import com.by5388.demo.listview.ListViewApplication;
import com.by5388.demo.listview.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListViewSingleActivity extends BaseActivity {
    private final List<Integer> mIntegers = new ArrayList<>();
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_single);
        mListView = findViewById(android.R.id.list);
        final MyAdapter adapter = new MyAdapter(android.R.layout.simple_list_item_single_choice);
        mListView.setAdapter(adapter);

    }

    @Override
    public void onSubmit() {
        final SparseBooleanArray checkedItemPositions = mListView.getCheckedItemPositions();
        // TODO: 2020/3/5 在单选的模式下，size的值永远是1
        final int size = checkedItemPositions.size();
        mIntegers.clear();
        for (int i = 0; i < mListView.getAdapter().getCount(); i++) {
            if (checkedItemPositions.get(i)) {
                mIntegers.add(i);
            }
        }
        if (mIntegers.isEmpty()) {
            ListViewApplication.toast("未点击");
        } else {
            ListViewApplication.toast(" 点击了 " + Arrays.toString(mIntegers.toArray()));
        }


    }
}
