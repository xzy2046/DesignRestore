
package xzy.android.designrestore.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

/**
 * (C) 2013 zhengyang.xu
 * 
 * @author zhengyang.xu
 * @version 0.1
 * @since Sep 20, 2013 12:03:29 AM
 */
public class DesignPadView extends ViewGroup {

    private Context mContext;

    private DesignPadManager mDesignPadManager;

    protected WindowManager mWinManager;

    protected WindowManager.LayoutParams mWinParams;
    
    private int mWidth;
    
    private int mHeight;
    
    private Button mUpLoadButton;
    
    public boolean isAttachedToWindow = false;

    public static enum PAD_MODE {
        SMALL, NORMAL
    };

    public DesignPadView(Context context, DesignPadManager manager) {
        super(context);
        mDesignPadManager = manager;
        mWinManager = (WindowManager) mContext.getSystemService("window");
        mWinParams = new WindowManager.LayoutParams();
        mContext = context;
        mUpLoadButton = new Button(mContext);
    }

    public DesignPadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public DesignPadView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public WindowManager.LayoutParams getWMLayoutParams() {
        return mWinParams;
    }

    @Override
    protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
        // TODO Auto-generated method stub
    	mUpLoadButton.layout(0, 0, 200, 200);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

	@Override
	protected void onAttachedToWindow() {
		isAttachedToWindow = true;
		super.onAttachedToWindow();
	}

	@Override
	protected void onDetachedFromWindow() {
		isAttachedToWindow = false;
		super.onDetachedFromWindow();
	}
    
    

}
