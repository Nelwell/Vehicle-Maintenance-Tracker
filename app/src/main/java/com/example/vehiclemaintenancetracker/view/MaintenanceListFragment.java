package com.example.vehiclemaintenancetracker.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vehiclemaintenancetracker.R;
import com.example.vehiclemaintenancetracker.model.VehicleMaintenance;
import com.example.vehiclemaintenancetracker.viewmodel.MaintenanceViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceListFragment extends Fragment {

    private static final String TAG = "MaintenanceListFragment";

    //    private Spinner vehicle;
    FloatingActionButton addMaintenanceFab;
    private MaintenanceAdapter adapter;
    private RecyclerView recyclerView;
    private MaintenanceViewModel maintenanceViewModel;
    private AfterTitleScreenListener afterTitleScreenListener;
    private StartNewMaintenanceItemListener newMaintenanceItemListener;

    public interface AfterTitleScreenListener {
        void viewVehicleMaintenanceList();
    }

    interface StartNewMaintenanceItemListener {
        void startNewMaintenanceItem();
    }

    public MaintenanceListFragment() {
        // Required empty public constructor
    }

    public static MaintenanceListFragment newInstance() {
        return new MaintenanceListFragment();
    }

    // Set listeners
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if (context instanceof AfterTitleScreenListener) {
            afterTitleScreenListener = (AfterTitleScreenListener) context;
            Log.d(TAG, "After Title Screen Listener set");
        } else {
            throw new RuntimeException(context.getClass().getName() + " should implement AfterTitleScreenListener");
        }

        if (context instanceof StartNewMaintenanceItemListener) {
            newMaintenanceItemListener = (StartNewMaintenanceItemListener) context;
            Log.d(TAG, "New Maintenance Item Listener set");
        } else {
            throw new RuntimeException(context.getClass().getName() + " should implement StartNewMaintenanceItemListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maintenance_list, container, false);

//        vehicle = view.findViewById(R.id.vehicle_list);

        recyclerView = view.findViewById(R.id.maintenance_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        // Creates adapter
        adapter = new MaintenanceAdapter();
        // Sets adapter in recycler view
        recyclerView.setAdapter(adapter);

        addMaintenanceFab = view.findViewById(R.id.add_new_maintenance_fab);
        addMaintenanceFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newMaintenanceItemListener.startNewMaintenanceItem();
            }
        });

        return view;
    }

    // Overrides onResume() to reload list with newest data
    @Override
    public void onResume() {
        super.onResume();
        maintenanceViewModel = ViewModelProviders.of(this).get(MaintenanceViewModel.class);
        maintenanceViewModel.getAllMaintenanceRecords().observe(this, new Observer<List<VehicleMaintenance>>() {

            @Override
            public void onChanged(List<VehicleMaintenance> maintenances) {
                adapter.setMaintenance(maintenances);
                // For testing
//                Toast.makeText(getContext(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Class to create Adapter and perform adapter functions
    private class MaintenanceAdapter extends RecyclerView.Adapter<MaintenanceAdapter.MaintenanceHolder> {
        // Where adapter stores its data
        private List<VehicleMaintenance> maintenance = new ArrayList<>();

        @NonNull
        @Override
        public MaintenanceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Get a reference to the maintenance_item RelativeLayout container and inflate in, in this context
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.maintenance_item, parent, false);
            return new MaintenanceHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MaintenanceHolder holder, int position) {
            VehicleMaintenance currentMaintenance = maintenance.get(position);
            holder.vehicleTextview.setText("Vehicle: " + currentMaintenance.getVehicle());
            holder.maintenanceTextview.setText("Repair: " + currentMaintenance.getService());
            holder.mileageTextview.setText("Mileage: " + String.valueOf(currentMaintenance.getMileage()));
            holder.locationTextview.setText("Location: " + currentMaintenance.getLocation());
            if (currentMaintenance.getNotes() == null) {
                holder.notesTextview.setText("Notes: N/A");
            } else {
                holder.notesTextview.setText("Notes: " + currentMaintenance.getNotes());
            }
            holder.dateTextView.setText(currentMaintenance.getDate().toString());
        }

        @Override
        public int getItemCount() {
            return maintenance.size();
        }

        public void setMaintenance(List<VehicleMaintenance> maintenance) {
            this.maintenance = maintenance;
            // Tell recyclerView to reload
            notifyDataSetChanged();
        }

        class MaintenanceHolder extends RecyclerView.ViewHolder {
            private TextView vehicleTextview;
            private TextView maintenanceTextview;
            private TextView mileageTextview;
            private TextView locationTextview;
            private TextView notesTextview;
            private TextView dateTextView;

            public MaintenanceHolder(@NonNull View itemView) {
                super(itemView);
                vehicleTextview = itemView.findViewById(R.id.vehicle_textview);
                maintenanceTextview = itemView.findViewById(R.id.maintenance_textview);
                mileageTextview = itemView.findViewById(R.id.mileage_textview);
                locationTextview = itemView.findViewById(R.id.location_textview);
                notesTextview = itemView.findViewById(R.id.notes_textview);
                dateTextView = itemView.findViewById(R.id.date_textview);

            }
        }
    }
}
