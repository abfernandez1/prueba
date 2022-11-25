package com.example.pinkcal;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.ArrayMap;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

public class Logup extends AppCompatActivity {
    EditText email;
    EditText password;
    EditText confirmpassword;
    private var fireStore = FirebaseFirestore.getInstance();
    CheckBox checkbox;
    Button btn_signUp;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logup);

        fireStore.collection("users").document(email.getText().toString()).set(ArrayMap(
                "email", email.getText().toString(),
                "password", password.getText().toString(),

        ))
    }
/*
        //mostrar y ocultar contraseña
        password = findViewById(R.id.txtPassUsu);
        confirmpassword = findViewById(R.id.confirmpassword);
        checkbox = findViewById(R.id.checkbox);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    confirmpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    password.setSelection(password.getText().length());
                    confirmpassword.setSelection(password.getText().length());
                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    confirmpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password.setSelection(password.getText().length());
                    confirmpassword.setSelection(password.getText().length());
                }
            }
        } );
    }

        // ir al MainActivity
        public void MainActivity(View view) {
        Intent MainActivity = new Intent(Logup.this, MainActivity.class);
        startActivity(MainActivity);

        }

 */
}