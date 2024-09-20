package com.example.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText city;
    private EditText age;
    private EditText bornDate;
    private Button saveButton;
    private TextView resultData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editTextUserName);
        city = findViewById(R.id.editTextCity);
        age = findViewById(R.id.editTextAge);
        bornDate = findViewById(R.id.editTextBornDate);
        saveButton = findViewById(R.id.buttonSaveData);
        resultData = findViewById(R.id.tvUsername);

        loadUsername();


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUsername();
            }
        });

    }

    private void saveUsername() {
        String usernameVal = username.getText().toString();
        String cityVal = city.getText().toString();
        String ageVal = age.getText().toString();
        String bornDateVal = bornDate.getText().toString();

        if (!usernameVal.isEmpty() && !cityVal.isEmpty() && !ageVal.isEmpty() && !bornDateVal.isEmpty()) {
            SharedPreferences sharedPreferences = getSharedPreferences("MyUsername", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("username", usernameVal);
            editor.putString("city", cityVal);
            editor.putString("age", ageVal);
            editor.putString("bornDate", bornDateVal);
            editor.apply();

            resultData.setText("Привет, " + usernameVal);

            // Transition to MainActivity2
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }
    }

    private void loadUsername() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyUsername", MODE_PRIVATE);

        String username = sharedPreferences.getString("username", null);
        String city = sharedPreferences.getString("city", null);
        if (username!=null){
            resultData.setText("С возращением, "+ username);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("username");
        editor.remove("city");
        editor.remove("age");
        editor.remove("bornDate");
        editor.commit();
    }
}