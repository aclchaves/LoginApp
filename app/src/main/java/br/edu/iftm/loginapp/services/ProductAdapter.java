package br.edu.iftm.loginapp.services;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.edu.iftm.loginapp.R;
import br.edu.iftm.loginapp.entities.Content;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;

    //data
    List<Content> contentList;

    //Inflater layout - create one view for one list line recycle
    private LayoutInflater mInflater;

    private static final String TAG = "ProductAdapter";


    public ProductAdapter(Context context, List<Content> contentList) {
        this.context = context;
        this.contentList = contentList;
        this.mInflater = LayoutInflater.from(context);
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View line = mInflater.inflate(R.layout.list_item_product_recycler, parent, false);
        return new ProductViewHolder(line, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: "+position);
        Content product = contentList.get(position);

        Glide.with(context)
                .load(product.getImgUrl())
                .into(holder.imageView);
        holder.priceView.setText(String.valueOf(product.getPrice()));
        holder.descriptionView.setText(product.getDescription());
        holder.nameView.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public final TextView nameView;
        public final TextView descriptionView;
        public final TextView priceView;
        public final ImageView imageView;
        public final ProductAdapter mAdapter;

        public ProductViewHolder (@NonNull View itemView, ProductAdapter adapter) {
            super(itemView);
            nameView = itemView.findViewById(R.id.tv_name);
            descriptionView = itemView.findViewById(R.id.tv_description);
            priceView = itemView.findViewById(R.id.tv_price);
            imageView = itemView.findViewById(R.id.iv_url);
            this.mAdapter = adapter;
        }
    }
}

