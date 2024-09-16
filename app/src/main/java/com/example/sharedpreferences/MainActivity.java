package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private Button saveButton;
    private TextView resultData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editTextUserName);
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

    private void saveUsername(){
        String usernameVal = username.getText().toString();
        if (!usernameVal.isEmpty()){
            SharedPreferences sharedPreferences = getSharedPreferences("MyUsername",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("username",usernameVal);
            editor.apply();

            resultData.setText("Привет," + usernameVal);
        }

    }
    private void loadUsername() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyUsername", MODE_PRIVATE);

        String username = sharedPreferences.getString("username", null);
        if (username!=null){
            resultData.setText("С возращением, "+ username);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("username");
        editor.commit();
    }
}