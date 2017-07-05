package com.github.keyfour.currencyexchangerate.feature.rates;

import com.github.keyfour.currencyexchangerate.data.Fixer;
import com.github.keyfour.currencyexchangerate.model.Currencies;
import com.github.keyfour.currencyexchangerate.model.pojo.FixerResponse;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Presenter for currency rates requests
 *
 * @author Alex Karpov
 */

public class RatesPresenter implements RatesContract.Presenter {

    private final RatesContract.View view;

    public RatesPresenter(RatesContract.View view) {
        this.view = view;
    }

    @Override
    public void getRates(String base) {
        view.setLoadIndicator(true);
        Fixer.getInstance().getService().getRates(base)
                .subscribeOn(Schedulers.io())
                .map(FixerResponse::getRates)
                .map(Currencies.Mapper::map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber());
    }

    @Override
    public void cancel() {

    }

    private class Subscriber extends rx.Subscriber<List<String>> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            view.showMessage(e.getLocalizedMessage());
            view.setLoadIndicator(false);
        }

        @Override
        public void onNext(List<String> rates) {
            view.showRates(rates);
            view.setLoadIndicator(false);
        }
    }
}
