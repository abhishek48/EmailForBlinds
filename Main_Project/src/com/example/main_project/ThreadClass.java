package com.example.main_project;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ThreadClass extends AsyncTask <String ,Void ,Void>{
	protected Void doInBackground(String... params)
	{
        GMailSender sender = new GMailSender("emailforblinds@gmail.com", "emailforblinds1!");
        Log.d("ready to send", "ready to send");  
        try {
			sender.sendMail(params[2],   
			        params[0],   
			         "emailforblinds@gmail.com",   
			         params[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
     	

	}
}
