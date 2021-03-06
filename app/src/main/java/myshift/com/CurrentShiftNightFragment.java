package myshift.com;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CurrentShiftNightFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "CurrentShiftNightFragment";

    private FirebaseAuth mAuth;
    private String user;
    private DatabaseReference current_user_db, db_start_time, db_end_time, db_total_time;

    private TextView tvTime, tvDate, btnSwitch, startTime, endTime, totalHours;
    private Button startShiftButton;
    private SimpleDateFormat mSimpleDateFormatTime, simpleTimeFormat;
    private Calendar mCalendar, startCalendar, endCalendar;
    private String time, date;
    private Chronometer mChronometer;
    private static String shiftDate, shiftStartTime, shiftEndTime;

    private boolean running = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.current_shift_night_fragment,container,false);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser().getUid();
        current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user).child("Shift Records");

        tvTime = view.findViewById(R.id.night_time);
        tvDate = view.findViewById(R.id.night_date);
        btnSwitch = view.findViewById(R.id.night_tv_shift);

        mChronometer = view.findViewById(R.id.night_chronometer);
        startShiftButton = view.findViewById(R.id.night_startShiftButton);
        startTime = view.findViewById(R.id.night_startTimeTV);
        endTime = view.findViewById(R.id.night_endTimeTV);
        totalHours = view.findViewById(R.id.night_total);
        mCalendar = Calendar.getInstance();
        mSimpleDateFormatTime = new SimpleDateFormat("HH:mm");
        simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
        time = mSimpleDateFormatTime.format(mCalendar.getTime());

        date = new SimpleDateFormat("MMM ,yyyy", Locale.getDefault()).format(new Date());

        tvTime.setText(time);
        tvDate.setText(date);

        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new CurrentShiftDayFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.night_layout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        startShiftButton.setOnClickListener(this);

        return view;
    }

    private void calculatedHours() {
        Date startShift = null;
        Date endShift = null;

        try {
            startShift = simpleTimeFormat.parse(shiftStartTime);
            endShift = simpleTimeFormat.parse(shiftEndTime);

            long diff = endShift.getTime() - startShift.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;

            totalHours.setText("Total Shift Time" + ": " + diffHours + ":" + diffMinutes + ":" + diffSeconds);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (running){
            startCalendar = Calendar.getInstance();
            shiftStartTime = simpleTimeFormat.format(startCalendar.getTime());
            startTime.setText(shiftStartTime);
            startShiftButton.setText("STOP");

            mChronometer.setBase(SystemClock.elapsedRealtime());
            mChronometer.start();

            running = false;
            return;
        }

        if (!running ) {
            endCalendar = Calendar.getInstance();
            shiftEndTime = simpleTimeFormat.format(endCalendar.getTime());
            endTime.setText(shiftEndTime);
            startShiftButton.setText("START");
            try {
                calculatedHours();
            } catch (Exception e) {
                e.printStackTrace();
            }

            ShiftRecord sr = new ShiftRecord();
            sr.dateStr = mCalendar.getTime().toString();
            sr.startDate = startTime.getText().toString();
            sr.endDate = endTime.getText().toString();
//            sr.totalMillis = totalHours.getText().toString();

            current_user_db.push().setValue(sr);

            mChronometer.stop();

            running = true;
            return;
        }
    }
}

