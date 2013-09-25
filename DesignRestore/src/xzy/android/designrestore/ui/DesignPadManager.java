package xzy.android.designrestore.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;


/**
 * (C) 2013 zhengyang.xu
 *
 * @author zhengyang.xu
 * @version 0.1
 * @since Sep 19, 2013 11:08:33 PM
 */
public class DesignPadManager {
    
    private static DesignPadManager mInstance;

    private Context mContext;

    private DesignPadView mDesignPadView;

    private SharedPreferences mSharedPreference;

    private boolean isNeedVibrator;

    private int mScreenWidth;
    
    private int mScreenHeight;
    
    public static enum PAD_STATE {NORMAL, SMALL};
    
    private PAD_STATE mPadState;

    public DesignPadManager(Context context) {
        initialization(context);
    }

    public static synchronized DesignPadManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DesignPadManager(context);
        }
        return mInstance;
    }

    public DesignPadView getDesignPadView() {
        return mDesignPadView;
    }
    
    private void initialization(Context context) {
        mContext = context;
        mPadState = PAD_STATE.SMALL;

        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) mContext.getSystemService("window")).getDefaultDisplay().getMetrics(
                metrics);

        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;

        Resources resource = context.getResources();
        int designPadViewHeight = mScreenHeight;
        int designPadViewWidth = mScreenWidth;
        int designPadViewPaddingTop = 0;

        mSharedPreference = PreferenceManager.getDefaultSharedPreferences(mContext);

        int paddingLeft = 0;
        int paddingTop = 0;

        mDesignPadView = createPadView(designPadViewWidth, designPadViewHeight, paddingLeft,
                paddingTop, Gravity.LEFT | Gravity.TOP);

        isNeedVibrator = mSharedPreference.getBoolean(
                mContext.getResources().getString(R.string.pref_key_vibrate), true);
    }
    
    private DesignPadView createPadView(int width, int height, int x, int y, int gravity) {
        DesignPadView stockPadView = new DesignPadView(mContext, this);

        WindowManager.LayoutParams params = stockPadView.getWMLayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.format = PixelFormat.TRANSLUCENT;
        // params.alpha = 0;

        params.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        params.gravity = gravity;
        params.x = x;
        params.y = y;

        params.width = width;
        params.height = height;

        return stockPadView;
    }
    
    public boolean getNeedVibrator() {
        isNeedVibrator = mSharedPreference.getBoolean(
                mContext.getResources().getString(R.string.pref_key_vibrate), true);
        return isNeedVibrator;
    }
    
    public PAD_STATE getPadState() {
    	return mPadState;
    }
}
