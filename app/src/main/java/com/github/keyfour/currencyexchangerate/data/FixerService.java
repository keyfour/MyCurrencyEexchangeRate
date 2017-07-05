package com.github.keyfour.currencyexchangerate.data;

import com.github.keyfour.currencyexchangerate.model.pojo.FixerResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Interface for api requests
 *
 * @author Alex Karpov
 */

public interface FixerService {

    @GET("/latest")
    Observable<FixerResponse> getRates(@Query("base") String base);

}
