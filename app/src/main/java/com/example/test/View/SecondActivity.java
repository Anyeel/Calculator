package com.example.test.View;

import android.content.Intent;
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

import java.lang.reflect.Array;

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

        EditText UserText;
        EditText PassText;
        Button SubmitButton;

        SubmitButton = (Button)findViewById(R.id.buttonSubmit);
        PassText = (EditText)findViewById(R.id.editTextPass);
        UserText = (EditText)findViewById(R.id.editTextUser);

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserText.getText().toString().isEmpty() && PassText.getText().toString().isEmpty()){
                    Toast.makeText(SecondActivity.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    String Pass = PassText.getText().toString();
                    String User = UserText.getText().toString();
                    if (Pass.toCharArray().length >= 4 && Pass.toCharArray().length <= 10 && User.toCharArray().length >= 4 && User.toCharArray().length <= 10){
                        for (int i = 0; i < Pass.toCharArray().length; i++ ){
                            char c = Pass.toCharArray()[i];
                            if (Pass.matches("^[a-zA-Z0-9]+$")) {
                                System.out.println("Entrada válida");
                                Toast.makeText(SecondActivity.this, "Logueado", Toast.LENGTH_SHORT).show();

                                Bundle data = new Bundle();
                                data.putString("Username", UserText.getText().toString());

                                startActivity(new Intent(SecondActivity.this, ThirdActivity.class).putExtras(data));
                            } else {
                                Toast.makeText(SecondActivity.this, "Solo se permiten letras y números", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else {
                        Toast.makeText(SecondActivity.this, "Deben de tener entre 4 y 10 caracteres inclusive", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    // Que el login que tenemos creado solo acepte letras y números
    // Nada de simbolos ni caracteres extraños o emojis
    // Además, la contraseña y el usuario debe contener entre 4 y 10 caracteres inclusive.
    // La contraseña debe ser encriptada en la clase modelo con un cifrado a elegir por el
    // desarrollador (César, ASCII...) de forma automática. (Esto se debe hacer en el controlador)
}