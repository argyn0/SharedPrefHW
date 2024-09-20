package com.example.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    TextView username;
    TextView city;
    TextView age;
    TextView bornDate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        username = findViewById(R.id.textViewName);
        city = findViewById(R.id.textViewCity);
        age = findViewById(R.id.textViewAge);
        bornDate = findViewById(R.id.textViewBornDate);

//        loadUsername();
        setData();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyUsername", MODE_PRIVATE);
        username.setText(sharedPreferences.getString("username", null));
        city.setText(sharedPreferences.getString("city", null));
        age.setText(sharedPreferences.getString("age", null));
        bornDate.setText(sharedPreferences.getString("bornDate", null));
    }

    private void loadUsername() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyUsername", MODE_PRIVATE);

        String username = sharedPreferences.getString("username", null);
        String city = sharedPreferences.getString("city", null);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("username");
        editor.remove("city");
        editor.commit();
    }
}