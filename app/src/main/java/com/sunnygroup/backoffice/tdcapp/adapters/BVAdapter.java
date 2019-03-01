package com.sunnygroup.backoffice.tdcapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunnygroup.backoffice.tdcapp.BVFormActivity;
import com.sunnygroup.backoffice.tdcapp.PartMovementFormActivity;
import com.sunnygroup.backoffice.tdcapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BVAdapter extends RecyclerView.Adapter<BVAdapter.ViewHolder> {
    private JSONArray movements;
    private Context mContext;

    public BVAdapter(JSONObject movements) {
        try {
            this.movements = movements.getJSONObject("body").getJSONArray("slotMachines");
        } catch (JSONException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>EXCEPTION" + e);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_bv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        try {
            holder.mItem = movements.getJSONObject(position);

            holder.total.setText("$ " + holder.mItem.getString("machineAssetNumber"));
            holder.count.setText(holder.mItem.getString("machineAssetNumber"));
            holder.location.setText(holder.mItem.getString("location"));

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), BVFormActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        } catch (JSONException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>> MOVEMENT ADAPTER: " + e);
        }
    }

    @Override
    public int getItemCount() {
        return movements.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView location;
        public final TextView total;
        public final TextView count;
        public JSONObject mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            location = (TextView) view.findViewById(R.id.location);
            total = (TextView) view.findViewById(R.id.total);
            count = (TextView) view.findViewById(R.id.count);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + location.getText() + "'";
        }
    }
}
