package com.example.android.miniline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GameTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_type);

        Button singlePlayerButton = (Button) findViewById(R.id.game_type_single_player_button);
        Button multiPlayerButton = (Button) findViewById(R.id.game_type_multi_player_button);

        if (singlePlayerButton != null) {
            singlePlayerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GameTypeActivity.this, TicTacToe.class);
                    startActivity(intent);
                }
            });
        }

        if (multiPlayerButton != null) {
            multiPlayerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GameTypeActivity.this, MultiPlayerActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
