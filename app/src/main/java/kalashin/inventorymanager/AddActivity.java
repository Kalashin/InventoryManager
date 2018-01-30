package kalashin.inventorymanager;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import kalashin.inventorymanager.R;

import kalashin.inventorymanager.db.ProductModel;
import kalashin.inventorymanager.view_model.AddProductViewModel;

public class AddActivity extends AppCompatActivity {

    private EditText editTextProdName;
    private EditText editTextDescription;
    private EditText editTextPrice;
    private EditText editTextCategory;
    private EditText editTextQuantity;
    private EditText editTextMaxQuantity;
    private EditText editTextMinQuantity;
    private Spinner spinnerMeasure;

    private AddProductViewModel addBorrowViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextProdName = findViewById(R.id.editTextProdName);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextCategory = findViewById(R.id.editTextCategory);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        editTextMaxQuantity = findViewById(R.id.editTextMaxQuantity);
        editTextMinQuantity = findViewById(R.id.editTextMinQuantity);
        spinnerMeasure = findViewById(R.id.spinnerMeasure);

        addBorrowViewModel = ViewModelProviders.of(this).get(AddProductViewModel.class);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextProdName.getText() == null
                        || editTextDescription.getText() == null
                        || editTextPrice.getText() == null
                        || editTextCategory.getText() == null
                        || editTextQuantity.getText() == null
                        || editTextMaxQuantity.getText() == null
                        || editTextMinQuantity.getText() == null
                        || spinnerMeasure.getSelectedItem().toString() == null) {
                    Toast.makeText(AddActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
                else {
                    addBorrowViewModel.addProduct(new ProductModel(
                            editTextProdName.getText().toString(),0,
                            editTextDescription.getText().toString(),
                            //Integer.parseInt(editTextPrice.getText().toString()),
                            Float.valueOf(editTextPrice.getText().toString()),
                            editTextCategory.getText().toString(),
                            spinnerMeasure.getSelectedItem().toString(),
                            Integer.parseInt(editTextMinQuantity.getText().toString()),
                            Integer.parseInt(editTextMaxQuantity.getText().toString()),
                            Integer.parseInt(editTextQuantity.getText().toString())
                    ));
                    finish();
                }
            }
        });
    }
}



