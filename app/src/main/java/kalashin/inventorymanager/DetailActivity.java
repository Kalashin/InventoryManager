package kalashin.inventorymanager;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import kalashin.inventorymanager.R;

import kalashin.inventorymanager.db.ProductModel;
import kalashin.inventorymanager.db.ProductModelDao;
import kalashin.inventorymanager.view_model.ProductListViewModel;
import kalashin.inventorymanager.view_model.UpdateProductViewModel;

public class DetailActivity extends AppCompatActivity {

    private ProductListViewModel viewModel;
    private ProductModelDao productModelDao;

    private TextView textViewProdName;
    private TextView textViewDescription;
    private TextView textViewPrice;
    private TextView textViewCategory;
    private TextView textViewQuantity;
    private TextView textViewMaxQuantity;
    private TextView textViewMinQuantity;
    private TextView textViewMeasure;
    private int prodID = 0;

    private UpdateProductViewModel updateProductViewModel;
    private ProductModel productModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);

        textViewProdName = findViewById(R.id.textViewProdName);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewPrice = findViewById(R.id.textViewPrice);
        textViewCategory = findViewById(R.id.textViewCategory);
        textViewQuantity = findViewById(R.id.textViewQuantity);
        textViewMaxQuantity = findViewById(R.id.textViewMaxQuantity);
        textViewMinQuantity = findViewById(R.id.textViewMinQuantity);
        textViewMeasure = findViewById(R.id.textViewMeasure);

        updateProductViewModel = ViewModelProviders.of(this).get(UpdateProductViewModel.class);

        Intent i = getIntent();
        if (i != null) {
            productModel = updateProductViewModel.readProduct(i.getIntExtra("prodID",0));

            textViewProdName.setText(productModel.getProdName());
            textViewDescription.setText(productModel.getProdDescription());
            textViewPrice.setText(""+productModel.getPrice());
            textViewCategory.setText(productModel.getCategory());
            textViewMeasure.setText(productModel.getMeasure());
            textViewMinQuantity.setText(""+productModel.getMinQuantity());
            textViewMaxQuantity.setText(""+productModel.getMaxQuantity());
            textViewQuantity.setText(""+productModel.getQuantity());
            prodID = productModel.getProdID();

        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //productModel = (ProductModel) view.getTag();
                viewModel.deleteItem(productModel);
                finish();
            }
        });
    }
}
