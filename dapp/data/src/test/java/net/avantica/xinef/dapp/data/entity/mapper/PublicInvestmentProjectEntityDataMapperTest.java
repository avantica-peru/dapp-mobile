package net.avantica.xinef.dapp.data.entity.mapper;

import android.app.Application;

import net.avantica.xinef.dapp.data.entity.PublicInvestmentProjectEntity;
import net.avantica.xinef.dapp.domain.entity.PublicInvestmentProject;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class PublicInvestmentProjectEntityDataMapperTest extends Application {
    private static final String FAKE_PROJECT_SNIP_CODE = "211161";
    private static final String FAKE_PROJECT_NAME = "CREACION DE LOSA DEPORTIVA MULTIUSO EN EL ANEXO DE CHONIA , DISTRITO DE SAN JERONIMO - LUYA - AMAZONAS";

    private PublicInvestmentProjectEntityDataMapper publicInvestmentProjectEntityDataMapper;

    @Before
    public void setUp() {
        publicInvestmentProjectEntityDataMapper = new PublicInvestmentProjectEntityDataMapper();
    }

    @Test
    public void shouldTransformFromPublicInvestmentProjectEntity() {
        PublicInvestmentProjectEntity publicInvestmentProjectEntity = createFakePublicInvestmentProjectEntity();
        PublicInvestmentProject publicInvestmentProject = publicInvestmentProjectEntityDataMapper.transform(publicInvestmentProjectEntity);

        assertThat(publicInvestmentProject, isA(PublicInvestmentProject.class));
        assertThat(publicInvestmentProject.getSnipCode(), is(FAKE_PROJECT_SNIP_CODE));
        assertThat(publicInvestmentProject.getName(), is(FAKE_PROJECT_NAME));
    }

    @Test
    public void shouldTransformProjectEntityCollection() {
        PublicInvestmentProjectEntity mockPublicInvestmentProjectEntityOne = mock(PublicInvestmentProjectEntity.class);
        PublicInvestmentProjectEntity mockPublicInvestmentProjectEntityTwo = mock(PublicInvestmentProjectEntity.class);
        PublicInvestmentProjectEntity mockPublicInvestmentProjectEntityThree = mock(PublicInvestmentProjectEntity.class);

        List<PublicInvestmentProjectEntity> publicInvestmentProjectEntityList = new ArrayList<>();
        publicInvestmentProjectEntityList.add(mockPublicInvestmentProjectEntityOne);
        publicInvestmentProjectEntityList.add(mockPublicInvestmentProjectEntityTwo);
        publicInvestmentProjectEntityList.add(mockPublicInvestmentProjectEntityThree);

        Collection<PublicInvestmentProject> publicInvestmentProjectCollection = publicInvestmentProjectEntityDataMapper.transform(publicInvestmentProjectEntityList);

        assertThat(publicInvestmentProjectCollection.toArray()[0], is(instanceOf(PublicInvestmentProject.class)));
        assertThat(publicInvestmentProjectCollection.toArray()[1], is(instanceOf(PublicInvestmentProject.class)));
        assertThat(publicInvestmentProjectCollection.size(), is(3));
    }

    private PublicInvestmentProjectEntity createFakePublicInvestmentProjectEntity() {
        PublicInvestmentProjectEntity publicInvestmentProjectEntity = new PublicInvestmentProjectEntity();
        publicInvestmentProjectEntity.setSnipCode(FAKE_PROJECT_SNIP_CODE);
        publicInvestmentProjectEntity.setName(FAKE_PROJECT_NAME);

        return publicInvestmentProjectEntity;
    }
}