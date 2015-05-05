package com.bchollywood.moderartui;

import android.animation.ArgbEvaluator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;


public class MainActivity extends ActionBarActivity {

    private View topLeftView;
    private View topRightView;
    private View bottomLeftView;
    private View bottomRightView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar mySeekBar = (SeekBar) findViewById(R.id.seekBar);

        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                changeColour(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void changeColour(int progress) {
        int topLeftStartColor = Color.YELLOW;
        int topLeftEndColor = Color.BLUE;
        int bottomRightStartColor = Color.BLUE;
        int bottomRightEndColor = Color.YELLOW;
        int topRightStartColor = Color.RED;
        int topRightEndColor = Color.GREEN;
        int bottomLeftStartColor = Color.GREEN;
        int bottomLeftEndColor = Color.RED;

        ArgbEvaluator interpolator = new ArgbEvaluator();
        float fraction = (float) progress/100;

        topLeftView = (View) findViewById(R.id.topLeft);
        topRightView = (View) findViewById(R.id.topRight);
        bottomLeftView = (View) findViewById(R.id.bottomLeft);
        bottomRightView = (View) findViewById(R.id.bottomRight);

        topLeftView.setBackgroundColor((Integer) interpolator.evaluate(fraction, topLeftStartColor, topLeftEndColor));
        topRightView.setBackgroundColor((Integer) interpolator.evaluate(fraction, topRightStartColor, topRightEndColor));
        bottomLeftView.setBackgroundColor((Integer) interpolator.evaluate(fraction, bottomLeftStartColor, bottomLeftEndColor));
        bottomRightView.setBackgroundColor((Integer) interpolator.evaluate(fraction, bottomRightStartColor, bottomRightEndColor));




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu - adds items to the action bar if it's present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            createDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createDialog() {
        AlertDialog.Builder myDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        myDialogBuilder.setTitle("Inspired by MOMA Artists");
        myDialogBuilder.setCancelable(true);
        myDialogBuilder.setMessage("Click below to learn more!");
        myDialogBuilder.setNegativeButton("Not Now",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        myDialogBuilder.setPositiveButton("Visit MOMA",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent baseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org/m#home"));
                        startActivity(baseIntent);
                    }
                });
        AlertDialog myDialog = myDialogBuilder.create();
        myDialog.show();
    }
}
