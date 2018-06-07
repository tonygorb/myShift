package myshift.com;

import android.content.Intent;
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

public class WeeklyRateFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "WeeklyRate";

    private Spinner spnrSunday,spnrMonday,spnrTuesday,spnrWednesday,spnrThursday,spnrFriday,spnrSaturday;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weekly_rate_fragment,container,false);

        spnrSunday = view.findViewById(R.id.spnr_weekly_rate_sunday);
        ArrayAdapter sundayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.weeklyRate,android.R.layout.simple_spinner_item);
        spnrSunday.setAdapter(sundayAdapter);
        spnrSunday.setOnItemSelectedListener(this);

        spnrMonday = view.findViewById(R.id.spnr_weekly_rate_monday);
        ArrayAdapter mondayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.weeklyRate,android.R.layout.simple_spinner_item);
        spnrMonday.setAdapter(mondayAdapter);
        spnrMonday.setOnItemSelectedListener(this);

        spnrTuesday = view.findViewById(R.id.spnr_weekly_rate_tuesday);
        ArrayAdapter tuesdayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.weeklyRate,android.R.layout.simple_spinner_item);
        spnrTuesday.setAdapter(tuesdayAdapter);
        spnrTuesday.setOnItemSelectedListener(this);

        spnrWednesday = view.findViewById(R.id.spnr_weekly_rate_wednesday);
        ArrayAdapter wednesdayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.weeklyRate,android.R.layout.simple_spinner_item);
        spnrWednesday.setAdapter(wednesdayAdapter);
        spnrWednesday.setOnItemSelectedListener(this);

        spnrThursday = view.findViewById(R.id.spnr_weekly_rate_thursday);
        ArrayAdapter thursdayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.weeklyRate,android.R.layout.simple_spinner_item);
        spnrThursday.setAdapter(thursdayAdapter);
        spnrThursday.setOnItemSelectedListener(this);

        spnrFriday = view.findViewById(R.id.spnr_weekly_rate_friday);
        ArrayAdapter fridayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.weeklyRate,android.R.layout.simple_spinner_item);
        spnrFriday.setAdapter(fridayAdapter);
        spnrFriday.setOnItemSelectedListener(this);

        spnrSaturday = view.findViewById(R.id.spnr_weekly_rate_saturday);
        ArrayAdapter saturdayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.weeklyRate,android.R.layout.simple_spinner_item);
        spnrSaturday.setAdapter(saturdayAdapter);
        spnrSaturday.setOnItemSelectedListener(this);

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
