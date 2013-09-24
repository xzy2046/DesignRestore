
package xzy.android.designrestore.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * (C) 2013 zhengyang.xu
 * 
 * @author zhengyang.xu
 * @version 0.1
 * @since Sep 20, 2013 1:32:03 AM
 */
public class ImageSelectActivity extends Activity {

    private final static int IMAGE_SELECT = 0xFFFFFF;
    
    public final static String PATH = "path";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        selectPic();
        super.onCreate(savedInstanceState);
    }

    private void selectPic() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE_SELECT);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_SELECT && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            String path = cursor.getString(1); // 获取的是图片的绝对路径
            Log.i("xzy", "path is : " + path);
            Intent intent = new Intent(this, DesignService.class);
            intent.putExtra(PATH, path);
            this.startService(intent);
        }
        this.finish();
        super.onActivityResult(requestCode, resultCode, data);
    }

}
