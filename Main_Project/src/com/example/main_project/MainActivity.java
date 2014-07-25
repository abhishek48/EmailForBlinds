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



public class MainActivity extends Activity implements OnClickListener, OnInitListener {
	
	ArrayList<String> text;
	String forTextView;
	private TextToSpeech tts;
	public enum option{
		send,receive
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tts = new TextToSpeech(this,this);  
        setContentView(R.layout.activity_main); 
      Button  b = (Button) findViewById(R.id.button1);
      b.setOnClickListener(this);
   
         
        	 }    
    
    public void onClick(View v) {
    	
    	textts.speak("Say you command", tts);
       	try {
			new Thread().sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	
			SpeechRecognitionHelper.run(MainActivity.this,1);	
							}      
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
			case RecognizerIntent.RESULT_NO_MATCH:{
					textts.speak("No Match",tts);
					break;}
			case RESULT_OK: {			
				if(data!=null) {
					
					text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			}
		
				break;
			}
			
			case RecognizerIntent.RESULT_AUDIO_ERROR:{
			    textts.speak("Audio Error",tts);
			    break;}
			case RecognizerIntent.RESULT_CLIENT_ERROR:{
			    textts.speak("Client Error",tts);
			    break;}
			case RecognizerIntent.RESULT_NETWORK_ERROR:{
			    textts.speak("Network Error",tts);
			    break;}
			
			case RecognizerIntent.RESULT_SERVER_ERROR:{
			    textts.speak("Server Error",tts);
			    break;
			   }
			
		}
		
		if(data!=null){
		String s = text.get(0);	
		if(s.contains("send")){
			s="send";
		}
		
		else if(s.contains("receive"))
		{
			s="receive";
		}
		
		Log.d(s,s);
		try{
		option op=option.valueOf(s);
		switch(op){
		case send:	{Intent intent = new Intent(this, SendMailActivity.class);
					startActivity(intent);
					textts.speak("press button to say the sender mail address", tts);
					break;}
		case receive:
					{
					textts.speak("Wait while we recieve your mails", tts);
					
					Intent intent = new Intent(this, ReceiveMailActivity.class);
					startActivity(intent);
					Log.d("receive spoken","received");
					break;
					}
					
		
		default:textts.speak("Not recognized, please speak again",tts);
		}
		
		}
		catch(Exception e){
			
			textts.speak("Press Again",tts);
		}
		
		
			
		}
		
		
	}   

    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public void onInit(int code) {
		// TODO Auto-generated method stub
	      if (code==TextToSpeech.SUCCESS) {
	    	  tts.setLanguage(Locale.getDefault());


	    	  } else {
	    	  tts = null;
	    	  Toast.makeText(this, "Failed to initialize TTS engine.",
	    	  Toast.LENGTH_SHORT).show();
	    	  }		
	}	

		// TODO Auto-generated method stub
		
	protected void onDestroy() {
	      if (tts!=null) {
	tts.stop();


	tts.shutdown();
	}
	      super.onDestroy();
	}
	
	
	 }
