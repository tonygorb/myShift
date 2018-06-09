package myshift.com;

import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CurrentShiftNightFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "CurrentShiftNightFragment";
    private TextView newShiftDate, startTime, endTime, totalHours,currentTime;
    private Button startShiftButton , btnSwitch;
    private Calendar calendar, startCalendar, endCalendar;
    private SimpleDateFormat simpleDateFormat, simpleTimeFormat , simpleCurrentTimeFormat;
    private boolean isStartedShift = true;
    public static String shiftDate, shiftStartTime, shiftEndTime;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.current_shift_night_fragment,container,false);

        newShiftDate =  view.findViewById(R.id.startDateTV);
        startTime = view.findViewById(R.id.startTimeTV);
        endTime = view.findViewById(R.id.endTimeTV);
        totalHours = view.findViewById(R.id.total);
        currentTime = view.findViewById(R.id.currentTime);

        startShiftButton = view.findViewById(R.id.startShiftButton);

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
        simpleTimeFormat = new SimpleDateFormat("HH:mm:ss a");
        simpleCurrentTimeFormat = new SimpleDateFormat("HH:mm:ss");
        shiftDate = simpleDateFormat.format(calendar.getTime());
        currentTime.setText(simpleCurrentTimeFormat.format(calendar.getTime()));

        newShiftDate.setText(shiftDate);



        startShiftButton.setOnClickListener(this);


        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new CurrentShiftDayFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.night_shift_layout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        return view;
    }

    public void calculatedHours() throws ParseException {
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

    public void onClick(View v) {
        if (isStartedShift){
            startCalendar = Calendar.getInstance();
            shiftStartTime = simpleTimeFormat.format(startCalendar.getTime());
            startTime.setText(shiftStartTime);
            startShiftButton.setText("STOP");
            isStartedShift = false;
            return;
        }

        if (!isStartedShift ) {
            endCalendar = Calendar.getInstance();
            shiftEndTime = simpleTimeFormat.format(endCalendar.getTime());
            endTime.setText(shiftEndTime);
            startShiftButton.setText("START");
            try {
                calculatedHours();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

}

