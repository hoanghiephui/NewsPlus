package net.tichluy.ctinnhanh.ui.fragment.vnexpress;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.pnikosis.materialishprogress.ProgressWheel;

import net.tichluy.ctinnhanh.R;
import net.tichluy.ctinnhanh.adapter.NewsAdapter;
import net.tichluy.ctinnhanh.app.AppController;
import net.tichluy.ctinnhanh.checking.CheckConnect;
import net.tichluy.ctinnhanh.model.ItemsNews;
import net.tichluy.ctinnhanh.model.XMLParser;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoang Hiep on 8/1/2015.
 */
public class TamSu extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private TextView txtErr;
    private Context contexts;
    private ProgressWheel progressWheel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<ItemsNews> arrayList = new ArrayList<>();
    private String mUrl = "http://feeds.feedburner.com/vnexpress/tamsu_";
    AdView mAdView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.contexts = this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        progressWheel = (ProgressWheel) rootView.findViewById(R.id.progress);
        txtErr = (TextView) rootView.findViewById(R.id.txterr);
        mAdView = (AdView) rootView.findViewById(R.id.adViews);
        FloatingActionsMenu fab = (FloatingActionsMenu) rootView.findViewById(R.id.fab);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeNews);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);
        swipeRefreshLayout.setOnRefreshListener(TamSu.this);

        FloatingActionButton fab_update = (FloatingActionButton)rootView.findViewById(R.id.fab_a);
        FloatingActionButton fab_share = (FloatingActionButton)rootView.findViewById(R.id.fab_share);
        fab_share.setColorPressed(ItemsNews.darker(R.color.refresh_progress_2, 0.8));
        fab_update.setColorPressed(ItemsNews.darker(R.color.refresh_progress_2, 0.8));
        fab_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkConnect(mUrl);
            }
        });
        fab_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Yoga");
                String share = "Yoga Everyone \n https://play.google.com/store/apps/details?id=net.tichluy.newsrss";
                intent.putExtra(Intent.EXTRA_TEXT, share);
                startActivity(intent);
            }
        });
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setNavigationBarColor(getResources().getColor(R.color.md_indigo_500));
        }
        progressWheel.setVisibility(View.VISIBLE);
        checkConnect(mUrl);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
        final MenuItem menuItem = menu.findItem(R.id.share);
        menuItem.setVisible(true);

        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Tin Nhanh by Hoàng Hiệp");
        String share = "Đọc Tin Nhanh - News+ \n https://play.google.com/store/apps/details?id=net.tichluy.newsrss";
        intent.putExtra(Intent.EXTRA_TEXT, share);
        if (shareActionProvider != null) {
            shareActionProvider.setShareIntent(intent);
        } else {
            Toast.makeText(getActivity(), " null", Toast.LENGTH_LONG).show();
        }

        menu.findItem(R.id.menu_weather).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        checkConnect(mUrl);
    }

    private class loadNews extends AsyncTask<String, String, Boolean> {
        private String feedUrl;
        private Context context;

        public loadNews(Context c, String url) {
            this.feedUrl = url;
            this.context = c;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                XMLParser parser = new XMLParser(feedUrl, context);
                arrayList = parser.parser();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            progressWheel.setProgress(Float.parseFloat(values[0]));
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                progressWheel.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                NewsAdapter adapter = new NewsAdapter(contexts, arrayList);
                mRecyclerView.setAdapter(adapter);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);
                if (mRecyclerView.getAdapter().getItemCount() <= 0) {
                    txtErr.setVisibility(View.VISIBLE);
                    txtErr.setText("Có lỗi xảy ra, bạn vui lòng quay lại sau!");
                } else {
                    txtErr.setVisibility(View.GONE);
                }
            } else {
                swipeRefreshLayout.setRefreshing(false);
                progressWheel.setVisibility(View.GONE);
            }
        }
    }

    //kiểm tra trạng thái kiết nối mạng
    private void checkConnect(final String url) {
        Boolean checkConnect;
        CheckConnect cd = new CheckConnect(contexts);
        checkConnect = cd.isConnectingToInternet();
        if (checkConnect) {
            JsonArrayRequest req = new JsonArrayRequest("http://www.sieuvietesd.vn/document.json", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    if (response.length() > 0) {
                        new loadNews(contexts, url).execute();
                        txtErr.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                        swipeRefreshLayout.setVisibility(View.VISIBLE);
                    }else {
                        mRecyclerView.setVisibility(View.GONE);
                        swipeRefreshLayout.setRefreshing(false);
                        txtErr.setVisibility(View.VISIBLE);
                        progressWheel.setVisibility(View.GONE);
                        txtErr.setText("Lỗi!");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error){
                    try {
                        mRecyclerView.setVisibility(View.GONE);
                        swipeRefreshLayout.setRefreshing(false);
                        txtErr.setVisibility(View.VISIBLE);
                        progressWheel.setVisibility(View.GONE);
                        txtErr.setText("Kết nối Internet của bạn bị giãn đoạn, vui lòng kiểm tra lại!");
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
            });
            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(req);

        } else {
            mRecyclerView.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
            txtErr.setVisibility(View.VISIBLE);
            progressWheel.setVisibility(View.GONE);
            txtErr.setText("Bạn vui lòng bật kết nối Internet, và mở lại ứng dụng!");
        }

    }

    @Override
    public void onPause() {
        if (mAdView != null){
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null){
            mAdView.resume();
        }
        checkConnect(mUrl);
    }

    @Override
    public void onDestroy() {
        if (mAdView !=null){
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
