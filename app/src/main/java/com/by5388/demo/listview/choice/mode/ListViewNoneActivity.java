package com.by5388.demo.listview.choice.mode;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.by5388.demo.listview.BaseActivity;
import com.by5388.demo.listview.ListViewApplication;
import com.by5388.demo.listview.R;

public class ListViewNoneActivity extends BaseActivity {
    private ListView mListView;
    private int mIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_none);
        mListView = findViewById(android.R.id.list);
        final MyAdapter adapter = new MyAdapter();
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mIndex = position;
                final Object item = parent.getAdapter().getItem(position);
                ListViewApplication.toast(item.toString());
            }
        });
    }

    @Override
    public void onSubmit() {
        //final Object selectedItem = mListView.getS;
        // ListViewApplication.toast(selectedItem.toString());
        if (mIndex == -1) {
            ListViewApplication.toast("未点击");
        } else {
            final Object item = mListView.getAdapter().getItem(mIndex);
            ListViewApplication.toast("点击了 " + item.toString());
        }
    }
}
