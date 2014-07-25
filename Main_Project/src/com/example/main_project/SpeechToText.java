package com.example.main_project;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.Toast;

public class SpeechToText extends Activity {

	protected static final int RESULT_SPEECH = 1;
	
	ArrayList<String> text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
	
	
	Intent intent = new Intent(
				RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
		
		
		try {
			startActivityForResult(intent,RESULT_SPEECH);
			
		} catch (ActivityNotFoundException a) {
			Toast t = Toast.makeText(getApplicationContext(),
					"Ops! Your device doesn't support Speech to Text",
					Toast.LENGTH_SHORT);
			t.show();
		}
		
	//	return text;
	}
		
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (resultCode) {
		
			case RESULT_OK: {
			
			if(resultCode == RESULT_OK && null != data) {
				
				Toast t = Toast.makeText(getApplicationContext(),
						"Speech Recognized",
						Toast.LENGTH_SHORT);
				t.show();

			text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			}
		
			break;
			}
		
			case RESULT_CANCELED:{
				
			//	Intent j = new Intent(SpeechToText.this,MainActivity.class);
			//	startActivityForResult(j, 1);
				Toast t = Toast.makeText(getApplicationContext(),
						"User pressed cancelled, exiting",
						Toast.LENGTH_SHORT);
				t.show();
				this.finish();}
			
		
		}
		String s = text.get(0);
		Intent in=new Intent();
	    in.putExtra("st",s);
	    setResult(RESULT_OK, in);
	    finish();
	}
	
	}
	
	

