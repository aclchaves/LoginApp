package br.edu.iftm.loginapp.activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.edu.iftm.loginapp.R;
import br.edu.iftm.loginapp.entities.Content;
import br.edu.iftm.loginapp.entities.Product;
import br.edu.iftm.loginapp.services.ProductAdapter;
import br.edu.iftm.loginapp.services.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProductActivity extends AppCompatActivity {

    private static final String TAG = "ListProductActivity";
    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;
    private List<Content> listProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listProducts = new ArrayList<Content>();
        mRecyclerView = findViewById(R.id.rv_product);

        //Create an adapter and supply the data to be desplayed
        mAdapter = new ProductAdapter(ListProductActivity.this, listProducts);

        //Connect the adapter with the RecycleView
        mRecyclerView.setAdapter(mAdapter);

        //Give the RecycleView a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ListProductActivity.this));
        searchProduct();
    }

    private void updateAdapter(final Product product) {

        mAdapter.setContentList(product.getContents());
        mAdapter.notifyDataSetChanged();
    }

    private void searchProduct() {
        RetrofitService.getService().allProduct().enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                updateAdapter(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }

}
