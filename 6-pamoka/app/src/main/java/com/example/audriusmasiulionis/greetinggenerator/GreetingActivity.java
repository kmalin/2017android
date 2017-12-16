package com.example.audriusmasiulionis.greetinggenerator;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class GreetingActivity extends AppCompatActivity {

    private ChristmasGreeting greeting;
    private Switch newYearSwitch;
    private Switch warmthSwitch;
    private Switch familySwitch;
    private Button sendButton;
    private EditText nameEditView;
    private EditText numberEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        //set the views
        newYearSwitch = findViewById(R.id.newYearSwitch);
        warmthSwitch = findViewById(R.id.warmthSwitch);
        familySwitch = findViewById(R.id.familySwitch);
        sendButton = findViewById(R.id.button);
        sendButton.setOnClickListener(new OnClick());
        nameEditView = findViewById(R.id.nameEditText);
        numberEditView = findViewById(R.id.phoneEditText);
    }


    private class OnClick implements View.OnClickListener {
        public void onClick(View view) {
            greeting = new ChristmasGreeting(nameEditView.getText().toString(), numberEditView.getText().toString());

            if (newYearSwitch.isChecked()){
                greeting.addNewYearsGreet();
            }

            if (warmthSwitch.isChecked()){
                greeting.addWarmthGreet();
            }

            if (familySwitch.isChecked()){
                greeting.addBestWishesForFamily();
            }

            sendSms(greeting.getFriendsPhoneNumber(), greeting.toString());
        }
    }


    //DO NOT TOUCH!!!!!
    private void sendSms(String number, String smsContent){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null));
        intent.putExtra("sms_body", smsContent);
        startActivity(intent);
    }

}








