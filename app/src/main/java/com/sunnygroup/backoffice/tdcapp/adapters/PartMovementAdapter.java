package com.sunnygroup.backoffice.tdcapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunnygroup.backoffice.tdcapp.PartMovementFormActivity;
import com.sunnygroup.backoffice.tdcapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PartMovementAdapter extends RecyclerView.Adapter<PartMovementAdapter.ViewHolder> {
    private JSONArray movements;
    private Context mContext;

    public PartMovementAdapter(JSONObject movements) {
        try {
            this.movements = movements.getJSONObject("body").getJSONArray("slotMachines");
        } catch (JSONException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>EXCEPTION" + e);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_part_movement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        try {
            holder.mItem = movements.getJSONObject(position);

            String mpn = holder.mItem.getString("machineAssetNumber") + " | " +
                    holder.mItem.getString("machineAssetNumber") + " to " +
                    holder.mItem.getString("machineAssetNumber");

            holder.partType.setText(holder.mItem.getJSONObject("gameComponent").getString("masterGame"));
            holder.machineAndPartNumbers.setText(mpn);
            holder.location.setText(holder.mItem.getString("location"));
            //holder.locationTo.setText(holder.mItem.getString("id"));

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (null != mListener) {
//                        // Notify the active callbacks interface (the activity, if the
//                        // fragment is attached to one) that an item has been selected.
//                        mListener.onListFragmentInteraction(holder.mItem);
//                    }
//                    Toast.makeText(v.getContext(), "CLICK", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), PartMovementFormActivity.class);
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
        public final TextView machineAndPartNumbers;
        public final TextView location;
        public final TextView partType;
        public JSONObject mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            machineAndPartNumbers = (TextView) view.findViewById(R.id.machine_and_part_numbers);
            location = (TextView) view.findViewById(R.id.location);
            partType = (TextView) view.findViewById(R.id.part_type);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + location.getText() + "'";
        }
    }
}
