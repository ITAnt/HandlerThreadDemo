package com.itant.handlerthreadtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Handler mConnectHotSpotHandler;
	private boolean mContinue = true;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 一定要调用start方法，否则looper为null
        mConnectThread.start();
        mConnectHotSpotHandler = new Handler(mConnectThread.getLooper()) {
        	@Override
        	public void handleMessage(Message msg) {
        		// TODO Auto-generated method stub
        		super.handleMessage(msg);
        		Toast.makeText(MainActivity.this, "haha", Toast.LENGTH_SHORT).show();
        	}
        };
        mConnectHotSpotHandler.post(mConnectRunnable);
    }
    
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mContinue = false;
		mConnectHotSpotHandler.removeCallbacks(mConnectRunnable);
	}
	
	private Runnable mConnectRunnable = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			mConnectHotSpotHandler.sendEmptyMessage(1);
			//在循环里发消息将不会起作用!!!!!!!!!!!!!!!!!!!!!!!!!!!
			/*while (mContinue) {
				try {
					mConnectHotSpotHandler.sendEmptyMessage(1);
					Thread.sleep(5000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}*/
		}
	};
	
	private ConnectThread mConnectThread = new ConnectThread("connect-thread", Thread.MAX_PRIORITY);
	private class ConnectThread extends HandlerThread implements Callback {

		public ConnectThread(String name, int priority) {
			super(name, priority);
		}

		@Override
		public boolean handleMessage(Message msg) {
			
			return false;
		}
	}
}

