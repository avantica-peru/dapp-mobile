package net.avantica.xinef.dapp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.di.components.PublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;
import net.avantica.xinef.dapp.presenter.PublicInvestmentProjectListPresenter;
import net.avantica.xinef.dapp.view.PublicInvestmentProjectListView;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProjectsMapFragment extends BaseFragment implements PublicInvestmentProjectListView, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private static final String LATITUDE_KEY = "latitude_key";
    private static final String LONGITUDE_KEY = "longitude_key";
    private static final int CAMERA_ZOOM = 8;

    private double latitude;
    private double longitude;

    @Inject
    PublicInvestmentProjectListPresenter publicInvestmentProjectListPresenter;

    MapView mapView;
    GoogleMap googleMap;

    private PublicInvestmentProjectListListener publicInvestmentProjectListListener;

    private Unbinder unbinder;

    public ProjectsMapFragment() {
        // Required empty public constructor
        setRetainInstance(true);
    }

    public static ProjectsMapFragment newInstance(double latitude, double longitude) {
        ProjectsMapFragment fragment = new ProjectsMapFragment();
        Bundle args = new Bundle();
        args.putDouble(LATITUDE_KEY, latitude);
        args.putDouble(LONGITUDE_KEY, longitude);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(PublicInvestmentProjectComponent.class).inject(this);

        latitude = getArguments().getDouble(LATITUDE_KEY);
        longitude = getArguments().getDouble(LONGITUDE_KEY);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.publicInvestmentProjectListListener = (PublicInvestmentProjectListListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_project_map, container, false);
        unbinder = ButterKnife.bind(this, view);

        setHasOptionsMenu(true);

        // Gets the MapView from the XML layout and creates it
        this.mapView = (MapView) view.findViewById(R.id.mapview);
        this.mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.publicInvestmentProjectListPresenter.setView(this);

//        if (savedInstanceState == null) {
//            this.loadPublicInvestmentProjectList();
//        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.publicInvestmentProjectListPresenter.setView(this);
        this.loadPublicInvestmentProjectList();

        this.googleMap = googleMap;
        this.googleMap.setOnMarkerClickListener(this);

        this.googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        this.googleMap.getUiSettings().setAllGesturesEnabled(true);
        this.googleMap.getUiSettings().setZoomControlsEnabled(true);
//        googleMap.setMyLocationEnabled(true);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
//        try {
        MapsInitializer.initialize(this.getActivity());
//        } catch (GooglePlayServicesNotAvailableException e) {
//            e.printStackTrace();
//        }

        // Updates the location and zoom of the MapView
        final LatLng latLng = new LatLng(latitude, longitude);
        final CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, CAMERA_ZOOM);
        this.googleMap.animateCamera(cameraUpdate);

//        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    private MarkerOptions getMarkerFromProject(PublicInvestmentProjectModel publicInvestmentProjectModel) {
        final double latitude = Double.valueOf(publicInvestmentProjectModel.getLatitude());
        final double longitude = Double.valueOf(publicInvestmentProjectModel.getLongitude());
        final LatLng latLng = new LatLng(latitude, longitude);

        return new MarkerOptions().position(latLng).title(publicInvestmentProjectModel.getSnipCode());
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (this.publicInvestmentProjectListPresenter != null) {
            this.publicInvestmentProjectListPresenter.onPublicInvestmentProjectClicked(marker.getTitle());
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mapView.onResume();
        this.publicInvestmentProjectListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.mapView.onPause();
        this.publicInvestmentProjectListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mapView.onDestroy();
//        this.publicInvestmentProjectListPresenter.destroy();//TODO edward.carrion si hago el detruir presentador, en la otra vista de listado se pierde el presenter
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.publicInvestmentProjectListListener = null;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        this.mapView.onLowMemory();
    }

    private void loadPublicInvestmentProjectList() {
        this.publicInvestmentProjectListPresenter.initialize(PAGE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void renderPublicInvestmentProjectList(Collection<PublicInvestmentProjectModel> publicInvestmentProjectModelCollection) {
        if (publicInvestmentProjectModelCollection != null) {
            for (PublicInvestmentProjectModel publicInvestmentProjectModel : publicInvestmentProjectModelCollection) {
                MarkerOptions markerOptions = getMarkerFromProject(publicInvestmentProjectModel);
                this.googleMap.addMarker(markerOptions);
            }
        }
    }

    @Override
    public void viewPublicInvestmentProject(String snipCode) {
        this.publicInvestmentProjectListListener.onPublicInvestmentProjectClicked(snipCode);
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
        return this.getActivity().getApplicationContext();
    }
}
