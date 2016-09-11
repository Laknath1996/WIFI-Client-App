package com.example.wifi10;


import android.os.AsyncTask;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by ashwin on 9/1/16.
 */
public class Client extends AsyncTask<Void, Void, Void> {

    String dstAddress;
    int dstPort;
    String response = "";
    String msgToServer;

    TextView textResponse;


    Client(String addr, int port, String serverMsg){
        dstAddress = addr;
        dstPort = port;
        msgToServer = serverMsg;


    }

    @Override

    protected Void doInBackground(Void... arg0){

        Socket socket = null;
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;


        try{

            socket = new Socket(dstAddress,dstPort);

            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            dataInputStream = new DataInputStream(socket.getInputStream());

            if (msgToServer != null){
                dataOutputStream.writeUTF(msgToServer);
            }

            response = dataInputStream.readUTF();

        }catch (UnknownHostException e){
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        }catch (IOException e){
            e.printStackTrace();
            response = "IOException: " + e.toString();
        }finally {
            if (socket != null){
                try{
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            if (dataInputStream != null){
                try{
                    dataInputStream.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }

            if (dataOutputStream != null){
                try{
                    dataOutputStream.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /*@Override

    protected void onPostExecute(Void result){
        textResponse.setText(response);
        super.onPostExecute(result);
    }*/
}
