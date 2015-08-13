/*
 * Copyright 2015 Rudson Lima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.tichluy.ctinnhanh.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.getbase.floatingactionbutton.FloatingActionButton;
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

public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private TextView txtErr;
    private Context contexts;
    private ProgressWheel progressWheel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ArrayList<ItemsNews> arrayList = new ArrayList<>();
    private String mUrl;
    AdView mAdView;
    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";

    public static MainFragment newInstance(String text) {
        MainFragment mFragment = new MainFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);
        return mFragment;
    }

    public String checkSite(String url) throws NullPointerException{
        if (getString(R.string.tinhte).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/tinhte";
        } else if (getString(R.string.sohoa).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/vnexpress/sohoa_";
        }
        //vnreview
        else if (getString(R.string.vnreview).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/vnreview/home";
        }
        else if (getString(R.string.danhgia).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://vnreview.vn/feed/-/rss/13612?format=xml";
        }
        else if (getString(R.string.tintuc).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://vnreview.vn/feed/-/rss/13414?format=xml";
        }
        else if (getString(R.string.tuvan).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://vnreview.vn/feed/-/rss/13626?format=xml";
        }
        else if (getString(R.string.gocnhin).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://vnreview.vn/feed/-/rss/487801?format=xml";
        }
        else if (getString(R.string.hoidap).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://vnreview.vn/feed/-/rss/186935?format=xml";
        }
        else if (getString(R.string.hdViet).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/hdviet";
        } else if (getString(R.string.thethao247).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/thethao247";
        } else if (getString(R.string.droiViet).equals(getArguments().getString(TEXT_FRAGMENT))){
            url = "http://feeds.feedburner.com/rssdroidviet";
        } else if (getString(R.string.afamily).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/afamily/home_";
        }
        //Tiin
        else if (getString(R.string.tiinhome).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/tiinvn/home";
        }
        else if (getString(R.string.tinsao).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/tiin/sao";
        }
        else if (getString(R.string.tiindep).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/tiin/dep";
        }
        else if (getString(R.string.tiinyeu).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/tiin/yeu";
        }
        //Kênh14
        else if (getString(R.string.khome).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/kenh14/home";
        }
        else if (getString(R.string.star).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/kenh14/star";
        }
        else if (getString(R.string.fashion).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/kenh14/fashion";
        }
        else if (getString(R.string.cine).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/kenh14/cafe";
        }
        else if (getString(R.string.tvshow).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/kenh14/tvshow";
        }
        else if (getString(R.string.kthegioi).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/Kenh14vn-ThGii-RssFeed";
        }
        else if (getString(R.string.skgt).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/kenh14/skgt";
        }
        else if (getString(R.string.made).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/kenh14/madeby";
        }
        else if (getString(R.string.tek).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/kenh14/2tek";
        }
        //người đưa tin
        else if (getString(R.string.tinnhat).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/nguoiduatin/home";
        }
        else if (getString(R.string.nthoisu).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/nguoiduatin/thoisu";
        }
        else if (getString(R.string.nthegioi).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/nguoiduatin/thegioi";
        }
        else if (getString(R.string.ndoisong).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/nguoiduatin/doisong";
        }
        else if (getString(R.string.nphapluat).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/nguoiduatin/phapluat";
        }
        //pháp luật đời sống
        else if (getString(R.string.plhome).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/doisongphapluat/home";
        }
        else if (getString(R.string.plpl).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/doisongphapluat/phapluat";
        }
        else if (getString(R.string.plthegioi).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/doisongphapluat/thegioi";
        }
        else if (getString(R.string.plthethao).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/doisongphapluat/thethao";
        }
        else if (getString(R.string.pldiaphuong).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/doisongphapluat/diaphuong";
        }
        else if (getString(R.string.antvs).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/doisongphapluat/antv";
        }
        else if (getString(R.string.nguyentandung).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/nguyentandung?format=xml";
        }
        //thanh niên
        else if (getString(R.string.tinnhatTN).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://www.thanhnien.com.vn/rss/home.rss?format=xml";
        }
        else if (getString(R.string.chinhtriTN).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://www.thanhnien.com.vn/rss/chinh-tri-xa-hoi.rss?format=xml";
        }
        else if (getString(R.string.quocphong).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://www.thanhnien.com.vn/rss/quoc-phong.rss?format=xml";
        }
        else if (getString(R.string.kinhteTN).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://www.thanhnien.com.vn/rss/kinh-te.rss?format=xml";
        }
        else if (getString(R.string.thegioiTN).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://www.thanhnien.com.vn/rss/the-gioi.rss?format=xml";
        }
        else if (getString(R.string.game).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://www.thanhnien.com.vn/rss/game.rss?format=xml";
        }
        else if (getString(R.string.suckhoeTN).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://www.thanhnien.com.vn/rss/suc-khoe.rss?format=xml";
        }
        //tiền phong
        else if (getString(R.string.xahoiTP).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://www.tienphong.vn/rss/xa-hoi-2.rss?format=xml";
        }
        else if (getString(R.string.kinhteTP).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://www.tienphong.vn/rss/kinh-te-3.rss?format=xml";
        }
        else if (getString(R.string.thegioiTP).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://www.tienphong.vn/rss/the-gioi-5.rss?format=xml";
        }
        else if (getString(R.string.gioitre).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://www.tienphong.vn/rss/gioi-tre-4.rss?format=xml";
        }
        else if (getString(R.string.phapluatTP).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://www.tienphong.vn/rss/phap-luat-12.rss?format=xml";
        }
        else if (getString(R.string.giaitriTP).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://www.tienphong.vn/rss/giai-tri-36.rss?format=xml";
        }
        else if (getString(R.string.khoahocTp).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://www.tienphong.vn/rss/cong-nghe-45.rss?format=xml";
        }
        else if (getString(R.string.nguoilinh).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://www.tienphong.vn/rss/hanh-trang-nguoi-linh-182.rss?format=xml";
        }//petro news
        else if (getString(R.string.petro).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://feeds.feedburner.com/petro/home?format=xml";
        }
        //ICT news
        else if (getString(R.string.vienthong).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://ictnews.vn/rss/vien-thong?format=xml";
        }
        else if (getString(R.string.internet).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://ictnews.vn/rss/internet?format=xml";
        }
        else if (getString(R.string.cntt).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://ictnews.vn/rss/cntt?format=xml";
        }
        else if (getString(R.string.ictKinhDoanh).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://ictnews.vn/rss/kinh-doanh?format=xml";
        }
        else if (getString(R.string.thegioiso).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://ictnews.vn/rss/the-gioi-so?format=xml";
        }
        else if (getString(R.string.gameICT).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://ictnews.vn/rss/game?format=xml";
        }
        else if (getString(R.string.khoinghiep).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://ictnews.vn/rss/khoi-nghiep?format=xml";
        }
        else if (getString(R.string.congnghe360).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://ictnews.vn/rss/cong-nghe-360?format=xml";
        }
        //dân trí
        else if (getString(R.string.home_vn).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://dantri.com.vn/trangchu.rss?format=xml";
        }
        else if (getString(R.string.dxahoi).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://dantri.com.vn/xa-hoi.rss?format=xml";
        }
        else if (getString(R.string.thegioi).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://dantri.com.vn/the-gioi.rss?format=xml";
        }
        else if (getString(R.string.dgiaoduc).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://dantri.com.vn/giao-duc-khuyen-hoc.rss?format=xml";
        }
        else if (getString(R.string.kinhdoanh).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://dantri.com.vn/kinh-doanh.rss?format=xml";
        }
        else if (getString(R.string.giaitri).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://dantri.com.vn/giai-tri.rss?format=xml";
        }
        else if (getString(R.string.phapluat).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://dantri.com.vn/phap-luat.rss?format=xml";
        }
        else if (getString(R.string.dsuckhoe).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://dantri.com.vn/suc-khoe.rss?format=xml";
        }
        else if (getString(R.string.xe).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://dantri.com.vn/o-to-xe-may.rss?format=xml";
        }
        else if (getString(R.string.dsucmanhso).equals(getArguments().getString(TEXT_FRAGMENT))) {
            url = "http://dantri.com.vn/suc-manh-so.rss?format=xml";
        }
        return url;

    }

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

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeNews);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);
        swipeRefreshLayout.setOnRefreshListener(MainFragment.this);

        FloatingActionButton fab_update = (FloatingActionButton)rootView.findViewById(R.id.fab_a);
        FloatingActionButton fab_share = (FloatingActionButton)rootView.findViewById(R.id.fab_share);
        fab_share.setColorPressed(ItemsNews.darker(R.color.refresh_progress_2, 0.8));
        fab_update.setColorPressed(ItemsNews.darker(R.color.refresh_progress_2, 0.8));
        fab_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkConnect(checkSite(mUrl));
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setNavigationBarColor(getResources().getColor(R.color.md_indigo_500));
        }
        progressWheel.setVisibility(View.VISIBLE);
        checkConnect(checkSite(mUrl));
        return rootView;
    }
    @Override
    public void onRefresh() {
        checkConnect(checkSite(mUrl));
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
        checkConnect(checkSite(mUrl));
    }

    @Override
    public void onDestroy() {
        if (mAdView !=null){
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
