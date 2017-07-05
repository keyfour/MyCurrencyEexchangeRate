package com.github.keyfour.currencyexchangerate.data;

import retrofit2.Retrofit;

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
