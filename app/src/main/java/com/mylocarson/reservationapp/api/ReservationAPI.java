package com.mylocarson.reservationapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mylocarson.reservationapp.classes.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReservationAPI {
    private final Gson gson = new GsonBuilder()
            .setLenient()
            .create();


//    OkHttpClient.Builder builder = new OkHttpClient.Builder()
//    .addInterceptor(new Interceptor() {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            Response response = chain.proceed(request);
//            return response;
//        }
//    })
//    .connectTimeout(60, TimeUnit.SECONDS)
//    .readTimeout(60, TimeUnit.SECONDS)
//    .writeTimeout(60, TimeUnit.SECONDS);
//    // important line here
//    .addNetworkInterceptor(LoggerInterceptor());


//    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
//        .setLevel(HttpLoggingInterceptor.Level.BODY);
//    OkHttpClient builder = new OkHttpClient.Builder().addInterceptor(interceptor).build();
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(builder)
            .build();

    public ReservationService getNewsService() {
        return retrofit.create(ReservationService.class);
    }

}
