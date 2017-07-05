package com.github.keyfour.currencyexchangerate.feature.rates;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.keyfour.currencyexchangerate.R;
import com.github.keyfour.currencyexchangerate.model.Currencies;

import java.util.List;

/**
 * {@link Fragment} for interacting with rates
 *
 * @author Alex Karpov
 */

public class RatesFragment extends Fragment implements RatesContract.View {

    private final RatesContract.Presenter presenter;

    private TextView textViewRates;
    private ProgressDialog progressDialog;


    public RatesFragment() {
        presenter = new RatesPresenter(this);
    }

    public static RatesFragment newInstance() {
        return new RatesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rates, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1);
        adapter.addAll(Currencies.names);

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView)
                view.findViewById(R.id.actv_base);
        autoCompleteTextView.setAdapter(adapter);

        Button button = (Button) view.findViewById(R.id.b_get_rates);
        button.setOnClickListener(view1 ->
                presenter.getRates(autoCompleteTextView.getText().toString()));

        textViewRates = (TextView) view.findViewById(R.id.tv_rates);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle(getString(R.string.loading));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setOnCancelListener(dialogInterface -> presenter.cancel());

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancel();
    }

    @Override
    public void showRates(List<String> rates) {
        textViewRates.setText("");
        for (String rate : rates) {
            textViewRates.append(rate + "\n\n");
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLoadIndicator(boolean state) {
        if (state) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }
}
