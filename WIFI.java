package com.example.wifi10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WIFI extends AppCompatActivity {

    TextView textResponse;
    EditText editTextAddress, editTextPort,editMessage;
    Button buttonClear,buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        editTextAddress = (EditText) findViewById(R.id.addressEditText);
        editTextPort = (EditText) findViewById(R.id.portEditText);
        buttonClear = (Button) findViewById(R.id.clearButton);
        textResponse = (TextView) findViewById(R.id.responseTextView);
        buttonSend = (Button) findViewById(R.id.sendButton);
        editMessage = (EditText) findViewById(R.id.messageText);


        OnClickListener buttonSendOnClickListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = editMessage.getText().toString();
                if (msg.equals("")){
                    msg = null;
                    Toast.makeText(WIFI.this,"No Message Sent",Toast.LENGTH_SHORT).show();
                }

                Client client = new Client(editTextAddress.getText().toString(),Integer.parseInt(editTextPort.getText().toString()),msg);
                client.execute();
            }
        };

        buttonSend.setOnClickListener(buttonSendOnClickListener);


        buttonClear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                textResponse.setText("");
            }
        });
    }
}


