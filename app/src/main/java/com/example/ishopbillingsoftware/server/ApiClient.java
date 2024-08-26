package com.example.ishopbillingsoftware.server;


import com.example.ishopbillingsoftware.R;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static Retrofit retrofit = null;

    private static final String api_base_url = App.getRes().getString(R.string.api_base_url);

    private static Retrofit getRetrofit() {


        Retrofit retrofit = new Retrofit.Builder().baseUrl(api_base_url).
                addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }

   /* public static Retrofit getClient(String token) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    okhttp3.Request original = chain.request();
                    okhttp3.Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", token);
                    okhttp3.Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .build();

    return null;
    }*/

    public static Retrofit getClient(String token) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    okhttp3.Request original = chain.request();
                    okhttp3.Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", token);
                    okhttp3.Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .build();

        return new Retrofit.Builder()
                .baseUrl(api_base_url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static UserService getUserService() {
        UserService userService = getRetrofit().create(UserService.class);
        return userService;
    }

   /* public static Retrofit getClient(final String token) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer " + token); // Add your token here
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return null;
    }*/
}