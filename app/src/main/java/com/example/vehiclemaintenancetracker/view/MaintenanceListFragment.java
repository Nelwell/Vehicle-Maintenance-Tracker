package com.example.vehiclemaintenancetracker.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.vehiclemaintenancetracker.R;

public class MaintenanceListFragment extends Fragment {

    private Spinner mVehicle;

    public interface afterTitleScreenExpires {
        void viewVehicleMaintenanceList();
    }

    public MaintenanceListFragment() {
        // Required empty public constructor
    }

    public static MaintenanceListFragment newInstance() {
        MaintenanceListFragment fragment = new MaintenanceListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maintenance_list, container, false);

        mVehicle = view.findViewById(R.id.vehicle_list);
//        mVehicle.setAdapter();

//        // Gets reference to recycler view's attribute ID in activity_fragment layout file
//        mAutoRecyclerView = view.findViewById(R.id.auto_recycler_view);
//        // Calls layout manager object to linearly display items in list
//        mAutoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        // Creates adapter
//        mAutoAdapter = new AutoAdapter();
//        // Sets adapter in recycler view
//        mAutoRecyclerView.setAdapter(mAutoAdapter);
//
//        mAddAutoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = AutoActivity.newIntent(getActivity(), null);
//                startActivity(intent);
//            }
//        });
        return null;
    }
}
