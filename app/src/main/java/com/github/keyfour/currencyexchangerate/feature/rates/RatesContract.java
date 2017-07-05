package com.github.keyfour.currencyexchangerate.feature.rates;

import java.util.List;

/**
 * Description
 *
 * @author Alex Karpov
 */

public interface RatesContract {

    interface View {
        void showRates(List<String> rates);
        void showMessage(String message);
        void setLoadIndicator(boolean state);
    }

    interface Presenter {
        void getRates(String base);
        void cancel();
    }
}
