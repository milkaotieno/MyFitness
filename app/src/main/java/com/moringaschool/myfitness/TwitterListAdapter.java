package com.moringaschool.myfitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import java.util.ArrayList;

public class TwitterListAdapter extends ArrayAdapter<Tweet> {
    private ArrayList<Tweet> twits;
    public TwitterListAdapter(Context context, int resource, ArrayList<Tweet> items) {
        super(context, resource, items);
        this.twits = items;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent){
        View v = view;
        if(v == null){
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.listtwitters, null);
            Tweet o = twits.get(position);
            TextView header1 = (TextView) v.findViewById(R.id.header1);
            TextView header2 = (TextView) v.findViewById(R.id.header2);
            header1.setText(o.content);
            header2.setText(o.author);


        }
        return v;
    }


   }
