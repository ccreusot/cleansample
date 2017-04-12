package ccr.cleanarchisample.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ccr.cleanarchisample.list.viewmodel.GitHubForkItemViewModel;

public class GitHubForkListAdapter extends RecyclerView.Adapter {

    private final List<GitHubForkItemViewModel> items;

    public GitHubForkListAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<GitHubForkItemViewModel> datas) {
        items.clear();
        items.addAll(datas);
        notifyDataSetChanged();
    }
}
