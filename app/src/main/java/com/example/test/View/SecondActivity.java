package com.example.test.View;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;
import com.example.test.Controller.Cifrado;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText UserText = findViewById(R.id.editTextUser);
        EditText PassText = findViewById(R.id.editTextPass);
        Button SubmitButton = findViewById(R.id.buttonSubmit);

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = UserText.getText().toString();
                String Pass = PassText.getText().toString();

                UserText.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                PassText.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));

                if (!(User.length() >= 4 && User.length() <= 10 && User.matches("^[a-zA-Z0-9]+$"))) {
                    UserText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FA6862")));
                    UserText.setTextColor(Color.parseColor("#FA6862"));
                } else {
                    UserText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#91F9AE")));
                    UserText.setTextColor(Color.parseColor("#91F9AE"));
                }

                if (!(Pass.length() >= 4 && Pass.length() <= 10 && Pass.matches("^[a-zA-Z0-9]+$"))) {
                    PassText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FA6862")));
                    PassText.setTextColor(Color.parseColor("#FA6862"));
                } else {
                    PassText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#91F9AE")));
                    PassText.setTextColor(Color.parseColor("#91F9AE"));
                }

                if ((User.length() >= 4 && User.length() <= 10 && User.matches("^[a-zA-Z0-9]+$")) && (Pass.length() >= 4 && Pass.length() <= 10 && Pass.matches("^[a-zA-Z0-9]+$"))) {
                    sendUserData(User, Pass);
                } else {
                    Toast.makeText(SecondActivity.this, "Solo se permiten letras y números, ambos entre 4 y 10 caracteres inclusive.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void sendUserData(String user, String pass){
        String passCifrada = Cifrado.cifrarASCII(pass);
        Toast.makeText(SecondActivity.this, "Logueado", Toast.LENGTH_SHORT).show();

        Bundle data = new Bundle();
        data.putString("Username", user);
        data.putString("Contraseña", passCifrada);

        startActivity(new Intent(SecondActivity.this, ThirdActivity.class).putExtras(data));
    }
}
