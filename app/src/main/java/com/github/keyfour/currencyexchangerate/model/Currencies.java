package com.github.keyfour.currencyexchangerate.model;

import android.util.Log;

import com.github.keyfour.currencyexchangerate.model.pojo.Rates;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Alex Karpov
 */

public class Currencies {

    public static final String names[] = {
            "AUD",
            "BGN",
            "BRL",
            "CAD",
            "CHF",
            "CNY",
            "CZK",
            "DKK",
            "GBP",
            "HKD",
            "HRK",
            "HUF",
            "IDR",
            "ILS",
            "INR",
            "JPY",
            "KRW",
            "MXN",
            "MYR",
            "NOK",
            "NZD",
            "PHP",
            "PLN",
            "RON",
            "RUB",
            "SEK",
            "SGD",
            "THB",
            "TRY",
            "ZAR",
            "EUR",
            "USD"
    };

    public static Double getRate(Rates rates, String name) {
        Double rate = null;
        try {
            Method method = Rates.class.getMethod("get" + name);
            rate = (Double) method.invoke(rates);
        } catch (NoSuchMethodException e) {
            log(e.getMessage());
        } catch (InvocationTargetException e) {
            log(e.getMessage());
        } catch (IllegalAccessException e) {
            log(e.getMessage());
        }

        return rate;
    }

    private static int log(String message) {
        return Log.d(Currencies.class.getSimpleName(), message);
    }

    public static class Mapper {

        public static List<String> map (Rates rates) {
            ArrayList<String> ratesList = new ArrayList<>(Currencies.names.length);
            for (String name : Currencies.names) {
                Double rate = Currencies.getRate(rates, name);
                if (rate != null) {
                    ratesList.add(name + ": " + String.valueOf(rate));
                }
            }
            return ratesList;
        }
    }
}
