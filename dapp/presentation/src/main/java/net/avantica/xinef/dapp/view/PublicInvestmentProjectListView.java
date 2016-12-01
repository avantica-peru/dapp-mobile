package net.avantica.xinef.dapp.view;

import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link PublicInvestmentProjectModel}.
 */
public interface PublicInvestmentProjectListView extends LoadDataView {
    /**
     * Render a user list in the UI.
     *
     * @param userModelCollection The collection of {@link PublicInvestmentProjectModel} that will be shown.
     */
    void renderUserList(Collection<PublicInvestmentProjectModel> userModelCollection);

    /**
     * View a {@link PublicInvestmentProjectModel} profile/details.
     *
     * @param userModel The user that will be shown.
     */
    void viewUser(PublicInvestmentProjectModel userModel);
}
