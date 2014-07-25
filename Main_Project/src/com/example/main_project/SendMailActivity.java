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

public class SendMailActivity extends Activity implements OnInitListener, OnClickListener{
	ArrayList<String> text;
	static TextToSpeech tts2;
	Button b;
	String address;
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        tts2=new TextToSpeech(this,this);

	        setContentView(R.layout.mailsender); 

	        b=(Button) findViewById(R.id.b2);
	        b.setOnClickListener(this);
	        Toast t7 = Toast.makeText(getApplicationContext(),"Send Activity started",Toast.LENGTH_LONG);
			t7.show();    
	         
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
		    	  tts2.setLanguage(Locale.getDefault());


		    	  } else {
		    	 tts2 = null;
		    	  Toast.makeText(this, "Failed to initialize TTS engine.",
		    	  Toast.LENGTH_SHORT).show();
		    	  }		
		}	

			// TODO Auto-generated method stub
			
		protected void onDestroy() {
		      if (tts2!=null) {
		tts2.stop();


		tts2.shutdown();
		}
		      super.onDestroy();
		} 
	 

	public void send() {
		Log.d("entered send method","send method");
		
		try{
			
		textts.speak("Whom do you want to send the mail? You can also speak letter by letter. Speak now",tts2);
		
			new Thread().sleep(5800);
		Log.d("spoken- to whom","speech");
		}
		catch(Exception e)
		{
			Log.e("cannot speak- to whom","speech");
		}
		
//
		SpeechRecognitionHelper.run(SendMailActivity.this,1);
		
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
			    textts.speak("Audio Error",tts2);
			    break;}
			case RecognizerIntent.RESULT_CLIENT_ERROR:{
			    textts.speak("Client Error",tts2);
			    break;}
			case RecognizerIntent.RESULT_NETWORK_ERROR:{
			    textts.speak("Network Error",tts2);
			    break;}
			case RecognizerIntent.RESULT_NO_MATCH:{
			    textts.speak("No Match",tts2);
			    break;}
			case RecognizerIntent.RESULT_SERVER_ERROR:{
			    textts.speak("Server Error",tts2);
			    break;
			   }
			
		}
		if(data!=null){

			String s = text.get(0);	
			Log.d("going to email the message","entered");
			Toast t6 = Toast.makeText(getApplicationContext(),"data is not null",Toast.LENGTH_LONG);
			t6.show();
			
			if(s.equalsIgnoreCase("yes"))
			{	textts.speak("press button to say the subject", tts2);

				try {
					new Thread().sleep(2300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent intent = new Intent(this, SubjectMailActivity.class);
				Bundle extra=new Bundle();
				extra.putString("address", address);
				intent.putExtras(extra);
				startActivity(intent);		
				
				finish();
			}
			else if(s.equalsIgnoreCase("no"))
			{
				textts.speak("Press and speak the address again",tts2);
			}
			else if(s.equalsIgnoreCase("discard mail"))
			{
				textts.speak("Mail Discarded", tts2);
				try {
					new Thread().sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.finish();
			}
			else {
			s=s.replaceAll("\\s+","");
			s=s.replaceAll("underscore","_");
			s=s.replace("attherate","@");
			
			Toast t5 = Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG);
			t5.show();
			
			tts2.setSpeechRate((float) 0.75);
			
			textts.speak(s+"   "+"Is this correct?", tts2);
			address = s;
			tts2.setSpeechRate(1);
			while(true)
			{
				if(!tts2.isSpeaking()){
			SpeechRecognitionHelper.run(SendMailActivity.this,1);
			break;}
			}
			
			}
			//new ThreadClass().execute(s);
		}
			else
			{
				textts.speak("Could not recognize anything, press again",tts2);
				try {
					new Thread().sleep(1600);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Log.d("email sent successfully1","entered");

		//	textts.speak("Email sent successfully");

			
			
	}
	 
	

}

