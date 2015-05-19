package com.android.samay.myedite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;


public class MainActivity extends Activity {

    private EditText myEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myEditView=(EditText)findViewById(R.id.myEditView);
        myEditView.setOnKeyListener(mEditEnterHandler);
    }


    private View.OnKeyListener mEditEnterHandler = new View.OnKeyListener() {
        //PR985527 -haijun.chen-001 modify begin
        int fcousindex = -1 ;
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            switch (event.getAction()) {
                case KeyEvent.ACTION_UP:
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        Log.i("shaohua","  Input DEL key");
                    }
                    else{
                        Log.i("shaohua","Input other key");
                    }
                    break;
                case KeyEvent.ACTION_DOWN:
                    Log.i("shaohua","Input down key");
                    break;
            }
            //PR985527 -haijun.chen-001 modify end
            return false;
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
