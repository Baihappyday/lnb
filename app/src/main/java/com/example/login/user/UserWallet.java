package com.example.login.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageButton;

import com.example.login.R;

public class UserWallet extends AppCompatActivity {
    ImageButton QRCode;
    ImageButton Wallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_wallet);
        QRCode = (ImageButton)findViewById(R.id.qr_code);
        Wallet = (ImageButton)findViewById(R.id.wallet);
    }
}