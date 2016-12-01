package net.avantica.xinef.dapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.view.fragment.ProjectDetailFragment;

public class ProjectDetailActivity extends AppCompatActivity {
    private static final String INTENT_EXTRA_PARAM_UNIQUE_CODE = "net.avantica.xinef.dapp.INTENT_PARAM_CODE_PROJECT";

    public static Intent getCallingIntent(Context context, String codeProject) {
        Intent callingIntent = new Intent(context, ProjectDetailActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_UNIQUE_CODE, codeProject);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        showProjectDetailView();
    }

    private void showProjectDetailView() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        ProjectDetailFragment newFragment = ProjectDetailFragment.newInstance("", "");
        Bundle bundle = new Bundle();
        newFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, newFragment, ProjectDetailFragment.class.getName());
        fragmentTransaction.commit();
    }
}
