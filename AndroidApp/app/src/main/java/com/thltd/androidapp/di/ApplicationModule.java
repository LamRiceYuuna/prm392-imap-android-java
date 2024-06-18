package com.thltd.androidapp.di;

import android.content.Context;

import com.thltd.androidapp.R;
import com.thltd.androidapp.di.qualifier.BaseUrl;
import com.thltd.androidapp.utils.AppConstant;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ApplicationModule {
    @Provides
    @Singleton
    public Context providesContext(@ApplicationContext Context context){
        return context;
    }
    @Provides
    @Singleton
    @BaseUrl
    public String provideBaseUrl(){
        return AppConstant.BASE_URL;
    }

}
