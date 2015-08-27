package com.luizgadao.testzup.event;

import android.view.View;

/**
 * Created by luizcarlos on 27/08/15.
 */
public class ItemRecyclerViewClick {

    private View view;
    private int position;

    public ItemRecyclerViewClick( View view, int pos ) {
        this.view = view;
        this.position = pos;
    }
    public View getView() {
        return view;
    }
    public int getPosition() { return position; }

}
