package com.example.vehiclemaintenancetracker.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vehiclemaintenancetracker.R;

/**
 * Opening Screen upon app startup
 */

public class OpeningFragment extends Fragment {

    public interface OpeningFragmentListener {
        void openingTitleScreen();
    }

    private static final String TAG = "Opening Screen Fragment";

    private TextView mOpeningTitle;
    private OpeningFragmentListener mTitleScreenListener;


    public OpeningFragment() {
        // Required empty public constructor
    }

    public static OpeningFragment newInstance() {
        return new OpeningFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if (context instanceof OpeningFragmentListener) {
            mTitleScreenListener = (OpeningFragmentListener) context;
            Log.d(TAG, "Listener set");
        } else {
            throw new RuntimeException(context.getClass().getName() + " should implement OpeningTitleScreenListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_opener, container, false);

        Log.d(TAG, "onCreateView");

        mOpeningTitle = view.findViewById(R.id.opening_title);

        return view;
    }

}
