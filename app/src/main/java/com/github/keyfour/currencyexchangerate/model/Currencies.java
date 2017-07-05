package com.github.keyfour.currencyexchangerate.model;

import com.github.keyfour.currencyexchangerate.model.pojo.Rates;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    public static Double getRate(Rates rates, String name) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> aClass = Class.forName("Rates");
        Method method = aClass.getMethod("get" + name);
        Double rate = (Double) method.invoke(aClass);
        return rate;
    }

}
