package com.example.main_project;


import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SubjectMailActivity extends Activity implements OnInitListener, OnClickListener{
	ArrayList<String> text;
	static TextToSpeech tts3;
	Button b;
	String subject;
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        tts3=new TextToSpeech(this,this);

	        setContentView(R.layout.subject_sender); 

	        b=(Button) findViewById(R.id.b2);
	        b.setOnClickListener(this);
	     	     
	         
	        	 }   
	 
	 public void onClick(View v) {	
    		
		 	send();							}  
		
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }

		public void onInit(int code) {
			// TODO Auto-generated method stub
		      if (code==TextToSpeech.SUCCESS) {
		    	  tts3.setLanguage(Locale.getDefault());


		    	  } else {
		    	 tts3 = null;
		    	  Toast.makeText(this, "Failed to initialize TTS engine.",
		    	  Toast.LENGTH_SHORT).show();
		    	  }		
		}	

			// TODO Auto-generated method stub
			
		protected void onDestroy() {
		      if (tts3!=null) {
		tts3.stop();


		tts3.shutdown();
		}
		      super.onDestroy();
		} 
	 

	public void send() {
		Log.d("entered send method","send method");
		
		try{
		textts.speak("What is the subject of the mail? Speak now",tts3);
		new Thread().sleep(3500);
		Log.d("spoken- to whom","speech");
		}
		catch(Exception e)
		{
			Log.e("cannot speak- to whom","speech");
		}
		
//		Log.d("spoken","spoken");
		SpeechRecognitionHelper.run(SubjectMailActivity.this,1);
		
	}
	
	
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			switch (resultCode) {		
			case RESULT_OK: {			
				if(data!=null) {
					Toast t = Toast.makeText(getApplicationContext(),
						"Speech Recognized",
						Toast.LENGTH_SHORT);
					t.show();

					text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			}
		
				break;
			}
			
			case RecognizerIntent.RESULT_AUDIO_ERROR:{
			    textts.speak("Audio Error",tts3);
			    break;}
			case RecognizerIntent.RESULT_CLIENT_ERROR:{
			    textts.speak("Client Error",tts3);
			    break;}
			case RecognizerIntent.RESULT_NETWORK_ERROR:{
			    textts.speak("Network Error",tts3);
			    break;}
			case RecognizerIntent.RESULT_NO_MATCH:{
			    textts.speak("No Match",tts3);
			    break;}
			case RecognizerIntent.RESULT_SERVER_ERROR:{
			    textts.speak("Server Error",tts3);
			    break;
			   }
			
		}
		if(data!=null){

			String s = text.get(0);	
			Log.d("going to email the message","entered");
			Toast t = Toast.makeText(getApplicationContext(),
					s,
					Toast.LENGTH_SHORT);
			t.show();
			
			if(s.equalsIgnoreCase("yes"))
			{
				textts.speak("press button to say the body", tts3);
				Bundle bun=getIntent().getExtras();
				bun.putString("subject", subject);
				Intent intent = new Intent(this, BodyMailActivity.class);
				intent.putExtras(bun);
				//intent.putExtra("subject",subject);
				while(tts3.isSpeaking())
				{		
				}
				startActivity(intent);
				finish();
				

			}
			else if(s.equalsIgnoreCase("no"))
			{
				textts.speak("Press and speak the subject again",tts3);
			}
			else if(s.equalsIgnoreCase("discard mail"))
			{
				textts.speak("Mail discarded", tts3);
				try {
					new Thread().sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.finish();
			}
			else{

			s=s.replaceAll("underscore","_");
			s=s.replace("attherate","@");
			subject = s;

			textts.speak(s+" Is this correct? Speak Now.", tts3);
			while(true){
				if(!tts3.isSpeaking()){
			SpeechRecognitionHelper.run(SubjectMailActivity.this,1);
			break;}
			}
			}
			
			
			//new ThreadClass().execute(s);
		}
			else
			{
				textts.speak("Could not recognize anything, press again",tts3);
				try {
					new Thread().sleep(1600);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		//	Log.d("email sent successfully1","entered");

		//	textts.speak("Email sent successfully");

			
			
	}
	 
	

}

