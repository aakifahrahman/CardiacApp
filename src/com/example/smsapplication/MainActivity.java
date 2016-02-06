package com.example.smsapplication;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity  implements LocationListener{

	Button btnSend;
	Button setNumbers;
	SharedPreferences sharedpreferences;
	public static final String MyPREFERENCES = "MyPrefs" ;
	LocationManager lm;
	Location l;
	String loc="Unknown Location";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnSend = (Button) findViewById(R.id.send);
		
		btnSend.setOnClickListener(new OnClickListener() {			 
			@Override
			public void onClick(View v) { 
				 sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
				 String phoneNo1 = sharedpreferences.getString("PHONE 1", "");
				 String phoneNo2 = sharedpreferences.getString("PHONE 2", "");
				 
				 lm=(LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
				 lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 200, 1, MainActivity.this);
				 l=lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
				 if(l!=null) { 
					 double lng=l.getLongitude();
					 double lat=l.getLatitude();
					 loc="Latitude : "+Double.toString(lat)+" Longitude : "+Double.toString(lng);
				 }else
						Log.i("aaki", "Could not get location");
				 
				 String msg = "Cardiac Emergency at "+loc;
			  try {
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phoneNo1, null, msg, null, null);
				smsManager.sendTextMessage(phoneNo2, null, msg, null, null);
				Toast.makeText(getApplicationContext(), "Message sent successfully !", Toast.LENGTH_LONG).show();
			  } catch (Exception ex) {
				Toast.makeText(getApplicationContext(), "Could not sent message !", Toast.LENGTH_LONG).show();
				ex.printStackTrace();
			  }
			}
		});
		
		setNumbers = (Button) findViewById(R.id.button1);
		
		setNumbers.setOnClickListener(new OnClickListener() {			 
			@Override
			public void onClick(View v) { 
				Intent myIntent = new Intent(MainActivity.this, NumberSelect.class);
				startActivity(myIntent);
			}
		});
		
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		double lng=l.getLongitude();
	    double lat=l.getLatitude();
	    loc="Latitude : "+Double.toString(lat)+" Longitude : "+Double.toString(lng);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
}
