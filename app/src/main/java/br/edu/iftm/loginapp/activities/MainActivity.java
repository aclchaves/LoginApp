package br.edu.iftm.loginapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.iftm.loginapp.R;
import br.edu.iftm.loginapp.entities.UserLogin;
import br.edu.iftm.loginapp.entities.UserLoginReturn;
import br.edu.iftm.loginapp.services.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView userText;
    TextView passwordText;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterNewUserActivity.class));
            }
        });

        userText = findViewById(R.id.tv_user_name);
        passwordText =findViewById(R.id.tv_password);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(View view) {
        String user = userText.getText().toString();
        String password = passwordText.getText().toString();

        UserLogin userLogin = new UserLogin(user, password);
        RetrofitService.getService().authenticate(userLogin).enqueue(new Callback<UserLoginReturn>() {
            @Override
            public void onResponse(Call<UserLoginReturn> call, Response<UserLoginReturn> response) {
                Log.d(TAG, "onResponse"+response.body().getToken());
                SharedPreferences sp = getSharedPreferences("user", 0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("token",response.body().getToken());
                editor.apply();
                startActivity(new Intent(MainActivity.this, ListProductActivity.class));
            }

            @Override
            public void onFailure(Call<UserLoginReturn> call, Throwable t) {
                Log.e(TAG,"onFailure: "+t.getMessage());
                Toast.makeText(MainActivity.this, "Erro. Tente novamente!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
