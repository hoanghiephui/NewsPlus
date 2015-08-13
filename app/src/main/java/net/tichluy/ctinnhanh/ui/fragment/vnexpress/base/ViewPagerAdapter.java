package net.tichluy.ctinnhanh.ui.fragment.vnexpress.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.tichluy.ctinnhanh.ui.fragment.vnexpress.DoiSong;
import net.tichluy.ctinnhanh.ui.fragment.vnexpress.GiaiTri;
import net.tichluy.ctinnhanh.ui.fragment.vnexpress.KhoaHoc;
import net.tichluy.ctinnhanh.ui.fragment.vnexpress.KinhDoanh;
import net.tichluy.ctinnhanh.ui.fragment.vnexpress.PhapLuat;
import net.tichluy.ctinnhanh.ui.fragment.vnexpress.TamSu;
import net.tichluy.ctinnhanh.ui.fragment.vnexpress.TheGioi;
import net.tichluy.ctinnhanh.ui.fragment.vnexpress.ThoiSu;
import net.tichluy.ctinnhanh.ui.fragment.vnexpress.TrangChu;
import net.tichluy.ctinnhanh.ui.fragment.vnexpress.Xe;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{

    int mNumOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) throws NullPointerException{
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) throws NullPointerException{

        switch (position) {
            case 0:
                return new TrangChu();
            case 1:
                return new ThoiSu();
            case 2:
                return new DoiSong();
            case 3:
                return new TheGioi();
            case 4:
                return new KinhDoanh();
            case 5:
                return new GiaiTri();
            case 6:
                return new PhapLuat();
            case 7:
                return new KhoaHoc();
            case 8:
                return new Xe();
            case 9:
                return new TamSu();
            default:
                return new TrangChu();
        }
    }

    @Override
    public int getCount() throws NullPointerException{
        return mNumOfTabs;
    }

}