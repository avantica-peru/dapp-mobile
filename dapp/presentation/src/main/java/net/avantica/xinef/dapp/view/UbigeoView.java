package net.avantica.xinef.dapp.view;

import net.avantica.xinef.dapp.model.UbigeoModel;

import java.util.Collection;

public interface UbigeoView extends LoadDataView {

    void renderDepartments(Collection<UbigeoModel> departments);

    void renderProvinces(Collection<UbigeoModel> departments);

    void renderDistricts(Collection<UbigeoModel> departments);
}
