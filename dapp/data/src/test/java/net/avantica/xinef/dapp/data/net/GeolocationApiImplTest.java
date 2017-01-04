package net.avantica.xinef.dapp.data.net;

import android.support.annotation.NonNull;

import net.avantica.xinef.dapp.data.entity.AddressComponent;
import net.avantica.xinef.dapp.data.entity.ReverseGeocodingEntity;
import net.avantica.xinef.dapp.data.entity.ReverseGeocodingResult;
import net.avantica.xinef.dapp.data.entity.mapper.ReverseGeocodingEntityJsonMapper;
import net.avantica.xinef.dapp.data.exception.NetworkConnectionException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import rx.observers.TestSubscriber;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class GeolocationApiImplTest {

    private GeolocationApiImpl geolocationApi;

    @Mock
    private ReverseGeocodingEntityJsonMapper jsonMapper;

    @Before
    public void setUp() throws Exception {
        geolocationApi = spy(new GeolocationApiImpl(null, jsonMapper));
    }

    @Test
    public void shouldNotifyAnErrorWhenThereIsNoInternetConnection() {
        doReturn(false).when(geolocationApi).isThereInternetConnection();
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        geolocationApi.getDepartmentFromlatitudeLongitude(14, 15)
                .subscribe(testSubscriber);

        testSubscriber.assertError(NetworkConnectionException.class);
    }

    @Test
    public void shouldReturnDepartmentWhenRetrievingSuccessfullyFromApi() throws MalformedURLException {
        doReturn(true).when(geolocationApi).isThereInternetConnection();

        ReverseGeocodingEntity entity = preparateReverseGeocodingEntity("Lambayeque", "Lambayeque", singletonList("administrative_area_level_1"));

        given(jsonMapper.transformReverseGeocodingEntity(anyString())).willReturn(entity);
        given(geolocationApi.getDepartmentFromLatitudeLongitudeFromApi(14, 15)).willReturn("{}");

        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        geolocationApi.getDepartmentFromlatitudeLongitude(14, 15).subscribe(testSubscriber);

        testSubscriber.assertReceivedOnNext(singletonList("LAMBAYEQUE"));
    }

    @NonNull
    private ReverseGeocodingEntity preparateReverseGeocodingEntity(String longName, String shortName, List<String> types) {
        AddressComponent addressComponent = new AddressComponent();
        addressComponent.setLongName(longName);
        addressComponent.setShortName(shortName);
        addressComponent.setTypes(types);

        ReverseGeocodingResult reverseGeocodingResult = new ReverseGeocodingResult();
        reverseGeocodingResult.setAddressComponents(singletonList(addressComponent));

        ReverseGeocodingEntity entity = new ReverseGeocodingEntity();
        entity.setResults(singletonList(reverseGeocodingResult));
        entity.setStatus("Ok");
        return entity;
    }

    @Test
    public void shouldNotifyAnErrorWhenListOfResultFromReverseGeocodingEntityIsEmpty() throws MalformedURLException {
        doReturn(true).when(geolocationApi).isThereInternetConnection();

        ReverseGeocodingEntity entity = new ReverseGeocodingEntity();
        List<ReverseGeocodingResult> results = new ArrayList<>();
        entity.setResults(results);

        given(jsonMapper.transformReverseGeocodingEntity(anyString())).willReturn(entity);
        given(geolocationApi.getDepartmentFromLatitudeLongitudeFromApi(14, 15)).willReturn("{}");

        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        geolocationApi.getDepartmentFromlatitudeLongitude(14, 15).subscribe(testSubscriber);

        testSubscriber.assertError(NetworkConnectionException.class);
    }

    @Test
    public void shouldReturnEmptyStringWhenThereIsNotAnAdministrativeAreaLevel1() throws MalformedURLException {
        doReturn(true).when(geolocationApi).isThereInternetConnection();

        ReverseGeocodingEntity entity = preparateReverseGeocodingEntity("Lambayeque", "Lambayeque", singletonList(""));

        given(jsonMapper.transformReverseGeocodingEntity(anyString())).willReturn(entity);
        given(geolocationApi.getDepartmentFromLatitudeLongitudeFromApi(14, 15)).willReturn("{}");

        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        geolocationApi.getDepartmentFromlatitudeLongitude(14, 15).subscribe(testSubscriber);

        testSubscriber.assertReceivedOnNext(singletonList(""));
    }
}