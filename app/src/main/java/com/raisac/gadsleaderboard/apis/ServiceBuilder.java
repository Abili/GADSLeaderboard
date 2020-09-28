package com.raisac.gadsleaderboard.apis;

import android.os.Build;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static final String SUBMIT_URL = "https://docs.google.com/forms/d/e/";

    //creater logger
    private static final HttpLoggingInterceptor logger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    //create http client
    private static final OkHttpClient.Builder sOkHttp = new OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(new Interceptor() {
                @NotNull
                @Override
                public Response intercept(@NotNull Chain chain) throws IOException {
                    Request request = chain.request();
                    request = request.newBuilder()
                            .addHeader("x-device-type", Build.DEVICE)
                            .addHeader("Accept-Language", Locale.getDefault().getLanguage())
                            .build();

                    return chain.proceed(request);
                }
            })
            .addInterceptor(logger);

    private static final Retrofit.Builder mBuilder = new Retrofit.Builder()
            .baseUrl(SUBMIT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(sOkHttp.build()); /*tell retrofit to use the interceptor*/


    private static final Retrofit sRetrofit = mBuilder.build();

    public static <S> S buildService(Class<S> buildType) {
        return sRetrofit.create(buildType);
    }

}
