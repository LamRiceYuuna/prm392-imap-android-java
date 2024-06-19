package com.thltd.androidapp.di;

import android.content.Context;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.thltd.androidapp.R;
import com.thltd.androidapp.data.api.UserRepository;
import com.thltd.androidapp.di.qualifier.BaseUrl;

import java.time.Duration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    public ObjectMapper providesObjectMapper(){
        return JsonMapper.builder().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true).build();
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit(OkHttpClient okHttpClient, @BaseUrl String baseUrl, ObjectMapper objectMapper){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient(){
        var duration = Duration.ofSeconds(30);
        return new OkHttpClient.Builder()
                .connectTimeout(duration)
                .readTimeout(duration)
                .writeTimeout(duration)
                .build();
    }

    @Provides
    @Singleton
    public UserRepository providesUserRepository(Retrofit retrofit){
        return retrofit.create(UserRepository.class);
    }
}
