package com.royal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddProductActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText edtProductName;
    EditText edtPrice;
    EditText edtCategory;
    EditText edtQty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //1 : binding
        edtProductName = findViewById(R.id.edtAddProductProductName);
        edtPrice = findViewById(R.id.edtAddProductPrice);
        edtCategory = findViewById(R.id.edtAddProductCategory);
        edtQty =findViewById(R.id.edtAddProductQty);
        btnSubmit = findViewById(R.id.btnAddProductSubmit);

        //click
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //logic
                //read data
                String productName = edtProductName.getText().toString();
                String qty = edtQty.getText().toString();
                String category = edtCategory.getText().toString();
                String price = edtPrice.getText().toString();

                //regex
                String alphaRegEx = "[a-zA-Z\\s]+"; // 1 or N
                String digitRegEx = "[0-9]+";
                //validation
                if(productName.isEmpty()){
                    edtProductName.setError("Please Enter Product Name");
                }

                if(category.isEmpty()){
                    edtCategory.setError("Please Enter Category");
                }else if( !category.matches(alphaRegEx)  ){
                    edtCategory.setError("Category contains only alphabets");
                }

                if(qty.isEmpty()){
                    edtQty.setError("Please Enter Qty");
                }

                if(price.isEmpty()){
                    edtPrice.setError("Please Enter Price");
                }else if(Integer.parseInt(price) <= 0 ){
                    edtPrice.setError("Price Should be Positive value");
                }

                //logic
                //next Activity Display
                //total Price - 1500000
                //
            }
        });



    }
}


