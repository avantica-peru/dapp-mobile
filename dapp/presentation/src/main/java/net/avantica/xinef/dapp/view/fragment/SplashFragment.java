package net.avantica.xinef.dapp.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.di.components.PublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.presenter.ReverseGeocodingPresenter;
import net.avantica.xinef.dapp.view.ReverseGeocodingView;

import javax.inject.Inject;

public class SplashFragment extends BaseFragment implements ReverseGeocodingView {

    public interface SplashListener {
        void success(String departmentName);

        void error();
    }

    @Inject
    ReverseGeocodingPresenter reverseGeocodingPresenter;

    private SplashListener splashListener;

    public SplashFragment() {
        // Required empty public constructor
    }

    public static SplashFragment newInstance() {
        SplashFragment splashFragment = new SplashFragment();
        return splashFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.splashListener = (SplashListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getComponent(PublicInvestmentProjectComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.reverseGeocodingPresenter.setView(this);

        if (savedInstanceState == null) {
            this.loadDepartmentName();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        this.splashListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.reverseGeocodingPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.reverseGeocodingPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.reverseGeocodingPresenter.destroy();
    }

    private void loadDepartmentName() {
        this.reverseGeocodingPresenter.initialize();
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
        showToastMessage(message);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    @Override
    public void getDepartmentName(String departmentName) {
        splashListener.success(departmentName);
    }
}
