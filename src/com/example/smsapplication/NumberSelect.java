package com.example.smsapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NumberSelect extends Activity{

	public static final String MyPREFERENCES = "MyPrefs" ;
	Button btnSave;
	EditText PhoneNo1;
	EditText PhoneNo2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.numbers);
		PhoneNo1 = (EditText) findViewById(R.id.editText1);		
		PhoneNo2 = (EditText) findViewById(R.id.editText2);
		btnSave = (Button) findViewById(R.id.button1);

		btnSave.setOnClickListener(new OnClickListener() {			 
			@Override
			public void onClick(View v) { 
				SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
				Editor editor = sharedpreferences.edit();
				editor.putString("PHONE 1", PhoneNo1.getText().toString());
				editor.putString("PHONE 2", PhoneNo2.getText().toString());
				editor.commit();
				Toast.makeText(getApplicationContext(), "Emergency Contacts saved !", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
