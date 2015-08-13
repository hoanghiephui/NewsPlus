package net.tichluy.ctinnhanh.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import net.tichluy.ctinnhanh.R;
import net.tichluy.ctinnhanh.model.ItemsNews;

/**
 * Created by Hoang Hiep on 8/1/2015.
 */
public class ViewsNews extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private ShareActionProvider shareActionProvider;
    private WebView webView;
    private Context context;
    private ItemsNews items;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AdView mAdView;
    CoordinatorLayout mCoordinatorLayout;

    private void getInitialConfiguration() {
        String title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");
        String des = getIntent().getStringExtra("des");
        items = new ItemsNews(title, url, des);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        setContentView(R.layout.activity_views);
        webView = (WebView)findViewById(R.id.webView);

        getInitialConfiguration();
        final Toolbar toolbar = (Toolbar) findViewById(R.id.quickreturn_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(items.getTitle());
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.quickreturn_coordinator);



        //F5
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeNewsViews);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);
        swipeRefreshLayout.setOnRefreshListener(ViewsNews.this);
        displayAdView();
        loadUrl();
    }



    public void displayAdView(){
        mAdView = (AdView)findViewById(R.id.adViews);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void loadUrl(){
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#E91E63"), PorterDuff.Mode.SRC_IN);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient());
        try {
            webView.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
                    if (progress < 100 && progressBar.getVisibility() == ProgressBar.GONE) {
                        progressBar.setVisibility(ProgressBar.VISIBLE);
                    }
                    progressBar.setProgress(progress);
                    if (progress == 100) {
                        progressBar.setVisibility(ProgressBar.GONE);
                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                            displayAdView();
                        }
                    }
                }
            });
        }catch (NullPointerException e){
            e.printStackTrace ();
        }
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(items.getLink());
            }
        });
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }//end

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_forward, R.anim.slide_out_right);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_news, menu);
        MenuItem shareItem = menu.findItem(R.id.shareView);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Tin Nhanh by Hoàng Hiệp");
        String body = items.getTitle().toString() + "\n" + items.getLink().toString() + "\n" + "send to Đọc Tin Nhanh by Hoang Hiep";
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (shareActionProvider != null){
            shareActionProvider.setShareIntent(intent);
            shareActionProvider.refreshVisibility();
        }else {
            Toast.makeText(getApplicationContext()," null", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.f5:
                refreshList(item);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        loadUrl();
    }

    public void refreshList(final MenuItem menuItem){
        LayoutInflater inflater = (LayoutInflater)getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ImageView imgRefresh = (ImageView)inflater.inflate(R.layout.action_refresh, null);
        Animation rotation = AnimationUtils.loadAnimation(getApplication(),
                R.anim.refresh_rotate);
        rotation.setRepeatCount(Animation.INFINITE);
        imgRefresh.startAnimation(rotation);

        menuItem.setActionView(imgRefresh);

        try {
            webView.setWebChromeClient(new WebChromeClient(){
                public void onProgressChanged(WebView view, int progress)
                {
                    if(progress < 100 && progressBar.getVisibility() == ProgressBar.GONE){
                        progressBar.setVisibility(ProgressBar.VISIBLE);

                    }
                    progressBar.setProgress(progress);
                    try {
                        if(progress == 100) {
                            progressBar.setVisibility(ProgressBar.GONE);
                            menuItem.getActionView().clearAnimation();
                            menuItem.setActionView(null);
                            Toast.makeText(getApplicationContext(),"Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }

                }
            });
            webView.loadUrl(items.getLink());

        }catch (NullPointerException e){
            e.printStackTrace();
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
    }

    @Override
    public void onDestroy() {
        if (mAdView !=null){
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
