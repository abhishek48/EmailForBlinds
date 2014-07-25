package com.example.main_project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReceiveMailActivity extends Activity implements OnInitListener {
	TextToSpeech tts;
	ArrayList<String> text;
	 Multipart multipart = null;
	 Message mess;
	 int taker = 0;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tts=new TextToSpeech(this, this);
		receive();
	}
	public void receive() {
				// TODO Auto-generated method stub
				try {
                    Log.d("trying to execute thread Class 2", "trying");  

                   new ThreadClass2().execute();
                	
      	          Toast.makeText(getApplicationContext(), "Email was received successfully.", Toast.LENGTH_LONG).show(); 

                } catch (Exception e) {   
                    Log.e("problem", e.getMessage(), e);  
        	        Toast.makeText(getApplicationContext(), "There was a problem sending the email.", Toast.LENGTH_LONG).show(); 

                }
				

			}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}




public class ThreadClass2 extends AsyncTask<Void,Void,Void>{
	private Message[] msges;
//	Context c;
//	private OnTaskCompleted listener;

	protected Void doInBackground(Void... params)
	{
        GMailReader reader = new GMailReader("emailforblinds@gmail.com", "emailforblinds1!");
        Log.d("properties set, ready to read", "ready to read");  
        

        	MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
    		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
    		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
    		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
    		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
    		mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
    		CommandMap.setDefaultCommandMap(mc);
    		try {
    			
    			msges=reader.readMail();
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		if(msges.length==0){
    			
    			textts.speak("No new mails", tts);
    			while(tts.isSpeaking())
				{
					
				}
    			finish();
    		}
    		for(int i=0;i<msges.length;i++)
    		{
    			taker = 0;
    			mess=msges[i];
    			
    			try{
    				int k=i+1;
    				String r="Email Number: "+ k;
    				while(tts.isSpeaking())
    				{
    					
    				}
    			    textts.speak( r,tts);  
    				Log.d("spoken the email number","email");

    			    }
    			catch(Exception e)
    			{
    				Log.e("could not speak the email number","email");
    			}
    			    try {
    			    	String r="Speaking Subject: "+mess.getSubject();
    			    	while(tts.isSpeaking())
        				{
        					
        				}
        			    textts.speak( r,tts);      
        				Log.d("spoken subject","email");

    				} catch (MessagingException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
        				Log.e("could not speak the subject","email");

    				}  
    			    try {
    			    	String r="Email From: "+mess.getFrom()[0];
    			    	while(tts.isSpeaking())
        				{
        					
        				}
    			    	textts.speak(r, tts);
    		//			System.out.println("From: " + mess.getFrom()[0]);
    				} catch (MessagingException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}  
    			    String s="";
    			    
    			    while(tts.isSpeaking())
    				{
    					
    				}
    			    textts.speak("Do you want to listen body?", tts);
    			    try {
						new Thread().sleep(2300);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    			    while(tts.isSpeaking()){}
    			    SpeechRecognitionHelper.run(ReceiveMailActivity.this,1);
    			    
    			   
    				
    			    
    			    
    			    
    				/*	try {
							multipart = (Multipart)mess.getContent();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    				
    			    
    					try {
							for(int j=0;j<multipart.getCount();j++) {
							    BodyPart bodyPart = multipart.getBodyPart(j);
							    if (bodyPart.isMimeType("text/plain")) {
							        s = (String) bodyPart.getContent();
							    }
							}
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
    					
    				
    		//	    System.out.println("Text: "+s);
    			while(taker==0){}
        	
    			
        	
		}

    		while(tts.isSpeaking()){
				
			}
    		textts.speak("All mails read", tts);
    		
    		while(tts.isSpeaking()){
				
			}
			finish();
    		
        return null;
     	

	}
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
	    textts.speak("Audio Error",tts);
	    break;}
	case RecognizerIntent.RESULT_CLIENT_ERROR:{
	    textts.speak("Client Error",tts);
	    break;}
	case RecognizerIntent.RESULT_NETWORK_ERROR:{
	    textts.speak("Network Error",tts);
	    break;}
	case RecognizerIntent.RESULT_NO_MATCH:{
	    textts.speak("No Match",tts);
	    break;}
	case RecognizerIntent.RESULT_SERVER_ERROR:{
	    textts.speak("Server Error",tts);
	    break;
	   }
	
}
if(data!=null){

	String s = text.get(0);	
	Log.d("going to email the message","entered");
	Toast t6 = Toast.makeText(getApplicationContext(),"data is not null",Toast.LENGTH_LONG);
	t6.show();
	
	if(s.equalsIgnoreCase("yes"))
	{	
		s = "";
		try {
			multipart = (Multipart)mess.getContent();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    
		try {
			for(int j=0;j<multipart.getCount();j++) {
			    BodyPart bodyPart = multipart.getBodyPart(j);
			    if (bodyPart.isMimeType("text/plain")) {
			        s = (String) bodyPart.getContent();
			    }
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(tts.isSpeaking())
		{
			
		}
	textts.speak("Text: "+s,tts);
	while(tts.isSpeaking())
	{
		
	}
	taker = 1;
		
	}
	else if(s.equalsIgnoreCase("no"))
	{
		textts.speak("Reading Next Mail",tts);
		while(tts.isSpeaking()){}
		taker = 1;
	}
	else if(s.equalsIgnoreCase("exit"))
	{
		textts.speak("Exiting", tts);
		try {
			new Thread().sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.finish();
	}
	else {
	
	
	}
	//new ThreadClass().execute(s);
}
	else
	{
		textts.speak("Could not recognize anything, press again",tts);
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
	
	public void onInit(int code) {
		// TODO Auto-generated method stub
	      if (code==TextToSpeech.SUCCESS) {
	    	  tts.setLanguage(Locale.getDefault());


	    	  } else {
	    	  tts = null;
	    	
	    	  }		
	}	

		// TODO Auto-generated method stub
		
	protected void onDestroy() {
	      if (tts!=null) {
	tts.stop();


	tts.shutdown();
	}super.onDestroy();
	      
	}

	
}
