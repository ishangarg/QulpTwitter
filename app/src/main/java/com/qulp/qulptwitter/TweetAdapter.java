package com.qulp.qulptwitter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qulp.qulptwitter.Model.Status;

import java.util.ArrayList;

/**
 * Created by ishan-3306 on 7/6/2016.
 */
public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.RecyclerViewHolder> {

    private ArrayList<Status> mDataset;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView textView_tweet;
        RelativeLayout row;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            textView_tweet = (TextView) itemView.findViewById(R.id.tv_tweet);
        }
    }

    public TweetAdapter(ArrayList<Status> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public TweetAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_tweet, parent, false);
        RecyclerViewHolder vh = new RecyclerViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView_tweet.setText(mDataset.get(position).getText());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();

    }
}
