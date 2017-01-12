package net.avantica.xinef.dapp.view.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.di.HasComponent;
import net.avantica.xinef.dapp.di.components.PublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.presenter.FilterProjectPresenter;
import net.avantica.xinef.dapp.view.FilterProjectView;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Edward Carrion on 12/30/16.
 * edward.carrion29@gmail.com
 */

public class FilterDialogFragment extends BaseDialogFragment implements FilterProjectView {
    @BindView(R.id.sp_department)
    AppCompatSpinner departmentSpinner;
    @BindView(R.id.sp_province)
    AppCompatSpinner provinceSpinner;
    @BindView(R.id.sp_district)
    AppCompatSpinner districtSpinner;
    @BindView(R.id.sp_populated_center)
    AppCompatSpinner populatedCenterSpinner;
    @BindView(R.id.et_snip_code)
    AppCompatEditText snipCodeEditText;

    private Unbinder unbinder;

    @Inject
    FilterProjectPresenter filterProjectPresenter;

    public static FilterDialogFragment newInstance() {
        FilterDialogFragment filterFragment = new FilterDialogFragment();

        Bundle args = new Bundle();
        filterFragment.setArguments(args);

        return filterFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getComponent(PublicInvestmentProjectComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.filterProjectPresenter.setView(this);
        loadDepartments();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_filter, null);
        builder.setView(view);
        builder.setTitle(R.string.search_filter)
                .setPositiveButton(getString(R.string.apply), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getDialog().dismiss();
                    }
                });

        return builder.create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        this.filterProjectPresenter.destroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.filterProjectPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.filterProjectPresenter.pause();
    }

    private void loadDepartments() {
        this.filterProjectPresenter.getDepartments();
    }

    @Override
    public void renderDepartments(Collection<String> departments) {

    }

    @Override
    public void renderProvinces(Collection<String> departments) {

    }

    @Override
    public void renderDistricts(Collection<String> departments) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return null;
    }

}
