package com.royal;

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

public class InputCalcActivity extends AppCompatActivity {

    EditText edtN1;
    EditText edtN2;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_calc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //binding
        btnAdd = findViewById(R.id.btnInputCalcAdd);
        edtN1= findViewById(R.id.edtInputCalcN1);
        edtN2 = findViewById(R.id.edtInputCalcN2);

        //on click button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strN1 = edtN1.getText().toString();
                String strN2 = edtN2.getText().toString();

                boolean isError = false;

                if(strN1.isEmpty()){
                    isError = true;
                    edtN1.setError("This field is required");
                }

                if(strN2.isEmpty()){
                    isError = true;
                    edtN2.setError("This field is required");
                }


                if(!isError){

                    int n1 = Integer.parseInt(strN1);
                    int n2 = Integer.parseInt(strN2);

                    int ans = n1 + n2 ;

                    //alert -> Toast
                    Toast.makeText(getApplicationContext(),"Addition => "+ans,Toast.LENGTH_LONG).show();

                    //navigate to the next activity
                    Intent intent = new Intent(getApplicationContext(), OutputCalcActivity.class);
                    intent.putExtra("ans",ans);
                    startActivity(intent);

                }

                 }
        });

    }



}