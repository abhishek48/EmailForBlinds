package com.example.main_project;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.widget.Toast;

public class SpeechRecognitionHelper  {

	protected static final int RESULT_SPEECH = 1;
	
	
	public static void run(Activity main,int reqcode){
		Log.d("entered run of speech recognition","entered");
	Intent intent = new Intent(	RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
		Log.d("ready to be recognized","entered");

		
		try {
			if(reqcode==1)
			main.startActivityForResult(intent,RESULT_SPEECH);
			else
			main.startActivityForResult(intent, reqcode);	
		Log.d("speech recognized","entered");

			
		} catch (ActivityNotFoundException a) {
			Toast t = Toast.makeText(main.getApplicationContext(),
					"Ops! Your device doesn't support Speech to Text",
					Toast.LENGTH_SHORT);
			t.show();
		}
		
	//	return text;
	}
		
}

	
	
	

