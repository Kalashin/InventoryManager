package kalashin.inventorymanager.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ProductModel")
public class ProductModel {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name= "prodID")
    public int prodID;

    @NonNull
    @ColumnInfo(name = "prodName")
    private String prodName;

    @ColumnInfo(name= "description")
    private String prodDescription;

    @ColumnInfo(name= "price")
    //private int price;
    private float price;

    @ColumnInfo(name= "category")
    private String category;

    @ColumnInfo(name= "measure")
    private String measure;

    @ColumnInfo(name= "minQuantity")
    private int minQuantity;

    @ColumnInfo(name= "maxQuantity")
    private int maxQuantity;

    @ColumnInfo(name= "quantity")
    private int quantity;


    public ProductModel(@NonNull String prodName,
                   @NonNull int prodID,
                   String prodDescription,
                   //int price,
                   float price,
                   String category,
                   String measure,
                   int minQuantity,
                   int maxQuantity,
                   int quantity) {
        this.prodName = prodName;
        this.prodID = prodID;
        this.prodDescription = prodDescription;
        this.price = price;
        this.category = category;
        this.measure = measure;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.quantity = quantity;
    }

    public String getProdName(){return this.prodName;}
    public int getProdID(){return this.prodID;}
    public String getProdDescription(){return this.prodDescription;}
    //public int getPrice(){return this.price;}
    public float getPrice(){return this.price;}
    public String getCategory(){return this.category;}
    public String getMeasure(){return this.measure;}
    public int getMinQuantity(){return this.minQuantity;}
    public int getMaxQuantity(){return this.maxQuantity;}
    public int getQuantity(){return this.quantity;}


}