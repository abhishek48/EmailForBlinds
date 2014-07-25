package com.example.main_project;

import android.speech.tts.TextToSpeech;


public class textts {
	public static void speak(String s,TextToSpeech tts)
    {
    	 
    	if (tts!=null) {
	    	  if (s!=null) {
	    	                        if (!tts.isSpeaking())
	    		  {
	    	  tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
	    	  }}
	    	  }	
    }
	
	public static void speakadd(String s,TextToSpeech tts)
    {
    	 
    	if (tts!=null) {
	    	  if (s!=null) {
	    	                        if (!tts.isSpeaking())
	    		  {
	    	  tts.speak(s, TextToSpeech.QUEUE_ADD, null);
	    	  }}
	    	  }	
    }
    
}
