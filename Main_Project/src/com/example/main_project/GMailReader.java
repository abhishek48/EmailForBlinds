package com.example.main_project;



import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;


public class GMailReader extends javax.mail.Authenticator { 
    private static final String TAG = "GMailReader";

    private String mailhost = "imap.gmail.com";  
    private Session session;
    private Store store;

    public GMailReader(String user, String password) {

        Properties props = System.getProperties();
        if (props == null){
        }else{
        props.setProperty("mail.store.protocol", "imaps");            

       
        }
    try {
        session = Session.getDefaultInstance(props, null);
        store = session.getStore("imaps");
        store.connect(mailhost, user, password);
    } catch (NoSuchProviderException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (MessagingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}

public synchronized Message[] readMail() throws Exception { 
    try { 
        Folder folder = store.getFolder("Inbox"); 
        folder.open(Folder.READ_WRITE);

        /* TODO to rework
        Message[] msgs = folder.getMessages(1, 10);
        FetchProfile fp = new FetchProfile(); 
        fp.add(FetchProfile.Item.ENVELOPE); 
        folder.fetch(msgs, fp);
        */
        FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
        Message msgs[] = folder.search(ft);
      //  Message[] msgs = folder.getMessages();
        try{
        folder.setFlags(msgs, new Flags(Flags.Flag.SEEN), true);}
        catch(Exception e)
        {
        	System.out.println("Sorry this cant be done");
        }
       // folder.close(false);
        return msgs; 
    } catch (Exception e) { 
        return null; 
    } 
} 
}