package kalashin.inventorymanager.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kalashin.inventorymanager.R;
import kalashin.inventorymanager.db.ProductModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<ProductModel> ProductModelList;
    private View.OnLongClickListener longClickListener;
    private View.OnClickListener clickListener;

    public RecyclerViewAdapter(List<ProductModel> ProductModelList,
                               View.OnLongClickListener longClickListener,
                               View.OnClickListener clickListener) {
        this.ProductModelList = ProductModelList;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        ProductModel productModel = ProductModelList.get(position);
        holder.textViewProdName.setText(productModel.getProdName());
        holder.textViewDescription.setText(productModel.getProdDescription());
        holder.textViewPrice.setText(""+productModel.getPrice());
        holder.itemView.setTag(productModel);
        holder.itemView.setOnLongClickListener(longClickListener);
        holder.itemView.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return ProductModelList.size();
    }

    public void addItems(List<ProductModel> ProductModelList) {
        this.ProductModelList = ProductModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewProdName;
        private TextView textViewDescription;
        private TextView textViewPrice;

        RecyclerViewHolder(View view) {
            super(view);
            textViewProdName = view.findViewById(R.id.textViewProdName);
            textViewDescription = view.findViewById(R.id.textViewDescription);
            textViewPrice = view.findViewById(R.id.textViewPrice);
        }
    }
}