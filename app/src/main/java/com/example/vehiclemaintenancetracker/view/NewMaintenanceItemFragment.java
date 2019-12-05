package com.example.vehiclemaintenancetracker.view;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vehiclemaintenancetracker.R;
import com.example.vehiclemaintenancetracker.model.VehicleMaintenance;
import com.example.vehiclemaintenancetracker.service.VehicleService;
import com.example.vehiclemaintenancetracker.viewmodel.MaintenanceViewModel;

import java.util.Date;

public class NewMaintenanceItemFragment extends Fragment {

    private static final String TAG = "NEW_MAINTENANCE_ITEM_FRAGMENT";

    private EditText vehicleEditText;
    private EditText maintenanceEditText;
    private EditText mileageEditText;
    private EditText locationEditText;
    private EditText notesEditText;
    private MaintenanceViewModel maintenanceViewModel;
    private OnNewMaintenanceAddedListener newMaintenanceListener;

    public interface OnNewMaintenanceAddedListener {
        void onNewMaintenanceAdded(VehicleMaintenance vehicleMaintenance);
    }

    public NewMaintenanceItemFragment() {
        // Required empty public constructor
    }


    public static NewMaintenanceItemFragment newInstance() {
        return new NewMaintenanceItemFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize maintenanceViewModel
        maintenanceViewModel = ViewModelProviders.of(this).get(MaintenanceViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_maintenance_item, container, false);

        // Get references to string resources
        vehicleEditText = view.findViewById(R.id.vehicle_edittext);
        maintenanceEditText = view.findViewById(R.id.maintenance_edittext);
        mileageEditText = view.findViewById(R.id.mileage_edittext);
        locationEditText = view.findViewById(R.id.location_edittext);
        notesEditText = view.findViewById(R.id.notes_edittext);
        Button okButton = view.findViewById(R.id.ok_button);

        // okButton click listener
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get all values from edittexts of newMaintenanceItem
                String vehicle = vehicleEditText.getText().toString();
                String maintenance = maintenanceEditText.getText().toString();
                int mileage = Integer.parseInt(mileageEditText.getText().toString());
                String location = locationEditText.getText().toString();
                // Notes optional field
                String notes = notesEditText.getText().toString();

                // Checks if any of required fields are empty
                if (vehicle.isEmpty() || maintenance.isEmpty() || mileageEditText.getText().toString().isEmpty()
                        || location.isEmpty()) {
                    Toast.makeText(NewMaintenanceItemFragment.this.getContext(),
                            "Enter a vehicle, maintenance, current mileage, and location of repair", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Bundles all fields into new VehicleMaintenance item
                final VehicleMaintenance newMaintenanceItem = new VehicleMaintenance(vehicle, maintenance, mileage, location, notes, new Date());
                // Insert newMaintenanceItem using maintenanceViewModel
                maintenanceViewModel.insert(newMaintenanceItem).observe(getActivity(), new Observer<String>() {
                    @Override
                    public void onChanged(String vm) {
                        Log.d(TAG, "s" + vm);
                        if (vm.equals("success")) {
                            // Notifies Main Activity so fragments can be swapped
                            newMaintenanceListener.onNewMaintenanceAdded(newMaintenanceItem);
                            Toast.makeText(getActivity(), "Maintenance Item added!", Toast.LENGTH_SHORT).show();
                        } else if (vm.contains("duplicate key")) {
                            Toast.makeText(getActivity(), "You already added that Maintenance Item!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Error adding movie", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNewMaintenanceAddedListener) {
            newMaintenanceListener = (OnNewMaintenanceAddedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNewMaintenanceAddedListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        newMaintenanceListener = null;
    }
}
