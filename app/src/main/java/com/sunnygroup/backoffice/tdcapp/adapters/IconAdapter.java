package com.sunnygroup.backoffice.tdcapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunnygroup.backoffice.tdcapp.R;

public class IconAdapter extends BaseAdapter {
    private Context mContext;

    public IconAdapter (Context c) { mContext = c; }

    public int getCount () {
        return mIconIds.length;
    }

    public Object getItem (int position) {
        return null;
    }

    public long getItemId (int position) {
        return 0;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
//        ImageView imageView;
//        if (convertView == null) {
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        imageView.setImageResource(mIconIds[position]);
//        return imageView;

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        if (convertView == null) {
            gridView = new View(mContext);
            gridView = inflater.inflate(R.layout.grid_view_item, null);

            TextView textView = (TextView) gridView.findViewById(R.id.android_gridview_text);
            textView.setText(mIconLabelIds[position]);

            ImageView imageView = (ImageView) gridView.findViewById(R.id.android_gridview_image);
            imageView.setImageResource(mIconIds[position]);
        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    private Integer[] mIconIds = {
            R.drawable.machine, R.drawable.part,
            R.drawable.bv, R.drawable.conversion,
            R.drawable.printer, R.drawable.history,
            R.drawable.settings
    };

    private Integer[] mIconLabelIds = {
            R.string.machine_movement, R.string.part_movement,
            R.string.bv, R.string.board_conversion,
            R.string.print, R.string.history,
            R.string.settings
    };


}
