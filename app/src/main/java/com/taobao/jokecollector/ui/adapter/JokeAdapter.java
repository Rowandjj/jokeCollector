package com.taobao.jokecollector.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.taobao.jokecollector.R;
import com.taobao.jokecollector.model.Joke;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Rowandjj on 2015/5/7.
 */
public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder> //BounceAnimationAdaptor<JokeAdapter.ViewHolder>
{
    private List<Joke> mJokes;

    public JokeAdapter()
    {
        this.mJokes = new ArrayList<>();
    }


    public void addJokes(List<Joke> jokes)
    {
        this.mJokes.addAll(jokes);
        this.notifyItemRangeInserted(mJokes.size(),jokes.size());
    }

    public void removeAll()
    {
        this.mJokes.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_joke,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
//        super.onBindViewHolder(holder,position);
        Joke joke = mJokes.get(position);
        holder.bindData(joke);
    }

    @Override
    public int getItemCount()
    {
        return mJokes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        @InjectView(R.id.tv_joke_author)
        TextView jokeAuthor;

        @InjectView(R.id.tv_joke_time)
        TextView jokeTime;

        @InjectView(R.id.tv_joke_content)
        TextView jokeContent;

        @InjectView(R.id.tv_joke_like)
        TextView likeNum;

        @InjectView(R.id.tv_joke_unlike)
        TextView unlikeNum;

        @InjectView(R.id.tv_tucao_num)
        TextView commentNum;

        @InjectView(R.id.iv_joke_menu)
        ImageView menu;


        public ViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }

        public void bindData(Joke joke)
        {
            if(joke == null)
                return;
            jokeAuthor.setText(joke.getComment_author());
            jokeTime.setText(joke.getComment_date());
            jokeContent.setText(joke.getComment_content());
            likeNum.setText("XX "+joke.getVote_positive());
            unlikeNum.setText("OO "+joke.getVote_negative());

            //TODO callback
            commentNum.setText("10");
        }
    }
}















