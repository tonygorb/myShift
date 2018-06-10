package myshift.com;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "JobFragment";

    private FirebaseAuth mAuth;
    private String user;
    private EditText jobName, hourlyRate, bonusHours, breaks, jobAddress;
    private TextView showWeeklyRate;
    private Button btnSave;

    private DatabaseReference current_user_db, db_job_name, db_hourly_rate, db_bonus_hours, db_breaks, db_job_address;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.job_fragment,container,false);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser().getUid();

        current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user).child("User Preferences");

        db_job_name = FirebaseDatabase.getInstance().getReference().child("Users").child(user).child("User Preferences").child("שם העבודה");
        db_job_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jobName.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        db_hourly_rate = FirebaseDatabase.getInstance().getReference().child("Users").child(user).child("User Preferences").child("שכר שעתי");
        db_hourly_rate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hourlyRate.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        db_bonus_hours = FirebaseDatabase.getInstance().getReference().child("Users").child(user).child("User Preferences").child("שעות נוספות");
        db_bonus_hours.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bonusHours.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        db_breaks = FirebaseDatabase.getInstance().getReference().child("Users").child(user).child("User Preferences").child("הפסקות");
        db_breaks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                breaks.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        db_job_address = FirebaseDatabase.getInstance().getReference().child("Users").child(user).child("User Preferences").child("כתובת העבודה");
        db_job_address.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jobAddress.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        jobName = view.findViewById(R.id.et_job_name);
        hourlyRate = view.findViewById(R.id.et_hourly_rate);
        bonusHours = view.findViewById(R.id.et_bonus_hours);
        breaks = view.findViewById(R.id.et_breaks);

        showWeeklyRate = view.findViewById(R.id.tv_show_weekly_rate);
        showWeeklyRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new WeeklyRateFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.job_layout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        jobAddress = view.findViewById(R.id.et_job_address);

        btnSave = view.findViewById(R.id.btn_save_job);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jName = jobName.getText().toString();
                String hRate = hourlyRate.getText().toString();
                String bHours = bonusHours.getText().toString();
                String user_breaks = breaks.getText().toString();
                String jAddress = jobAddress.getText().toString();

                Map newPost = new HashMap();
                newPost.put("שם העבודה", jName);
                newPost.put("שכר שעתי",hRate);
                newPost.put("שעות נוספות",bHours);
                newPost.put("הפסקות",user_breaks);
                newPost.put("כתובת העבודה",jAddress);

                current_user_db.setValue(newPost);
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

}
