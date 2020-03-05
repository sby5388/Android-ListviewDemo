package com.by5388.demo.listview.choice.mode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * @author Administrator  on 2020/3/5.
 */
public class MyAdapter extends BaseAdapter {
    private final List<String> mStringList = new ArrayList<>();
    private final int mItemLayout;

    public void removeAll(Collection<String> strings) {
        if (strings == null) {
            return;
        }
        for (String s : strings) {
            remove(s);
        }
    }

    public void setStringList(Collection<String> strings){
        if (strings == null) {
            return;
        }
        mStringList.clear();
        mStringList.addAll(strings);
    }

    public MyAdapter(@NonNull MyAdapter adapter,int itemLayout) {
        this(itemLayout);
        this.mStringList.clear();
        this.mStringList.addAll(adapter.mStringList);
    }

    public void remove(String s) {
        final int index = mStringList.indexOf(s);
        if (index < 0) {
            return;
        }
        mStringList.remove(index);

    }


    public MyAdapter(int itemLayout) {
        mItemLayout = itemLayout;
        for (int i = 0; i < 20; i++) {
            mStringList.add("item " + i);
        }
    }

    public MyAdapter() {
        this(android.R.layout.simple_list_item_1);
    }

    @Override
    public int getCount() {
        return mStringList.size();
    }

    @Override
    public String getItem(int position) {
        return mStringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * convert:替换
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            final Context context = parent.getContext();
            final LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(mItemLayout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final String item = getItem(position);
        holder.bind(item);
        return convertView;
    }

    private static class ViewHolder {
        private final TextView mTextView;

        ViewHolder(View view) {
            mTextView = view.findViewById(android.R.id.text1);
        }

        void bind(String s) {
            mTextView.setText(s);
        }
    }
}
