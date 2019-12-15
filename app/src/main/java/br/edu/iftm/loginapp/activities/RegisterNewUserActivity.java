package br.edu.iftm.loginapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.iftm.loginapp.R;
import br.edu.iftm.loginapp.entities.User;
import br.edu.iftm.loginapp.services.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterNewUserActivity extends AppCompatActivity {

    private static final String TAG = "RegisterNewUserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);
    }

    public void Save(View view) {
        String name = ((TextView)findViewById(R.id.et_name)).getText().toString();
        String phone = ((TextView)findViewById(R.id.et_phone)).getText().toString();
        String email = ((TextView)findViewById(R.id.et_email)).getText().toString();
        String password = ((TextView)findViewById(R.id.et_password)).getText().toString();

        User user = new User(email, name, password, phone);

        RetrofitService.getService().registerNewUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "onResponse: "+response.body().getId());
                Toast.makeText(RegisterNewUserActivity.this, "Usuário cadastro com Sucesso. Você será redirecionado para tela de login", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure" +t.getMessage());
            }
        });
    }
}
