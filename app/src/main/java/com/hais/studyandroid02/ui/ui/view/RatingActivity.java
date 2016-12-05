package com.hais.studyandroid02.ui.ui.view;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hais.studyandroid02.ui.R;
import com.hais.studyandroid02.widget.HaisRatingBar;

public class RatingActivity extends Activity {
    private HaisRatingBar r1;
    private HaisRatingBar r2;
    private HaisRatingBar r3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        r1 = (HaisRatingBar) findViewById(R.id.my_1);
        r2 = (HaisRatingBar) findViewById(R.id.my_2);
        r3 = (HaisRatingBar) findViewById(R.id.my_3);

        r1.setOnRatingBarChangeListener(new HaisRatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(HaisRatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(RatingActivity.this, rating + "", Toast.LENGTH_SHORT).show();
            }
        });

        r2.setRatingNums(2.3f);
        //r1.setRatingNums(3.6f);

        Toast.makeText(RatingActivity.this,  r1.getRatingNums() + "", Toast.LENGTH_SHORT).show();

    }


}
