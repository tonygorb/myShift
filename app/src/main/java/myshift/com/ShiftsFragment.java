package myshift.com;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
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
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM, yyyy", Locale.forLanguageTag("he"));

    DatabaseReference shiftRecordsRef, db_user;


    UserPrefFb userPref;

//    hourlyRate * totalHours =


    String dateTime = new SimpleDateFormat("MMM ,yyyy", Locale.forLanguageTag("he")).format(new Date());

    private Button prev, next, add, delete;

    private RecyclerView recycler;

    ShiftAdapter adapter;

    private static ShiftsFragment instance;

    public static ShiftsFragment getInstance() {
        if (instance == null) {
            instance = new ShiftsFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.shifts_fragment, container, false);

        recycler = view.findViewById(R.id.list_exact_date);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new ShiftAdapter(new ArrayList<ShiftRecord>());
        recycler.setAdapter(adapter);

        updateShifts();

        Date date1 = Calendar.getInstance().getTime();
        Date date2 = Calendar.getInstance().getTime();

        int sec = (int) ((date1.getTime() - date2.getTime()) / 1000f);


        return view;
    }

    public void updateShifts() {

        Log.e(TAG, "UPDATING");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String user = mAuth.getCurrentUser().getUid();

        shiftRecordsRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user).child("Shift Records");

        DatabaseReference prefsRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user).child("User Preferences");

        prefsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    userPref = ds.getValue(UserPrefFb.class);

                    if (userPref != null) {

                        readShifts();
                    } else {
                        Log.e(TAG, "null pref");
                    }

                    break;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void readShifts() {

        shiftRecordsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<ShiftRecord> shiftRecords = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    ShiftRecord sr = ds.getValue(ShiftRecord.class);
                    shiftRecords.add(sr);

                }

                Log.e(TAG, "shifts size=" + shiftRecords.size());

                populateListView(shiftRecords);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("getGoodsFromFireBase", databaseError.getMessage());

            }
        });

    }

    private void populateListView(ArrayList<ShiftRecord> shiftRecords) {

        adapter.list = shiftRecords;
        adapter.notifyDataSetChanged();

        Log.e(TAG, "adapter list size=" + adapter.list.size());

    }

    @Override
    public void onClick(View v) {

    }

    //region ADAPTER

    class VH extends RecyclerView.ViewHolder {

        TextView dayTV;
        TextView jobNameTV;
        TextView totalHoursTV;
        TextView totalDailyTV;

        public VH(View v) {
            super(v);

            dayTV = v.findViewById(R.id.list_monthDay);
            jobNameTV = v.findViewById(R.id.list_jobName);
            totalHoursTV = v.findViewById(R.id.list_totalHours);
            totalDailyTV = v.findViewById(R.id.list_totalDaily);
        }


    }

    class ShiftAdapter extends RecyclerView.Adapter<VH> {

        ArrayList<ShiftRecord> list;

        public ShiftAdapter(ArrayList<ShiftRecord> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VH(getLayoutInflater().inflate(R.layout.item_test, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {

            ShiftRecord sr = list.get(position);

            holder.dayTV.setText(sr.dateStr);//u can set the rest here

//            Log.e(TAG, "hourlRate=" + userPref.hourlyRate);
//            Log.e(TAG, "totalMillis=" + sr.totalMillis);

            float rate = 0f;
            try {
                Log.e(TAG, "milis=" + sr.totalMillis);

                float second = (sr.totalMillis * 1.0f) / 1000f;

                float minute = second / (60.0f);
                Log.e(TAG, "minute=" + minute);

                float hour = minute / (60f);
                Log.e(TAG, "hour=" + hour);


                rate = userPref.hourlyRate * hour;

                Log.e(TAG, "rate=" + rate);

            } catch (Exception e) {
                e.printStackTrace();
            }

            //here search google
            //format the float
            //format decimal part of float

            holder.totalDailyTV.setText(String.format("%.2f",rate));

//            holder.jobNameTV.setText(sr.);


        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    //endregion
}
