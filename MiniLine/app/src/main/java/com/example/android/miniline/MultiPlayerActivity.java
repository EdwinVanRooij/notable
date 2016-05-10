package com.example.android.miniline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MultiPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);

        Button onlineButton = (Button) findViewById(R.id.multi_player_online_button);
        Button offlineButton = (Button) findViewById(R.id.multi_player_offline_button);

        if (onlineButton != null) {
            onlineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MultiPlayerActivity.this, SearchActivity.class);
                    startActivity(intent);
                }
            });
        }

        if (offlineButton!= null) {
            offlineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MultiPlayerActivity.this, TicTacToe.class);
                    startActivity(intent);
                }
            });
        }
    }
}
