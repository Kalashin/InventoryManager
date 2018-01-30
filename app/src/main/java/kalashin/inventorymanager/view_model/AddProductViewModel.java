package kalashin.inventorymanager.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import kalashin.inventorymanager.db.AppDataBase;
import kalashin.inventorymanager.db.ProductModel;

public class AddProductViewModel extends AndroidViewModel {

    private AppDataBase appDatabase;

    public AddProductViewModel(Application application) {
        super(application);
        appDatabase = AppDataBase.getDatabase(this.getApplication());
    }

    public void addProduct(final ProductModel borrowModel) {
        new addAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class addAsyncTask extends AsyncTask<ProductModel, Void, Void> {

        private AppDataBase db;

        addAsyncTask(AppDataBase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final ProductModel... params) {
            db.productDao().addProduct(params[0]);
            return null;
        }

    }
}
