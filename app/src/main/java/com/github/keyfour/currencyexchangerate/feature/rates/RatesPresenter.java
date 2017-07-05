package com.github.keyfour.currencyexchangerate.feature.rates;

import com.github.keyfour.currencyexchangerate.data.Fixer;
import com.github.keyfour.currencyexchangerate.model.pojo.FixerResponse;

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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber());
    }

    @Override
    public void cancel() {

    }

    private class Subscriber extends rx.Subscriber<FixerResponse> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            view.showMessage(e.getLocalizedMessage());
            view.setLoadIndicator(false);
        }

        @Override
        public void onNext(FixerResponse fixerResponse) {
            view.showRates(fixerResponse.getRates());
            view.setLoadIndicator(false);
        }
    }
}
