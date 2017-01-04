package net.avantica.xinef.dapp.data.repository.datasource;

import net.avantica.xinef.dapp.data.ApplicationTestCase;
import net.avantica.xinef.dapp.data.entity.ReverseGeocodingEntity;
import net.avantica.xinef.dapp.data.net.GeolocationApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CloudReverseGeocodingDataStoreTest extends ApplicationTestCase {
    private static final double LATITUDE_FAKE = -6.25497;
    private static final double LONGITUDE_FAKE = -77.89822;

    private CloudReverseGeocodingDataStore cloudReverseGeocodingDataStore;

    @Mock
    private GeolocationApi mockGeolocationApi;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cloudReverseGeocodingDataStore = new CloudReverseGeocodingDataStore(mockGeolocationApi);
    }

    @Test
    public void shouldGetReverseGeocodingFromApi() {
        ReverseGeocodingEntity fakeReverseGeocodingEntity = new ReverseGeocodingEntity();
        Observable<String> fakeObservable = Observable.just("");
        given(mockGeolocationApi.getDepartmentFromlatitudeLongitude(LATITUDE_FAKE, LONGITUDE_FAKE)).willReturn(fakeObservable);

        cloudReverseGeocodingDataStore.departmentNameFromLatitudeLongitude(LATITUDE_FAKE, LONGITUDE_FAKE);

        verify(mockGeolocationApi).getDepartmentFromlatitudeLongitude(LATITUDE_FAKE, LONGITUDE_FAKE);
    }
}