package net.avantica.xinef.dapp.data.entity.mapper;

import net.avantica.xinef.dapp.data.entity.UbigeoEntity;
import net.avantica.xinef.dapp.domain.entity.Ubigeo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UbigeoEntityDataMapper {

    @Inject
    public UbigeoEntityDataMapper() {

    }

    public Ubigeo transform(UbigeoEntity ubigeoEntity) {
        Ubigeo ubigeo = null;

        if (ubigeoEntity != null) {
            ubigeo = new Ubigeo();
            ubigeo.setCodDpto(ubigeoEntity.getCodDpto());
            ubigeo.setCodProv(ubigeoEntity.getCodProv());
            ubigeo.setCodDist(ubigeoEntity.getCodDist());
            ubigeo.setDescripcion(ubigeoEntity.getDescripcion());
        }

        return ubigeo;
    }

    public List<Ubigeo> transform(Collection<UbigeoEntity> ubigeoEntityCollection) {
        List<Ubigeo> ubigeoList = new ArrayList<>();
        Ubigeo publicInvestmentProject;
        for (UbigeoEntity ubigeoEntity : ubigeoEntityCollection) {
            publicInvestmentProject = transform(ubigeoEntity);
            if (publicInvestmentProject != null) {
                ubigeoList.add(publicInvestmentProject);
            }
        }

        return ubigeoList;
    }
}
