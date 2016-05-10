package com.example.android.miniline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectGameActivity extends AppCompatActivity {

    private ArrayAdapter<String> mForecastAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create some dummy data for the ListView.  Here's a sample weekly forecast
        String[] data = {
                "Tic Tac Toe",
                "Four-in-a-row"
        };
        List<String> games = new ArrayList<>(Arrays.asList(data));

        mForecastAdapter =
                new ArrayAdapter<>(
                        this, // The current context (this activity)
                        R.layout.list_item_game, // The name of the layout ID.
                        R.id.list_item_game_title_view, // The ID of the textview to populate.
                        games);

        ListView listView = (ListView) findViewById(R.id.main_games_listview);
        listView.setAdapter(mForecastAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String forecast = mForecastAdapter.getItem(position);
                Intent intent = new Intent(SelectGameActivity.this, GameTypeActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, forecast);
                startActivity(intent);
            }
        });


    }
}
