package com.sunnygroup.backoffice.tdcapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sunnygroup.backoffice.tdcapp.MachineMovementFormActivity;
import com.sunnygroup.backoffice.tdcapp.R;
import com.sunnygroup.backoffice.tdcapp.entities.MachineMovement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MachineMovementAdapter extends RecyclerView.Adapter<MachineMovementAdapter.MachineMovementViewHolder> {

    class MachineMovementViewHolder extends RecyclerView.ViewHolder {
        private final TextView machineAssetNumber;
        private final TextView locationFrom;
        private final TextView locationTo;
        private final TextView id;

        public final View mView;

        private MachineMovementViewHolder(View view) {
            super(view);

            mView = view;

            machineAssetNumber = view.findViewById(R.id.company_asset_number);
            locationFrom = view.findViewById(R.id.location_from);
            locationTo = view.findViewById(R.id.location_to);
            id = view.findViewById(R.id.movement_id);
        }
    }

    private final LayoutInflater layoutInflater;
    private List<MachineMovement> machineMovementList;

    public MachineMovementAdapter(Context context) { layoutInflater = LayoutInflater.from(context); }

    @Override
    public MachineMovementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.fragment_machine_movement, parent, false);
        return new MachineMovementViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MachineMovementViewHolder holder, int position) {
        if (machineMovementList != null) {
            MachineMovement current = machineMovementList.get(position);

            holder.machineAssetNumber.setText(current.getAssetNumber());
            holder.locationFrom.setText(current.getLocationFromID().toString());
            holder.locationTo.setText(current.getLocationToID().toString());
            holder.id.setText(current.getId().toString());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (null != mListener) {
//                        // Notify the active callbacks interface (the activity, if the
//                        // fragment is attached to one) that an item has been selected.
//                        mListener.onListFragmentInteraction(holder.mItem);
//                    }
                    Toast.makeText(v.getContext(), "CLICK", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), MachineMovementFormActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        } else {

        }
    }

    public void setMachineMovementList(List<MachineMovement> machineMovementList) {
        this.machineMovementList = machineMovementList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (machineMovementList != null) {
            return machineMovementList.size();
        } else {
            return 0;
        }
    }


//    private JSONArray movements;
//    private Context mContext;
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public final View mView;
//        public final TextView machineAssetNumber;
//        public final TextView locationFrom;
//        public final TextView locationTo;
//        public final TextView id;
//        public JSONObject mItem;
//
//        public ViewHolder(View view) {
//            super(view);
//            mView = view;
//            machineAssetNumber = (TextView) mView.findViewById(R.id.company_asset_number);
//            locationFrom = (TextView) mView.findViewById(R.id.location_from);
//            locationTo = (TextView) mView.findViewById(R.id.location_to);
//            id = (TextView) mView.findViewById(R.id.movement_id);
//        }
//
//        @Override
//        public String toString() {
//            return super.toString() + " '" + locationTo.getText() + "'";
//        }
//    }
//
////    public MachineMovementAdapter(JSONObject movements) {
////        try {
////            System.out.println(movements);
////            this.movements = movements.getJSONObject("body").getJSONArray("slotMachines");
////        } catch (JSONException e) {
////            System.out.println(">>>>>>>>>>>>>>>>>>>EXCEPTION" + e);
////        }
////    }
//
//    public MachineMovementAdapter(JSONArray movements) {
//        System.out.println(movements);
//        this.movements = movements;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.fragment_machine_movement, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, int position) {
//        try {
//            holder.mItem = movements.getJSONObject(position);
//            holder.machineAssetNumber.setText(holder.mItem.getString("machineAssetNumber"));
//            holder.locationFrom.setText(holder.mItem.getJSONObject("locationFrom").getString("name"));
//            holder.locationTo.setText(holder.mItem.getJSONObject("locationTo").getString("name"));
//            holder.id.setText(holder.mItem.getString("id"));
//
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    if (null != mListener) {
////                        // Notify the active callbacks interface (the activity, if the
////                        // fragment is attached to one) that an item has been selected.
////                        mListener.onListFragmentInteraction(holder.mItem);
////                    }
////                    Toast.makeText(v.getContext(), "CLICK", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(v.getContext(), MachineMovementFormActivity.class);
//                    v.getContext().startActivity(intent);
//                }
//            });
//        } catch (JSONException e) {
//            System.out.println(">>>>>>>>>>>>>>>>>>> MOVEMENT ADAPTER: " + e);
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return movements.length();
//    }
}
