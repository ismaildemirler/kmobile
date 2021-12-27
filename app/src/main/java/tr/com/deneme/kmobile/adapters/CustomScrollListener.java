package tr.com.deneme.kmobile.adapters;

import androidx.recyclerview.widget.RecyclerView;

public abstract class CustomScrollListener extends RecyclerView.OnScrollListener {

    public abstract void onScrollStateChanged(RecyclerView recyclerView, int newState);

    public abstract void onScrolled(RecyclerView recyclerView, int dx, int dy);
}