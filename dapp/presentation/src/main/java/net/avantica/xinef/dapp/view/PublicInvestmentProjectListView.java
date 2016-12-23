package net.avantica.xinef.dapp.view;

import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link PublicInvestmentProjectModel}.
 */
public interface PublicInvestmentProjectListView extends LoadDataView {
    /**
     * Render a public investment project list in the UI.
     *
     * @param publicInvestmentProjectModelCollection The collection of {@link PublicInvestmentProjectModel} that will be shown.
     */
    void renderPublicInvestmentProjectList(Collection<PublicInvestmentProjectModel> publicInvestmentProjectModelCollection);

    /**
     * View a {@link PublicInvestmentProjectModel} profile/details.
     *
     * @param snipCode The public investment project that will be shown.
     */
    void viewPublicInvestmentProject(String snipCode);
}
