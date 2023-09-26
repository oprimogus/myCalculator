package com.example.mycalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CalculatorAdapter extends BaseAdapter {
    private Context context;
    private String[] buttons;

    public CalculatorAdapter(Context context, String[] buttons) {
        this.context = context;
        this.buttons = buttons;
    }

    @Override
    public int getCount() {
        return buttons.length;
    }

    @Override
    public Object getItem(int position) {
        return buttons[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
            textView = convertView.findViewById(R.id.grid_item_text);
            convertView.setTag(textView);
        } else {
            textView = (TextView) convertView.getTag();
        }

        if (buttons[position].equals("0")) {
            textView.setWidth(2 * 60); // Considerando que 60dp é a largura padrão
        } else {
            textView.setWidth(60);
        }

        textView.setText(buttons[position]);
        return convertView;
    }
}
