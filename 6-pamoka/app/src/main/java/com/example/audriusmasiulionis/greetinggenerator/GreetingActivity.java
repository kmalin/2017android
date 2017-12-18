package com.example.audriusmasiulionis.greetinggenerator;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class GreetingActivity extends AppCompatActivity {

    private Switch newYearSwitch;
    private EditText nameEditView;
    private EditText extraTextEditView;

    private ChristmasGreeting getGreeting() {

        String friendsName = nameEditView.getText().toString();
        boolean isNewYearGreeting = newYearSwitch.isChecked();
        String extraMessage = extraTextEditView.getText().toString();

        // Create ChristmasGreeting object and return it here

        return null;
    }

    private void sendSms() {

        ChristmasGreeting greeting = getGreeting();
        if (greeting == null) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", "", null));
        intent.putExtra("sms_body", greeting.getMessageBody());
        startActivity(intent);
    }

    private void sendEmail() {

        ChristmasGreeting greeting = getGreeting();
        if (greeting == null) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[0]);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Sveikinimas");
        intent.putExtra(Intent.EXTRA_TEXT, greeting.getMessageBody());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void sendMessage() {

        ChristmasGreeting greeting = getGreeting();
        if (greeting == null) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, greeting.getMessageBody());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        findViewById(R.id.smsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSms();
            }
        });
        findViewById(R.id.emailButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
        findViewById(R.id.messageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        //set the views
        newYearSwitch = findViewById(R.id.newYearSwitch);
        nameEditView = findViewById(R.id.nameEditText);
        extraTextEditView = findViewById(R.id.extraTextEditText);
    }

}