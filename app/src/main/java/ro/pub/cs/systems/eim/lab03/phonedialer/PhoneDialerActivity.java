package ro.pub.cs.systems.eim.lab03.phonedialer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class PhoneDialerActivity extends AppCompatActivity {

    private EditText phoneNumberEditText;

    private Button button;
    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
                phoneNumberEditText.setText(phoneNumberEditText.getText().toString() + ((Button) view).getText().toString());
        }
    }
    ButtonListener buttonListener = new ButtonListener();

    private ImageButton backspaceImageButton;
    private class BackspaceImageButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String s = phoneNumberEditText.getText().toString();
            if (s.length() > 0) {
                s = s.substring(0, s.length() - 1);
                phoneNumberEditText.setText(s);
            }
        }
    }
    BackspaceImageButtonListener backspaceImageButtonListener = new BackspaceImageButtonListener();

    private ImageButton callImageButton;
    private class CallImageButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        PhoneDialerActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        Constants.PERMISSION_REQUEST_CALL_PHONE);
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phoneNumberEditText.getText().toString()));
                startActivity(intent);
            }
        }
    }
    CallImageButtonListener callImageButtonListener = new CallImageButtonListener();

    private ImageButton hangupImageButton;
    private class HangupImageButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            finish();
        }
    }
    HangupImageButtonListener hangupImageButtonListener = new HangupImageButtonListener();

    private ImageButton contactsManagerImageButton;
    private class ContactsManagerImageButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String phoneNumber = phoneNumberEditText.getText().toString();
            if (phoneNumber.length() > 0) {
                Intent intent = new Intent("ro.pub.cs.systems.eim.lab04.contactsmanager.intent.action.ContactsManagerActivity");
                intent.putExtra("ro.pub.cs.systems.eim.lab04.contactsmanager.PHONE_NUMBER_KEY", phoneNumber);
                startActivityForResult(intent, Constants.CONTACTS_MANAGER_REQUEST_CODE);
            } else {
                Toast.makeText(getApplication(), "PHONE ERROR", Toast.LENGTH_LONG).show();
            }
        }
    }
    ContactsManagerImageButtonListener contactsManagerImageButtonListener = new ContactsManagerImageButtonListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        button = (Button)findViewById(R.id.digit_1);
        button.setOnClickListener(buttonListener);
        button = (Button)findViewById(R.id.digit_2);
        button.setOnClickListener(buttonListener);
        button = (Button)findViewById(R.id.digit_3);
        button.setOnClickListener(buttonListener);
        button = (Button)findViewById(R.id.digit_4);
        button.setOnClickListener(buttonListener);
        button = (Button)findViewById(R.id.digit_5);
        button.setOnClickListener(buttonListener);
        button = (Button)findViewById(R.id.digit_6);
        button.setOnClickListener(buttonListener);
        button = (Button)findViewById(R.id.digit_7);
        button.setOnClickListener(buttonListener);
        button = (Button)findViewById(R.id.digit_8);
        button.setOnClickListener(buttonListener);
        button = (Button)findViewById(R.id.digit_9);
        button.setOnClickListener(buttonListener);
        button = (Button)findViewById(R.id.star);
        button.setOnClickListener(buttonListener);
        button = (Button)findViewById(R.id.digit_0);
        button.setOnClickListener(buttonListener);
        button = (Button)findViewById(R.id.hashtag);
        button.setOnClickListener(buttonListener);

        backspaceImageButton = (ImageButton)findViewById(R.id.backspace_image_button);
        backspaceImageButton.setOnClickListener(backspaceImageButtonListener);

        callImageButton = (ImageButton)findViewById(R.id.call_image_button);
        callImageButton.setOnClickListener(callImageButtonListener);

        hangupImageButton = (ImageButton)findViewById(R.id.hangup_image_button);
        hangupImageButton.setOnClickListener(hangupImageButtonListener);

        phoneNumberEditText = (EditText)findViewById(R.id.edit_text);

        contactsManagerImageButton = (ImageButton)findViewById(R.id.contacts_manager_button);
        contactsManagerImageButton.setOnClickListener(contactsManagerImageButtonListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case Constants.CONTACTS_MANAGER_REQUEST_CODE:
                Toast.makeText(this, "Activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
                break;
        }
    }

}