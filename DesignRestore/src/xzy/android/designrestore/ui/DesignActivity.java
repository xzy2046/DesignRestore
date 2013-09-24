
package xzy.android.designrestore.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class DesignActivity extends PreferenceActivity {

    private IDesignService mIDesignService = null;
    
    private boolean mIsConnecting = false;
    
    private CheckBoxPreference mDesignPadCheckBoxPreference;
    
    private final String TAG = "DesignActivity";

    private final boolean DEBUG = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.design_activity);
        addPreferencesFromResource(R.xml.preferences);
        findPreference();
        boolean result = this.bindService((new Intent()).setClass(this, DesignService.class),
                mConnection, BIND_AUTO_CREATE);
        if (!result) {
            finish();
            // ERROR Activity 跟着退出?
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIDesignService = IDesignService.Stub.asInterface(service);
            mIsConnecting = true;

//            boolean startButtonSelected = mSharedPreferences.getBoolean(
//                    getString(R.string.preference_start_button), true);

            if (DEBUG) {
                Log.i(TAG, "service connected success");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            if (DEBUG) {
                Log.i(TAG, "service disconnected success");
            }
            mIDesignService = null;
            mIsConnecting = false;
        }
    };
    
    public void onChoosePicButtonClick(View v) {
    	Log.i("xzy", "onChoosePicButtonClick");
    	Intent intent = new Intent(this, ImageSelectActivity.class);
    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
    	this.startActivity(intent);
    }
    
    private void findPreference() {
    	mDesignPadCheckBoxPreference = (CheckBoxPreference) this.findPreference(this
                .getString(R.string.pref_title_basic));
    	mDesignPadCheckBoxPreference.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
            	//TODO ADD Design pad
            	Log.i("xzy", "DesignPadCheckBox");
            	if (newValue instanceof Boolean) {
            		Log.i("xzy", "values is : " + (Boolean)newValue);
            	}
                return true;
            }

        });
    }
}
