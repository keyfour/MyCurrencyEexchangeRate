package com.github.keyfour.currencyexchangerate.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * {@link FixerService} builder and wrapper
 *
 * @author Alex Karpov
 */

public class Fixer {

    private FixerService service;

    private static Fixer instance;

    private Fixer() {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.fixer.io")
                .build();

        service = retrofit.create(FixerService.class);

    }

    public static Fixer getInstance() {
        if (instance == null) {
            instance = new Fixer();
        }
        return instance;
    }

    public FixerService getService() {
        return service;
    }

}
