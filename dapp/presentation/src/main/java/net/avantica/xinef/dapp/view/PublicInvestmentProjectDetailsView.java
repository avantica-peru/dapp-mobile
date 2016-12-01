package net.avantica.xinef.dapp.view;


import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user profile.
 */
public interface PublicInvestmentProjectDetailsView extends LoadDataView {
    /**
     * Render a user in the UI.
     *
     * @param user The {@link PublicInvestmentProjectModel} that will be shown.
     */
    void renderUser(PublicInvestmentProjectModel user);
}
