package myshift.com;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class JobFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner spnrBonusHours, spnrBreaks;

    private static final String TAG = "JobFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.job_fragment,container,false);

        spnrBonusHours = view.findViewById(R.id.spnr_bonus_hours);

        ArrayAdapter bonusHoursAdapter = ArrayAdapter.createFromResource(getContext(),R.array.bonusHours,android.R.layout.simple_spinner_item);
        spnrBonusHours.setAdapter(bonusHoursAdapter);
        spnrBonusHours.setOnItemSelectedListener(this);


        spnrBreaks = view.findViewById(R.id.spnr_breaks);

        ArrayAdapter breaksAdapter = ArrayAdapter.createFromResource(getContext(),R.array.breaks,android.R.layout.simple_spinner_item);
        spnrBreaks.setAdapter(breaksAdapter);
        spnrBreaks.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText = (TextView) view;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
