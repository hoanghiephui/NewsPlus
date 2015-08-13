package net.tichluy.ctinnhanh.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import net.tichluy.ctinnhanh.R;
import net.tichluy.ctinnhanh.ui.fragment.dantri.MainDanTri;
import net.tichluy.ctinnhanh.ui.fragment.doisongpl.MainDoiSongPL;
import net.tichluy.ctinnhanh.ui.fragment.ict.MainICT;
import net.tichluy.ctinnhanh.ui.fragment.kenh14.MainKenh14;
import net.tichluy.ctinnhanh.ui.fragment.nguoiduatin.MainNguoiDuaTin;
import net.tichluy.ctinnhanh.ui.fragment.thanhnien.MainThanhNien;
import net.tichluy.ctinnhanh.ui.fragment.tiin.MainTiin;
import net.tichluy.ctinnhanh.ui.fragment.tinphong.MainTienPhong;
import net.tichluy.ctinnhanh.ui.fragment.vnexpress.MainFragment;
import net.tichluy.ctinnhanh.ui.fragment.vnreview.MainVnReview;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    Fragment mFragment = null;
    FragmentManager mFragmentManager = getSupportFragmentManager();
    String title = "Đọc Tin Nhanh";
    private Boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }
        mFragment = new MainFragment();
        mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
        getSupportActionBar().setTitle(title);
        //setupNavigationDrawerContent(navigationView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
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
            Toast.makeText(this, " null", Toast.LENGTH_LONG).show();
        }

        menu.findItem(R.id.menu_weather).setVisible(true);
        menu.findItem(R.id.about).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.about:
                Intent intent = new Intent(MainActivity.this, AboutsActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_weather:
                Intent intents = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intents);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.vnExpress:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = new MainFragment();
                                break;
                            case R.id.dantri:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = new MainDanTri();
                                break;
                            case R.id.kenh14:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = new MainKenh14();
                                break;
                            case R.id.tiin:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = new MainTiin();
                                break;
                            case R.id.thethao247:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = net.tichluy.ctinnhanh.ui.fragment.MainFragment.newInstance("Thể thao 247");
                                break;
                            case R.id.afamily:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = net.tichluy.ctinnhanh.ui.fragment.MainFragment.newInstance(getString(R.string.afamily));
                                break;
                            case R.id.nguoiduatin:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = new MainNguoiDuaTin();
                                break;
                            case R.id.doisong_phapluat:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = new MainDoiSongPL();
                                break;
                            case R.id.nguyentandung:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = net.tichluy.ctinnhanh.ui.fragment.MainFragment.newInstance(getString(R.string.nguyentandung));
                                break;
                            case R.id.thanhnien:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = new MainThanhNien();
                                break;
                            case R.id.tienphong:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = new MainTienPhong();
                                break;
                            case R.id.petro:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = net.tichluy.ctinnhanh.ui.fragment.MainFragment.newInstance(getString(R.string.petro));
                                break;
                            case R.id.tinhte:
                                menuItem.setCheckable(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = net.tichluy.ctinnhanh.ui.fragment.MainFragment.newInstance(getString(R.string.tinhte));
                                break;
                            case R.id.vnreview:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = new MainVnReview();
                                break;
                            case R.id.droiviet:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = net.tichluy.ctinnhanh.ui.fragment.MainFragment.newInstance(getString(R.string.droiViet));
                                break;
                            case R.id.sohoa:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = net.tichluy.ctinnhanh.ui.fragment.MainFragment.newInstance(getString(R.string.sohoa));
                                break;
                            case R.id.hdviet:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = net.tichluy.ctinnhanh.ui.fragment.MainFragment.newInstance(getString(R.string.hdViet));
                                break;
                            case R.id.ictnews:
                                menuItem.setChecked(true);
                                title = (String) menuItem.getTitle();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                mFragment = new MainICT();
                                break;
                            /*case R.id.settings:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                                startActivity(intent);
                                break;*/
                        }
                        if (mFragment != null) {
                            mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
                            getSupportActionBar().setTitle(title);
                        }
                        return true;
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        drawerLayout.closeDrawer(GravityCompat.START);
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        final Toast toast = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.toast_layout_root));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Nhấn thêm lần nữa để thoát!");
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(layout);
        toast.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }
}
