package br.edu.iftm.loginapp.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private String baseUrl = "http://192.168.100.6:8080/";
    private ApiEndPoint api;
    private static RetrofitService instance;

    private RetrofitService() {
        api = createRetrofit().create(ApiEndPoint.class);
    }

    public static ApiEndPoint getService() {
        if(instance == null)
            instance = new RetrofitService();
        return instance.api;
    }

    private Retrofit createRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }
}
