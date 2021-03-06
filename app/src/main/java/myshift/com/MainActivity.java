package myshift.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        };

        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // App Language
        String languageToLoad = "he";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);

        // Default Tab
        mViewPager.setCurrentItem(1);

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new JobFragment(), "עבודה");
        adapter.addFragment(new CurrentShiftDayFragment(), "משמרת נוכחית");
        adapter.addFragment(ShiftsFragment.getInstance(), "משמרות");
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logOutBtn:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));

                break;
        }

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    // When [BACK BUTTON] pressed //
    public void onBackPressed() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        // if any [FRAGMENT] is open, back button will come back for it's previous fragment
        if (fragmentManager.getBackStackEntryCount() > 0) {
            Log.i("Main Activity", "popping backstack");
            fragmentManager.popBackStack();
        }
        // if no [FRAGMENT] is open, back button will close the app
        else {
            Log.i("Main Activity", "nothing on backstack, calling super");

            Intent finish = new Intent(Intent.ACTION_MAIN);
            finish.addCategory(Intent.CATEGORY_HOME);
            finish.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(finish);
        }
    }
}
