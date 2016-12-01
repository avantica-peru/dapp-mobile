package net.avantica.xinef.dapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.view.fragment.ProjectListFragment;

public class ProjectListActivity extends AppCompatActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ProjectListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        showProjectListView();
    }

    private void showProjectListView() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        ProjectListFragment newFragment = ProjectListFragment.newInstance();
        Bundle bundle = new Bundle();
        newFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, newFragment, ProjectListFragment.class.getName());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
