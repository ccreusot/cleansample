package ccr.cleanarchisample.list.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.List;

import ccr.cleanarchisample.R;
import ccr.cleanarchisample.core.GitHubRepositoryImpl;
import ccr.cleanarchisample.list.GitHubForkListModule;
import ccr.cleanarchisample.list.adapter.GitHubForkListAdapter;
import ccr.cleanarchisample.list.presentation.GitHubForkListView;
import ccr.cleanarchisample.list.viewmodel.GitHubForkItemViewModel;

public class GitHubForkListActivity extends AppCompatActivity implements GitHubForkListView {

    private RecyclerView gitHubForkRecyclerView;
    private TextView infosTextView;
    private ViewFlipper viewFlipper;
    private GitHubForkListAdapter gitHubForkListAdapter;
    private GitHubForkListModule module;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub_fork_list);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        infosTextView = (TextView) findViewById(R.id.infosTextView);
        gitHubForkRecyclerView = (RecyclerView) findViewById(R.id.gitHubForkListRecyclerView);
        gitHubForkRecyclerView.setHasFixedSize(true);
        gitHubForkRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        gitHubForkListAdapter = new GitHubForkListAdapter();
        gitHubForkRecyclerView.setAdapter(gitHubForkListAdapter);

        module = new GitHubForkListModule(new GitHubRepositoryImpl());
        module.getViewDecorator().mutate(this);
        module.getInteractor().fetchForkList();
    }

    @Override
    protected void onDestroy() {
        module.getViewDecorator().mutate(null);
        super.onDestroy();
    }

    @Override
    public void displayNothing() {
        viewFlipper.setDisplayedChild(1);
        infosTextView.setText(R.string.git_hub_fork_list_nothing_to_show);
    }

    @Override
    public void displayError() {
        viewFlipper.setDisplayedChild(1);
        infosTextView.setText(R.string.git_hub_fork_list_load_error);
    }

    @Override
    public void displayList(List<GitHubForkItemViewModel> viewModelList) {
        viewFlipper.setDisplayedChild(2);
        gitHubForkListAdapter.setData(viewModelList);
    }
}
