package com.example.benja.projet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnvoiInvitation extends AppCompatActivity {

    TextView tv_nomInvite;
    EditText ed_invit;
    Button envoyerInvitation;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoi_invitation);

        i = getIntent();

        tv_nomInvite = (TextView) findViewById(R.id.nomInvite);
        tv_nomInvite.setText("Invitation à : " + i.getStringExtra("nomContact"));

        ed_invit =(EditText) findViewById(R.id.messInvit);
        ed_invit.setText(i.getStringExtra("messageInvitation"));

        envoyerInvitation = (Button) findViewById(R.id.envoiInvit);

        envoyerInvitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envoiSMSMessage();
            }
        });
    }

    public void envoiSMSMessage() {

        String phoneNo =   i.getStringExtra("numeroContact");
        String message = ed_invit.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS d'invitation envoyé !", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERREUR ENVOI SMS", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
