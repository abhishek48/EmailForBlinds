package com.example.main_project;

import java.io.IOException;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ThreadClass2 extends AsyncTask<Void,Void,Void>{
	private Message[] msges;
//	Context c;
//	private OnTaskCompleted listener;


	protected Void doInBackground(Void... params)
	{
        GMailReader reader = new GMailReader("emailforblinds@gmail.com", "emailforblinds1!");
        Log.d("ready to read", "ready to read");  
        

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
    		
    		for(int i=0;i<msges.length;i++)
    		{
    			Message mess=msges[i];
    			System.out.println("Email Number "+ i+1 );
    			
    			    try {
    					System.out.println("Subject: " + mess.getSubject());
    					
    				} catch (MessagingException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}  
    			    try {
    					System.out.println("From: " + mess.getFrom()[0]);
    					
    				} catch (MessagingException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}  
    			    String s="";
    			    
    			    
    			    Multipart multipart = null;
    				
    					try {
							multipart = (Multipart)mess.getContent();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    				
    				
    			    
    			    {
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
    				
    			    System.out.println("Text: "+s);
    			
    			    }
        	
		} 
        return null;
     	

	}
	
	
	
}