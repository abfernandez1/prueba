package com.example.pinkcal;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Add_template extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btn_close_template;
    Spinner spinner;
    Button Add;
    LinearLayout layoutAdd;
    EditText edit_name;
    ImageView close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);

        spinner = findViewById(R.id.spinner);

        btn_close_template = findViewById(R.id.btn_close_template);
        btn_close_template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Add = findViewById(R.id.Add);
        layoutAdd = findViewById(R.id.layoutAdd);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView();
            }
        });
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }
    public void onNothingSelected(AdapterView<?> parent) {
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
    private void addView (){
        final View add = getLayoutInflater().inflate(R.layout.new_input, null, false);
        layoutAdd.addView(add);
        edit_name = add.findViewById(R.id.edit_name);
        spinner = add.findViewById(R.id.spinner);
        close = add.findViewById(R.id.close);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(add);
            }
        });
    }
    private void removeView (View view){
        layoutAdd.removeView(view);
    }
}

