package com.github.keyfour.currencyexchangerate.feature.rates;

import com.github.keyfour.currencyexchangerate.model.pojo.Rates;

/**
 * Description
 *
 * @author Alex Karpov
 */

public interface RatesContract {

    interface View {
        void showRates(Rates rates);
        void showMessage(String message);
        void setLoadIndicator(boolean state);
    }

    interface Presenter {
        void getRates(String base);
        void cancel();
    }
}
