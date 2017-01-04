package net.avantica.xinef.dapp.data.repository;

import net.avantica.xinef.dapp.data.repository.datasource.ReverseGeocodingDataStore;
import net.avantica.xinef.dapp.data.repository.datasource.ReverseGeocodingDataStoreFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReverseGeocodingDataRepositoryTest {
    private static final double LATITUDE_FAKE = -6.25497;
    private static final double LONGITUDE_FAKE = -77.89822;

    private ReverseGeocodingDataRepository reverseGeocodingDataRepository;

    @Mock
    private ReverseGeocodingDataStoreFactory mockReverseGeocodingDataStoreFactory;
    @Mock
    private ReverseGeocodingDataStore mockReverseGeocodingDataStore;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reverseGeocodingDataRepository = new ReverseGeocodingDataRepository(mockReverseGeocodingDataStoreFactory);
        given(mockReverseGeocodingDataStoreFactory.createCloudDataStore()).willReturn(mockReverseGeocodingDataStore);
    }

    @Test
    public void shouldGetReverGeocodingHappyCase() {
        given(mockReverseGeocodingDataStore.departmentNameFromLatitudeLongitude(LATITUDE_FAKE, LONGITUDE_FAKE)).willReturn(Observable.just(""));

        reverseGeocodingDataRepository.departmentNamefromlatitudeLongitude(LATITUDE_FAKE, LONGITUDE_FAKE);

        verify(mockReverseGeocodingDataStoreFactory).createCloudDataStore();
        verify(mockReverseGeocodingDataStore).departmentNameFromLatitudeLongitude(LATITUDE_FAKE, LONGITUDE_FAKE);
    }
}