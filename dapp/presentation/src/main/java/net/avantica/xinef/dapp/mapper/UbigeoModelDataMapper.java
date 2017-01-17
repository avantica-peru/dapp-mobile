package net.avantica.xinef.dapp.mapper;

import net.avantica.xinef.dapp.di.PerActivity;
import net.avantica.xinef.dapp.domain.entity.Ubigeo;
import net.avantica.xinef.dapp.model.UbigeoModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

@PerActivity
public class UbigeoModelDataMapper {

    @Inject
    public UbigeoModelDataMapper() {
    }

    public UbigeoModel transform(Ubigeo ubigeo) {
        if (ubigeo == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        UbigeoModel ubigeoModel = new UbigeoModel();
        ubigeoModel.setCodDpto(ubigeo.getCodDpto());
        ubigeoModel.setCodProv(ubigeo.getCodProv());
        ubigeoModel.setCodDist(ubigeo.getCodDist());
        ubigeoModel.setDescripcion(ubigeo.getDescripcion());

        return ubigeoModel;
    }

    public Collection<UbigeoModel> transform(Collection<Ubigeo> ubigeoCollection) {
        Collection<UbigeoModel> ubigeoModelCollection;

        if (ubigeoCollection != null && !ubigeoCollection.isEmpty()) {
            ubigeoModelCollection = new ArrayList<>();
            for (Ubigeo ubigeo : ubigeoCollection) {
                ubigeoModelCollection.add(transform(ubigeo));
            }
        } else {
            ubigeoModelCollection = Collections.emptyList();
        }

        return ubigeoModelCollection;
    }
}
