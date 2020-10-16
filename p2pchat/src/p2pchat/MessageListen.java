

package p2pchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MessageListen extends Thread {
    ServerSocket ss;
    int port=6607;
    MsgInterface mi;
            

    public MessageListen(MsgInterface mi,int port) {
        this.mi=mi;
        this.port=port;
        try {
            ss=new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MessageListen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public MessageListen() {
        try {
            ss=new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MessageListen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        
        try {
            Socket cs;
            while((cs=ss.accept())!=null){
                InputStream is=cs.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader (is));//kullanıcı verısını okumak
                String message=br.readLine();
                if(message!=null){
                    mi.write(message);
                }
                
                        
            }   
        } catch (Exception ex) {
                System.out.println("accpet hatası");
        }
    }
    
}
