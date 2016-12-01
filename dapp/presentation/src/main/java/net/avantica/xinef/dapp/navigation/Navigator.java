package net.avantica.xinef.dapp.navigation;

import android.content.Context;
import android.content.Intent;

import net.avantica.xinef.dapp.view.activity.ProjectDetailActivity;
import net.avantica.xinef.dapp.view.activity.ProjectListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
        //empty
    }

    /**
     * Goes to the user list screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToUserList(Context context) {
        if (context != null) {
            Intent intentToLaunch = ProjectListActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * Goes to the user details screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToUserDetails(Context context, String codeProject) {
        if (context != null) {
            Intent intentToLaunch = ProjectDetailActivity.getCallingIntent(context, codeProject);
            context.startActivity(intentToLaunch);
        }
    }
}
