package mb.ganesh.googlemapapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class LocationActivity extends AppCompatActivity {

    MaterialButton pickLocBtn;
    TextView textLoc;
    String result = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);


        pickLocBtn = findViewById(R.id.pickLocBtnId);
        textLoc = findViewById(R.id.textLocId);


        if (!isLocationPermissionGranted()) {
            requestLocationPermission();
        }else {
            pickLocBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LocationActivity.this , MainActivity.class));
                    finish();
                }
            });
        }


    }

    private boolean isLocationPermissionGranted() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public void requestLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
    }

    @Override
    protected void onResume() {
        super.onResume();
        result = getIntent().getStringExtra("ad");
        if (TextUtils.isEmpty(result)){
            textLoc.setText("Pick your location");
        }else {
            textLoc.setText(result);
        }
    }

}