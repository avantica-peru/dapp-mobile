package net.avantica.xinef.dapp.view;


import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a public investment project.
 */
public interface PublicInvestmentProjectDetailsView extends LoadDataView {
    /**
     * Render a public investment project in the UI.
     *
     * @param publicInvestmentProject The {@link PublicInvestmentProjectModel} that will be shown.
     */
    void renderPublicInvestmentProject(PublicInvestmentProjectModel publicInvestmentProject);
}
