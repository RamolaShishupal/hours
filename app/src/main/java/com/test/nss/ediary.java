package com.test.nss;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.test.nss.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

import static com.test.nss.startActivity.AUTH_TOKEN;

public class ediary extends AppCompatActivity {

    public static int blackish;
    public static int transparent;
    public static int primaryCol;
    private CheckConn checkConn;

    AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    Button logout;

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ediary);
        fm = getSupportFragmentManager();

        checkConn = new CheckConn();

        IntentFilter z = new IntentFilter();
        z.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(checkConn, z);

        blackish = this.getColor(R.color.blackish);
        transparent = this.getColor(R.color.transparent);
        primaryCol = this.getColor(R.color.colorPrimary);

        Toast.makeText(this, AUTH_TOKEN, Toast.LENGTH_SHORT).show();
        drawer = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        logout = findViewById(R.id.logoutbutton);

        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_main,
                R.id.nav_acti, R.id.nav_work, R.id.nav_camp, R.id.nav_help)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        logout.setOnClickListener(view -> {
            fm.popBackStackImmediate();

            Call<Void> helpData = RetrofitClient.getInstance().getApi().delToken("Token " + startActivity.AUTH_TOKEN);
            helpData.enqueue(new Callback<Void>() {
                @Override
                @EverythingIsNonNull
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(ediary.this, "Logged Out", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                @EverythingIsNonNull
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("Ediary:logout", t.toString());
                }
            });
            Intent i = new Intent(ediary.this, startActivity.class);
            startActivity(i);
            finish();
            Toast.makeText(ediary.this, "Logged Out!", Toast.LENGTH_SHORT).show();
            AUTH_TOKEN = "";
        });
    }

    // TODO: What the app should do if back pressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void click(View view) {
        Toast.makeText(ediary.this, "Clicked First year", Toast.LENGTH_SHORT).show();
    }

    public void click2(View view) {
        Toast.makeText(ediary.this, "Clicked second year", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(checkConn);
    }
}