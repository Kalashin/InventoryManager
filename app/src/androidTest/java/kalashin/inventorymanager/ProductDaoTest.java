package kalashin.inventorymanager;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import kalashin.inventorymanager.db.AppDataBase;
import kalashin.inventorymanager.db.ProductModel;
import kalashin.inventorymanager.db.ProductModelDao;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class ProductDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private ProductModelDao mProductDao;
    private AppDataBase db;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, AppDataBase.class)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build();
        mProductDao = db.productDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void insertAndGetProduct() throws Exception {
        ProductModel product = new ProductModel("Produto",0, "descrição Produto",
                350, "zxcvb", "Mililitro", 20, 50, 25);
        mProductDao.addProduct(product);
        List<ProductModel> allProducts = LiveDataTestUtil.getValue(mProductDao.getAllProducts());
        assertEquals(allProducts.get(0).getProdName(), product.getProdName());
        assertEquals(allProducts.get(0).getProdDescription(), product.getProdDescription());
        assertEquals(allProducts.get(0).getPrice(), product.getPrice());
        assertEquals(allProducts.get(0).getCategory(), product.getCategory());
        assertEquals(allProducts.get(0).getMeasure(), product.getMeasure());
        assertEquals(allProducts.get(0).getMinQuantity(), product.getMinQuantity());
        assertEquals(allProducts.get(0).getMaxQuantity(), product.getMaxQuantity());
        assertEquals(allProducts.get(0).getQuantity(), product.getQuantity());
    }

    @Test
    public void getAllProducts() throws Exception {
        ProductModel product = new ProductModel("aaa",0, "descrição aaa",
                199, "qwerty", "Litro", 0, 10, 5);
        mProductDao.addProduct(product);
        ProductModel product2 = new ProductModel("bbb",0, "descrição bbb",
                10, "asdf", "Unidade", 3, 35, 9);
        mProductDao.addProduct(product2);
        List<ProductModel> allProducts = LiveDataTestUtil.getValue(mProductDao.getAllProducts());
        assertEquals(allProducts.get(0).getProdName(), product.getProdName());
        assertEquals(allProducts.get(1).getProdName(), product2.getProdName());
    }

    @Test
    public void deleteAll() throws Exception {ProductModel product = new ProductModel("aaa",0, "descrição aaa",
            199, "qwerty", "Litro", 0, 10, 5);
        mProductDao.addProduct(product);
        ProductModel product2 = new ProductModel("bbb",0, "descrição bbb",
                10, "asdf", "Unidade", 3, 35, 9);
        mProductDao.deleteAllProducts();
        List<ProductModel> allProducts = LiveDataTestUtil.getValue(mProductDao.getAllProducts());
        assertTrue(allProducts.isEmpty());
    }
}
