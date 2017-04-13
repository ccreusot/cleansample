package ccr.cleanarchisample.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ccr.cleanarchisample.R;
import ccr.cleanarchisample.list.viewmodel.GitHubForkItemViewModel;

public class GitHubForkListAdapter extends RecyclerView.Adapter {

    private final List<GitHubForkItemViewModel> items;

    public GitHubForkListAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GitHubForkItemViewModel gitHubForkItemViewModel = items.get(position);
        ((ViewHolder)holder).setImage(gitHubForkItemViewModel.getUrlImage());
        ((ViewHolder)holder).setText(gitHubForkItemViewModel.getTitle());
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

    private static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }

        public void setImage(String url) {
            Picasso.with(imageView.getContext()).load(url).into(imageView);
        }

        public void setText(String text) {
            textView.setText(text);
        }
    }
}
