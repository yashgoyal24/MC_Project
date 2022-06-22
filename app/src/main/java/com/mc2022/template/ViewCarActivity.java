package com.mc2022.template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewCarActivity extends AppCompatActivity {
    TextView brand,year,fuel,transmission,distance,mileage;
    Button btnRequest;
    String Hostval = "smtp.gmail.com";
    MimeMessage msg_mime;
    ArrayList<String> data1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_car);
        Intent intent = getIntent();
        data1= intent.getStringArrayListExtra("data");
        brand = findViewById(R.id.brand);
        year = findViewById(R.id.year);
        fuel = findViewById(R.id.fuel);
        transmission = findViewById(R.id.transmission);
        distance = findViewById(R.id.distance);
        mileage = findViewById(R.id.mileage);

        btnRequest = findViewById(R.id.btnRequest);

        brand.setText(data1.get(0));
        year.setText(data1.get(1));
        fuel.setText(data1.get(2));
        transmission.setText(data1.get(3));
        distance.setText(data1.get(4));
        mileage.setText(data1.get(5));

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sendemail();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Car Requested from Buyer", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void sendemail() throws MessagingException {
        String sender_email="carbuysell97@gmail.com";
        String sender_pswd="carbuysell@123";
        String receiver_email=data1.get(6);
        Properties prop=getSYSProperties();

        javax.mail.Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender_email, sender_pswd);
            }
        });

      msg_mime = new MimeMessage(session);
        try {
            msg_mime.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver_email));

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        msg_mime.setSubject("Subject: Your CAR is getting noticed :)");
        msg_mime.setText("Greetings from CAR-BECHO!! \n\n \n\n A buyer is interested in your car.\n\n\n\nRegards,\nCAR-BECHO");


        ExecutorService exe_email = Executors.newCachedThreadPool();

        exe_email.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Transport.send(msg_mime);

                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });


    }
    public Properties getSYSProperties(){
        Properties prop = System.getProperties();

        prop.put("mail.smtp.host", Hostval);
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.auth", "true");
        return prop;
    }

}