package myshift.com;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ShiftsFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ShiftsFragment";

    TextView date;
    Calendar currentDate = Calendar.getInstance();
    int mYear = currentDate.get(Calendar.YEAR);
    int mMonth = currentDate.get(Calendar.MONTH);
    int mDay = currentDate.get(Calendar.DAY_OF_MONTH);
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM, yyyy",Locale.forLanguageTag("he"));

    String dateTime = new SimpleDateFormat("MMM ,yyyy", Locale.forLanguageTag("he")).format(new Date());

    private Button prev, next, add, delete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shifts_fragment,container,false);

        date = view.findViewById(R.id.tv_shifts_date);
        date.setText(dateTime);

        prev = view.findViewById(R.id.shifts_btn_prev);
        prev.setOnClickListener(this);

        next = view.findViewById(R.id.shifts_btn_next);
        next.setOnClickListener(this);

        add = view.findViewById(R.id.shifts_btn_add);
        add.setOnClickListener(this);

        delete = view.findViewById(R.id.shifts_btn_delete);
        delete.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shifts_btn_prev:
                currentDate.add(Calendar.MONTH,-1);
                dateTime = dateFormat.format(currentDate.getTime());
                date.setText(dateTime);
                break;

            case R.id.shifts_btn_next:
                currentDate.add(Calendar.MONTH,+1);
                dateTime = dateFormat.format(currentDate.getTime());
                date.setText(dateTime);
                break;

            case R.id.shifts_btn_add:

                break;

            case R.id.shifts_btn_delete:

                break;
        }
    }
}
