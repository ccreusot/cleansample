package ccr.cleanarchisample.detail.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ccr.cleanarchisample.R;
import ccr.cleanarchisample.core.GitHubRepositoryImpl;
import ccr.cleanarchisample.detail.GitHubForkDetailModule;
import ccr.cleanarchisample.detail.presentation.GitHubForkDetailView;
import ccr.cleanarchisample.detail.viewmodel.GitHubForkDetailViewModel;

public class DetailGitHubForkActivity extends AppCompatActivity implements GitHubForkDetailView {

    public static final String GITHUB_FORK_ID = "github_fork_id";
    private TextView titleTextView;
    private ImageView avatarImageView;
    private TextView descriptionTextView;
    private GitHubForkDetailModule module;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        avatarImageView = (ImageView) findViewById(R.id.avatarImageView);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);

        module = new GitHubForkDetailModule(GitHubRepositoryImpl.getInstance());
        module.getView().mutate(this);

        Intent intent = getIntent();
        intent.getStringExtra(GITHUB_FORK_ID);
        if (intent.hasExtra(GITHUB_FORK_ID)) {
            module.getInteractor().fetchGitHubForkDetail(intent.getStringExtra(GITHUB_FORK_ID));
        }
    }

    @Override
    protected void onDestroy() {
        module.getView().mutate(null);
        super.onDestroy();
    }

    @Override
    public void displayNothing() {

    }

    @Override
    public void displayError() {

    }

    @Override
    public void displayDetail(GitHubForkDetailViewModel viewModel) {
        Picasso.with(this).load(viewModel.getImageUrl()).into(avatarImageView);
        titleTextView.setText(viewModel.getTitle());
        descriptionTextView.setText(viewModel.getDescription());
    }
}
