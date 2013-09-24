package xzy.android.designrestore.ui;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.WindowManager;

public class DesignService extends Service {

	private DesignPadManager mDesignPadManager;

	private WindowManager mWinManager;

	private final String TAG = "DesignService";

	private final boolean DEBUG = true;;

	@Override
	public IBinder onBind(Intent intent) {
		if (DEBUG)
			Log.i(TAG, "service onBind");
		return mBinder;
	}

	private IBinder mBinder = new IDesignService.Stub() {
        @Override
        public void addDesignPadView() throws RemoteException {
            if (mDesignPadManager == null) {
            	mDesignPadManager = DesignPadManager.getInstance(DesignService.this);
            }

            DesignPadView designPadView = mDesignPadManager.getDesignPadView();
            if (!designPadView.isAttachedToWindow) {
                try {
                    mWinManager.addView(designPadView, designPadView.getWMLayoutParams());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
	};

	@Override
	public void onCreate() {
		if (DEBUG)
			Log.i(TAG, "onCreate");
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		if (DEBUG)
			Log.i(TAG, "onDestroy");
		super.onDestroy();
	}

	@Override
	public void onRebind(Intent intent) {
		if (DEBUG)
			Log.i(TAG, "onRebind");
		super.onRebind(intent);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (DEBUG)
			Log.i(TAG, "onStartCommand");
		//TODO getPic location
		String path = intent.getStringExtra(ImageSelectActivity.PATH);
		Log.i("xzy", "service path is : " + path);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		if (DEBUG)
			Log.i(TAG, "onUnbind");
		return super.onUnbind(intent);
	}

}
