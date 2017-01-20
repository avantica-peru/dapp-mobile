package net.avantica.xinef.dapp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout.LayoutParams;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.di.components.PublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.model.UbigeoModel;
import net.avantica.xinef.dapp.presenter.UbigeoPresenter;
import net.avantica.xinef.dapp.view.UbigeoView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;

/**
 * Created by Edward Carrion on 12/30/16.
 * edward.carrion29@gmail.com
 */

public class FilterDialogFragment extends BaseDialogFragment implements UbigeoView {
    @BindView(R.id.sp_department)
    AppCompatSpinner departmentSpinner;
    @BindView(R.id.sp_province)
    AppCompatSpinner provinceSpinner;
    @BindView(R.id.sp_district)
    AppCompatSpinner districtSpinner;
    @BindView(R.id.et_snip_code)
    AppCompatEditText snipCodeEditText;

    private Unbinder unbinder;

    @Inject
    UbigeoPresenter filterProjectPresenter;

    OnFiltersSelectedListener filterSelectedCallback;

    public interface OnFiltersSelectedListener {
        void onUbigeoEntered(String ubigeo);
    }

    public static FilterDialogFragment newInstance() {
        FilterDialogFragment filterFragment = new FilterDialogFragment();

        Bundle args = new Bundle();
        filterFragment.setArguments(args);

        return filterFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            filterSelectedCallback = (OnFiltersSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
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
        setCancelable(false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.filterProjectPresenter.setView(this);
        loadDepartments();
    }

    @Override
    public void onStart() {
        super.onStart();

        getDialog().getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
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

    private void loadProvinces(String codDepartment) {
        this.filterProjectPresenter.getProvinces(codDepartment);
    }

    private void loadDistricts(String codDepartment, String codProvince) {
        this.filterProjectPresenter.getDistricts(codDepartment, codProvince);
    }

    @Override
    public void renderDepartments(Collection<UbigeoModel> departments) {
        ArrayList<UbigeoModel> list = (ArrayList<UbigeoModel>) departments;
        ArrayAdapter<UbigeoModel> dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(dataAdapter);
    }

    @Override
    public void renderProvinces(Collection<UbigeoModel> provincies) {
        ArrayAdapter<UbigeoModel> dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, (List<UbigeoModel>) provincies);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceSpinner.setAdapter(dataAdapter);
        provinceSpinner.setEnabled(true);
    }

    @Override
    public void renderDistricts(Collection<UbigeoModel> districts) {
        ArrayAdapter<UbigeoModel> dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, (List<UbigeoModel>) districts);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtSpinner.setAdapter(dataAdapter);
        districtSpinner.setEnabled(true);
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

    @OnItemSelected(R.id.sp_department)
    void onDepartmentSelected(int position) {
        UbigeoModel deparmtent = (UbigeoModel) departmentSpinner.getSelectedItem();
        loadProvinces(deparmtent.getCodDpto());
    }

    @OnItemSelected(R.id.sp_province)
    void onProvinceSelected(int position) {
        UbigeoModel department = (UbigeoModel) departmentSpinner.getSelectedItem();
        UbigeoModel province = (UbigeoModel) provinceSpinner.getSelectedItem();
        loadDistricts(department.getCodDpto(), province.getCodProv());
    }

    @OnClick(R.id.btn_cancel)
    void onCancel() {
        dismiss();
    }

    @OnClick(R.id.btn_apply)
    void onApply() {
        UbigeoModel ubigeoModel = (UbigeoModel) districtSpinner.getSelectedItem();
        String ubigeo = ubigeoModel.getCodDpto() + ubigeoModel.getCodProv() + ubigeoModel.getCodDist();
        filterSelectedCallback.onUbigeoEntered(ubigeo);
    }
}
