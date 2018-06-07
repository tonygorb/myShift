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
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CurrentShiftNightFragment extends Fragment {

    private static final String TAG = "CurrentShiftNightFragment";

    private TextView tvTime, tvDate, btnSwitch;
    private SimpleDateFormat mSimpleDateFormatTime;
    private Calendar mCalendar;
    private String time, date;
    private Chronometer timer;
    private ImageButton btnStart;

    private boolean running;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.current_shift_night_fragment,container,false);

        tvTime = view.findViewById(R.id.night_time);
        tvDate = view.findViewById(R.id.night_date);
        btnSwitch = view.findViewById(R.id.night_tv_shift);
        timer = view.findViewById(R.id.night_cron_timer);
        btnStart = view.findViewById(R.id.night_btn_start_shift);

        mCalendar = Calendar.getInstance();
        mSimpleDateFormatTime = new SimpleDateFormat("HH:mm");
        time = mSimpleDateFormatTime.format(mCalendar.getTime());

        date = new SimpleDateFormat("MMM ,yyyy", Locale.getDefault()).format(new Date());

        tvTime.setText(time);
        tvDate.setText(date);

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

        btnStart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                chronometer(view);

                if (running){
                    btnSwitch.setEnabled(false);
                    running = true;
                } else {
                    btnSwitch.setEnabled(true);
                    running = false;
                }
                return true;
            }
        });

        return view;
    }

    public void chronometer(View view) {
        if (!running){
            timer.setBase(SystemClock.elapsedRealtime());
            timer.start();
            running = true;
        } else if (running){
            timer.stop();
            timer.setBase(SystemClock.elapsedRealtime());
            running = false;
        }
        return;
    }
}
