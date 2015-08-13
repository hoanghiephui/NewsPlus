package net.tichluy.ctinnhanh.utils;

import android.content.Context;
import android.preference.PreferenceCategory;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.ThemeSingleton;

import net.tichluy.ctinnhanh.R;

/**
 * Created by Hoang Hiep on 28/07/2015.
 */
public class PreferenceCategorySetting extends PreferenceCategory {

    public PreferenceCategorySetting(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PreferenceCategorySetting(Context context) {
        this(context, null, 0);
    }

    public PreferenceCategorySetting(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutResource(R.layout.preference_category_custom);
        setSelectable(false);
    }

    @Override
    protected void onBindView(@NonNull View view) {
        super.onBindView(view);
        ((TextView) view).setTextColor(ThemeSingleton.get().positiveColor);
    }
}
