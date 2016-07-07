package com.qulp.qulptwitter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ishan-3306 on 7/7/2016.
 */
public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration{

    private final int mVerticalSpaceHeight;

    public VerticalSpaceItemDecoration(int mVerticalSpaceHeight) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.top = mVerticalSpaceHeight;
        outRect.bottom = mVerticalSpaceHeight;
        outRect.left = outRect.right = 25;

        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = mVerticalSpaceHeight;
        }
    }



}
