package myshift.com;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private ListView totalList;

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






        totalList = view.findViewById(R.id.list_exact_date);

        DailyInfo day1 = new DailyInfo("01","גוגל", "08:46","₪ 600");
        DailyInfo day2 = new DailyInfo("02","גוגל", "08:30","₪ 600");
        DailyInfo day3 = new DailyInfo("03","גוגל", "09:20","₪ 600");
        DailyInfo day4 = new DailyInfo("04","גוגל", "08:50","₪ 600");

        ArrayList<DailyInfo> dailyInfoList = new ArrayList<>();
        dailyInfoList.add(day1);
        dailyInfoList.add(day2);
        dailyInfoList.add(day3);
        dailyInfoList.add(day4);

        DailyListAdapter adapter = new DailyListAdapter(getContext(),R.layout.adapter_view_layout,dailyInfoList);
        totalList.setAdapter(adapter);








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
