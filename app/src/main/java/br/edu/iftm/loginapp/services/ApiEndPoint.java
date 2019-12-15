package br.edu.iftm.loginapp.services;

import br.edu.iftm.loginapp.entities.Product;
import br.edu.iftm.loginapp.entities.User;
import br.edu.iftm.loginapp.entities.UserLogin;
import br.edu.iftm.loginapp.entities.UserLoginReturn;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiEndPoint {

    @POST("auth/login")
    Call<UserLoginReturn> authenticate(@Body UserLogin userLogin);

    @POST("users")
    Call<User> registerNewUser(@Body User user);

    @GET("products")
    Call<Product> allProduct();
}
