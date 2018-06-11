package myshift.com;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeeklyRateFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "WeeklyRate";

    private FirebaseAuth mAuth;
    private String user;
    private DatabaseReference current_user_db, mSunday, mMonday, mTuesday, mWednesday, mThursday, mFriday, mSaturday;
    private String sunday, monday, tuesday, wednesday, thursday, friday, saturday;

    private Button save;

    private Spinner spnrSunday,spnrMonday,spnrTuesday,spnrWednesday,spnrThursday,spnrFriday,spnrSaturday;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weekly_rate_fragment,container,false);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser().getUid();

        current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user).child("Weekly Rate");

        readSunday();
        readMonday();
        readTuesday();
        readWednesday();
        readThursday();
        readFriday();
        readSaturday();

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

        save = view.findViewById(R.id.wr_btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sunday = spnrSunday.getSelectedItem().toString();
                monday = spnrMonday.getSelectedItem().toString();
                tuesday = spnrTuesday.getSelectedItem().toString();
                wednesday = spnrWednesday.getSelectedItem().toString();
                thursday = spnrThursday.getSelectedItem().toString();
                friday = spnrFriday.getSelectedItem().toString();
                saturday = spnrSaturday.getSelectedItem().toString();

                Map newPost = new HashMap();
                newPost.put("יום ראשון", sunday);
                newPost.put("יום שני", monday);
                newPost.put("יום שלישי", tuesday);
                newPost.put("יום רביעי", wednesday);
                newPost.put("יום חמישי", thursday);
                newPost.put("יום שישי", friday);
                newPost.put("יום שבת", saturday);

                current_user_db.setValue(newPost);
                closeFragment();
            }
        });

        return view;
    }






    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText = (TextView) view;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void closeFragment(){
        getActivity().onBackPressed();
    }


    // TODO : Read data from firebase into spinners
    private void readSunday(){
        mSunday = FirebaseDatabase.getInstance().getReference().child("Users").child(user).child("Weekly Rate").child("יום ראשון");
        mSunday.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void readDay(int index){

        ArrayList<Integer> days=new ArrayList<>();
        days.get(index);

    }

    private void readMonday() {

    }

    private void readTuesday() {

    }

    private void readWednesday() {

    }

    private void readThursday() {

    }

    private void readFriday() {

    }

    private void readSaturday() {

    }

}
