package net.avantica.xinef.dapp.view;

import java.util.Collection;

public interface FilterProjectView extends LoadDataView {

    void renderDepartments(Collection<String> departments);

    void renderProvinces(Collection<String> departments);

    void renderDistricts(Collection<String> departments);
}
