package com.by5388.demo.listview.choice.mode;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.by5388.demo.listview.BaseActivity;
import com.by5388.demo.listview.ListViewApplication;
import com.by5388.demo.listview.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ListViewMultipleWithActionBarActivity extends BaseActivity
        implements AbsListView.MultiChoiceModeListener {
    private final List<Integer> mIntegers = new ArrayList<>();
    private ListView mListView;
    private MyAdapter mAdapter;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_multiple_with_action_bar);
        mTextView = findViewById(R.id.tip);
        mListView = findViewById(android.R.id.list);
        mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);
        // TODO: 2020/3/5 长按item进入带操作栏的多选模式必须实现此接口
        //没有进入多选模式时，操作动作与Choice_mode_none 一样，
        mListView.setMultiChoiceModeListener(this);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewApplication.toast("点击了 " + mAdapter.getItem(position));
            }
        });

    }

    @Override
    public void onSubmit() {
        if (mListView.getChoiceMode() == AbsListView.CHOICE_MODE_NONE) {
            return;
        }
        // TODO: 2020/3/5 似乎永远不会触发

        final SparseBooleanArray checkedItemPositions = mListView.getCheckedItemPositions();
        // TODO: 2020/3/5 在单选的模式下，size的值永远是1
        //final int size = checkedItemPositions.size();4
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

    /**
     * 长按列表时触发创建：设置标题栏的标题、内容
     * 类似于
     * {@link Activity#onCreateOptionsMenu(Menu)}
     */
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mTextView.setVisibility(View.GONE);
        mAdapter = new MyAdapter(mAdapter, android.R.layout.simple_list_item_multiple_choice);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mode.getMenuInflater().inflate(R.menu.menu_listview_multiple_choice_with_actionbar, menu);
        mode.setTitle(getString(R.string.title_selected, mListView.getCheckedItemCount()));
        return true;
    }

    /**
     * 类似于
     * {@link Activity#onPrepareOptionsMenu(Menu)}
     * 可以动态地显示或者隐藏某一个MenuItem
     */
    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        final int checkedItemCount = mListView.getCheckedItemCount();
        final MenuItem deleteItem = menu.findItem(R.id.menu_delete);
        deleteItem.setVisible(checkedItemCount > 0);
        return true;
    }

    /**
     * 类似于
     * {@link Activity#onOptionsItemSelected(MenuItem)}
     * 选项点击
     */
    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete: {
                // TODO: 2020/3/5 删除已经选择的数据
                final SparseBooleanArray checkedItemPositions = mListView.getCheckedItemPositions();
                final List<String> deleteStrings = new ArrayList<>(checkedItemPositions.size());
                for (int i = 0; i < mAdapter.getCount(); i++) {
                    if (checkedItemPositions.get(i)) {
                        deleteStrings.add(mAdapter.getItem(i));
                    }
                }
                mAdapter.removeAll(deleteStrings);
                mAdapter.notifyDataSetChanged();
                ListViewApplication.toast(String.format(Locale.getDefault(),
                        "删除了%d项，剩余%d项", checkedItemPositions.size(), mAdapter.getCount()));
                mode.finish();
            }
            break;
            case R.id.menu_cancel:
                ListViewApplication.toast("退出多选模式");
                mode.finish();
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 当取消所有选中项或者点完成时触发
     * 撤销多选模式时的回调：把所有的勾选撤销掉
     */
    @Override
    public void onDestroyActionMode(ActionMode mode) {
        // TODO: 2020/3/5 把listView的选择模式更换为NON
        setTitle(R.string.model_multiple_with_actionbar);
        mTextView.setVisibility(View.VISIBLE);
        mAdapter = new MyAdapter(mAdapter, android.R.layout.simple_list_item_1);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    /**
     * 选择的数量发生改变时，触发，可以更新标题栏
     * 类似于
     * {@link Activity#invalidateOptionsMenu()}
     */
    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        mode.setTitle(getString(R.string.title_selected, mListView.getCheckedItemCount()));
        mode.invalidate();
    }
}
