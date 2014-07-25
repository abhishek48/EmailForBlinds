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

public class BodyMailActivity extends Activity implements OnInitListener, OnClickListener{
	ArrayList<String> text;
	static TextToSpeech tts4;
	Button b;
	String body="";
	String extra;
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        tts4=new TextToSpeech(this,this);

	        setContentView(R.layout.body_mail); 

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
		    	  tts4.setLanguage(Locale.getDefault());


		    	  } else {
		    	 tts4 = null;
		    	  Toast.makeText(this, "Failed to initialize TTS engine.",
		    	  Toast.LENGTH_SHORT).show();
		    	  }		
		}	

			// TODO Auto-generated method stub
			
		protected void onDestroy() {
		      if (tts4!=null) {
		tts4.stop();


		tts4.shutdown();
		}
		      super.onDestroy();
		} 
	 

	public void send() {
		Log.d("entered send method","send method");
		
		try{
		textts.speak("What do you want to send? Speak now.",tts4);
		new Thread().sleep(3200);
		Log.d("spoken- to whom","speech");
		}
		catch(Exception e)
		{
			Log.e("cannot speak- to whom","speech");
		}
		
//		Log.d("spoken","spoken");
		SpeechRecognitionHelper.run(BodyMailActivity.this,1);
		
	}
	
	
	
	
	
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			
			if(requestCode==1){
			
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
			    textts.speak("Audio Error",tts4);
			    break;}
			case RecognizerIntent.RESULT_CLIENT_ERROR:{
			    textts.speak("Client Error",tts4);
			    break;}
			case RecognizerIntent.RESULT_NETWORK_ERROR:{
			    textts.speak("Network Error",tts4);
			    break;}
			case RecognizerIntent.RESULT_NO_MATCH:{
			    textts.speak("No Match",tts4);
			    break;}
			case RecognizerIntent.RESULT_SERVER_ERROR:{
			    textts.speak("Server Error",tts4);
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
				
				//Intent intent = new Intent(this, SubjectMailActivity.class);
				//startActivity(intent);
				
				body=body+"\n"+extra;
				textts.speak("Do you want to speak more?", tts4);
				int  reqcode=5;
				try {
					new Thread().sleep(1800);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SpeechRecognitionHelper.run(BodyMailActivity.this,reqcode);
				
			}
			else if(s.equalsIgnoreCase("no"))
			{
				textts.speak("Press and speak the body again",tts4);
			}
			else if(s.equalsIgnoreCase("discard mail"))
			{
				textts.speak("Mail discarded", tts4);
				try {
					new Thread().sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.finish();
			}
			else{
				extra = s;
			s=s.replaceAll("underscore","_");
			s=s.replace("attherate","@");
			textts.speak(s+"   "+"Is this correct? Speak Now.", tts4);
			/*try {
				new Thread().wait(s.length()*250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			while(true){
				if(!tts4.isSpeaking()){
			
			SpeechRecognitionHelper.run(BodyMailActivity.this,1);
			break;
			}
			}
			}
			
			//new ThreadClass().execute(s);
		}
			else
			{
				textts.speak("Could not recognize anything, press again",tts4);
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
			else{
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
				    textts.speak("Audio Error",tts4);
				    break;}
				case RecognizerIntent.RESULT_CLIENT_ERROR:{
				    textts.speak("Client Error",tts4);
				    break;}
				case RecognizerIntent.RESULT_NETWORK_ERROR:{
				    textts.speak("Network Error",tts4);
				    break;}
				case RecognizerIntent.RESULT_NO_MATCH:{
				    textts.speak("No Match",tts4);
				    break;}
				case RecognizerIntent.RESULT_SERVER_ERROR:{
				    textts.speak("Server Error",tts4);
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
					
					//Intent intent = new Intent(this, SubjectMailActivity.class);
					//startActivity(intent);
					//body=body+extra;
					textts.speak("Press Button to speak more.", tts4);					
					
				
				}
				else if(s.equalsIgnoreCase("no"))
				{
					Bundle bun=getIntent().getExtras();
					String add=bun.getString("address");
					String subject=bun.getString("subject");
					new ThreadClass().execute(body,add,subject);
					textts.speak("Mail is being sent. Please Wait", tts4);
					try {
						new Thread().sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.finish();
				}
				else if(s.equalsIgnoreCase("discard mail"))
				{
					textts.speak("Mail discarded", tts4);
					try {
						new Thread().sleep(1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.finish();
				}
				else{
					textts.speak("Could not recognize , speak yes or no again",tts4);
					try {
						new Thread().sleep(3200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					SpeechRecognitionHelper.run(BodyMailActivity.this,5);
					
					
					
				}
			
				
			}
				else
				{
					textts.speak("Could not recognize , speak yes or no again",tts4);
					
					while(true)
					{
						if(!tts4.isSpeaking()){
					
					SpeechRecognitionHelper.run(BodyMailActivity.this,5);
					break;}
					}
				}
				Log.d("email sent successfully1","entered");

			}
	 
	 }

}

