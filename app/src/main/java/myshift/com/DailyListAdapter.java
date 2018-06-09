package myshift.com;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class DailyListAdapter extends ArrayAdapter<DailyInfo> {

    private static final String TAG = "DailyListAdapter";

    private Context mContext;
    int mResource;
    public TextView mDay, mJobName, mTotalHours, mTotalDaily;

    public DailyListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DailyInfo> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    // Getting the view and attaching it to a ListView
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String monthDay = getItem(position).getDayInMonth();
        String jobName = getItem(position).getJobName();
        String totalHours = getItem(position).getTotalHours();
        String totalDaily = getItem(position).getTotalDaily();

        // Create the DailyInfo object with the information
        DailyInfo dailyInfo = new DailyInfo(monthDay,jobName,totalHours,totalDaily);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        mDay = convertView.findViewById(R.id.list_monthDay);
        mJobName = convertView.findViewById(R.id.list_jobName);
        mTotalHours = convertView.findViewById(R.id.list_totalHours);
        mTotalDaily = convertView.findViewById(R.id.list_totalDaily);

        mDay.setText(monthDay);
        mJobName.setText(jobName);
        mTotalHours.setText(totalHours);
        mTotalDaily.setText(totalDaily);

        return convertView;
    }
}
