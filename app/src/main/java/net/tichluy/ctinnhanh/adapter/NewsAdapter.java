package net.tichluy.ctinnhanh.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import net.tichluy.ctinnhanh.R;
import net.tichluy.ctinnhanh.app.AppController;
import net.tichluy.ctinnhanh.model.ItemsNews;
import net.tichluy.ctinnhanh.ui.activity.ViewsNews;

import java.util.List;

/**
 * Created by Hoang Hiep on 23/07/2015.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.AppViewHolder> {
    private Context context;
    private List<ItemsNews> items;
    private int mPreviousPosition = 0;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader ();

    public NewsAdapter(Context context, List<ItemsNews> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AppViewHolder appViewHolder = null;
        try {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, null);
            appViewHolder = new AppViewHolder(view);
        } catch (InflateException e) {
            e.printStackTrace();
        }
        return appViewHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.AppViewHolder holder, int position) {
        final ItemsNews feedItem =  items.get(position);
        if (imageLoader == null) {
            imageLoader = AppController.getInstance ().getImageLoader ();
        }

        holder.txtTitle.setText(feedItem.getTitle());
        holder.txtContent.setText(Html.fromHtml(feedItem.getContent()) + "...".toString ());


        holder.txtTime.setText(feedItem.getTime());
        if (feedItem.getImage () != null) {
            holder.picNews.setImageUrl(feedItem.getImage(), imageLoader);
        } else if (feedItem.getImageD () != null){
            holder.picNews.setImageUrl(feedItem.getImageD(), imageLoader);
        }else {
            holder.picNews.setImageUrl(feedItem.getPic(), imageLoader);
        }

        CardView cardView = holder.vCard;
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Activity activity = (Activity) context;
                    Intent intent = new Intent(context, ViewsNews.class);
                    intent.putExtra("title", feedItem.getTitle());
                    intent.putExtra("url", feedItem.getLink());
                    intent.putExtra("des", feedItem.getContent());
                    if (feedItem.getImage () != null){
                        intent.putExtra("image", feedItem.getImage());
                    }else {
                        intent.putExtra("image", feedItem.getImageD());
                    }

                    context.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back);

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder{
        protected ImageView imageProfile, imageView;
        protected TextView txtTitle, txtContent, txtTime;
        protected NetworkImageView picNews;
        protected CardView vCard;
        public AppViewHolder(View itemView) {
            super(itemView);
            this.txtTitle = (TextView)itemView.findViewById(R.id.txt_title);
            this.txtContent = (TextView)itemView.findViewById(R.id.txt_content);
            this.txtTime = (TextView)itemView.findViewById(R.id.txt_time);
            this.picNews = (NetworkImageView)itemView.findViewById(R.id.img_new);
            this.vCard = (CardView)itemView.findViewById(R.id.app_cards);
        }
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }
}
