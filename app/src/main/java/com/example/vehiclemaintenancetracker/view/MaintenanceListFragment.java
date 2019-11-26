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

import java.util.ArrayList;
import java.util.List;

public class MaintenanceListFragment extends Fragment {

    private static final String TAG = "Maintenance Items List Fragment";

    private Spinner vehicle;
    private MaintenanceAdapter adapter;
    private RecyclerView recyclerView;
    private MaintenanceViewModel maintenanceViewModel;
    private AfterTitleScreenListener afterTitleScreenListener;

    public interface AfterTitleScreenListener {
        void viewVehicleMaintenanceList();
    }

    public MaintenanceListFragment() {
        // Required empty public constructor
    }

    public static MaintenanceListFragment newInstance() {
        return new MaintenanceListFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if (context instanceof MaintenanceListFragment.AfterTitleScreenListener) {
            afterTitleScreenListener = (MaintenanceListFragment.AfterTitleScreenListener) context;
            Log.d(TAG, "Listener set");
        } else {
            throw new RuntimeException(context.getClass().getName() + " should implement AfterTitleScreenListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maintenance_list, container, false);

        vehicle = view.findViewById(R.id.vehicle_list);

        recyclerView = view.findViewById(R.id.maintenance_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        adapter = new MaintenanceAdapter();
        recyclerView.setAdapter(adapter);

        maintenanceViewModel = ViewModelProviders.of(this).get(MaintenanceViewModel.class);
        maintenanceViewModel.getAllMaintenanceRecords().observe(this, new Observer<List<VehicleMaintenance>>() {
            @Override
            public void onChanged(List<VehicleMaintenance> maintenances) {
                adapter.setMaintenance(maintenances);
                Toast.makeText(getContext(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public static class MaintenanceAdapter extends RecyclerView.Adapter<MaintenanceAdapter.MaintenanceHolder> {
        private List<VehicleMaintenance> maintenance = new ArrayList<>();

        @NonNull
        @Override
        public MaintenanceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.maintenance_item, parent, false);
            return new MaintenanceHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MaintenanceHolder holder, int position) {
            VehicleMaintenance currentMaintenance = maintenance.get(position);
            holder.maintenanceEditText.setText(currentMaintenance.getService());
            holder.mileageEditText.setText(String.valueOf(currentMaintenance.getMileage()));
            holder.locationEditText.setText(currentMaintenance.getLocation());
            holder.notesEditText.setText(currentMaintenance.getNotes());
//            holder.dateTextView.setText(currentMaintenance.getDateCreated());
        }

        @Override
        public int getItemCount() {
            return maintenance.size();
        }

        public void setMaintenance(List<VehicleMaintenance> maintenance) {
            this.maintenance = maintenance;
            notifyDataSetChanged();
        }

        class MaintenanceHolder extends RecyclerView.ViewHolder {
            private EditText maintenanceEditText;
            private EditText mileageEditText;
            private EditText locationEditText;
            private EditText notesEditText;
            private TextView dateTextView;

            public MaintenanceHolder(@NonNull View itemView) {
                super(itemView);
                maintenanceEditText = itemView.findViewById(R.id.maintenance_edittext);
                mileageEditText = itemView.findViewById(R.id.mileage_edittext);
                locationEditText = itemView.findViewById(R.id.location_edittext);
                notesEditText = itemView.findViewById(R.id.notes_edittext);
                dateTextView = itemView.findViewById(R.id.date_textview);

            }
        }
    }
}
