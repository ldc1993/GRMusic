package soft.me.ldc.layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soft.me.ldc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicalRadioFragment extends Fragment {


    public MusicalRadioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_musical_radio, container, false);
    }

}
