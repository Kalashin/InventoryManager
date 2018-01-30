package kalashin.inventorymanager.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProductModelDao {
    
    @Query("select * from ProductModel ORDER BY prodName ASC")
    LiveData<List<ProductModel>> getAllProducts();

    @Query("select * from ProductModel where prodID = :itemId")
    ProductModel getProductById(int itemId);

    @Query("delete from ProductModel")
    void deleteAllProducts();

    @Insert(onConflict = REPLACE)
    void addProduct(ProductModel ProductModel);

    @Update(onConflict = REPLACE)
    void updateProduct(ProductModel ProductModel);

    @Delete
    void deleteProduct(ProductModel ProductModel);
    
}
