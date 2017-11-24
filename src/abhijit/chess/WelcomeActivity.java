package abhijit.chess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		Button singlePlayer=(Button)findViewById(R.id.button1);
		Button doublePlayer=(Button)findViewById(R.id.button2);
		doublePlayer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(WelcomeActivity.this,DoublePlayer.class));
				
			}
		});
/*		doublePlayer.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Intent intent=new Intent(WelcomeActivity.this,DoublePlayer.class);
				startActivity(intent);
				return false;
			}
			
		
		});*/
	/*	singlePlayer.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Intent intent=new Intent(WelcomeActivity.this,SinglePlayer.class);
				startActivity(intent);
				return false;
			}
			
		
		});*/
	}
	
	public void singlePlayer (View v) {
		startActivity(new Intent(WelcomeActivity.this,SinglePlayer.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
