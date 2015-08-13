package net.tichluy.ctinnhanh.utils;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;

import net.tichluy.ctinnhanh.R;

/**
 * Created by Hoang Hiep on 28/07/2015.
 */
public class PreferenceSetting extends Preference {

    private View mView;
    private int color;
    private int border;

    public PreferenceSetting(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PreferenceSetting(Context context) {
        this(context, null, 0);
    }

    public PreferenceSetting(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutResource(R.layout.preference_custom);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        mView = view;
        invalidateColor();
    }

    public void setColor(int color, int border) {
        this.color = color;
        this.border = border;
        invalidateColor();
    }

    private void invalidateColor() {
        if (mView != null) {
            CircleView circle = (CircleView) mView.findViewById(R.id.circle);
            if (this.color != 0) {
                circle.setVisibility(View.VISIBLE);
                circle.setBackgroundColor(color);
                circle.setBorderColor(border);
            } else {
                circle.setVisibility(View.GONE);
            }
        }
    }
}
