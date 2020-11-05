package com.example.spinner;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button spinButton;
    ImageView bottle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinButton = (Button) findViewById(R.id.spinButton);
        bottle = (ImageView) findViewById(R.id.bottle);


        spinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();

                //Sets the degrees and multiplies by 10 for number of spins
                int spinDegree = random.nextInt(3600);

                //finds the actual degree of the last spin
                int spinDegreeActual = spinDegree % 360;

                //Setting how the bottle spins
                //starts at degree 0, spins number of degrees
                RotateAnimation rotateBottle = new RotateAnimation(0, spinDegree, Animation.RELATIVE_TO_SELF,
                                                                   0.5f, Animation.RELATIVE_TO_SELF,
                                                                   0.5f);

                //Setting the bottle to rotate for 2 seconds
                rotateBottle.setDuration(2000);

                //Setting the bottle to display in position after it is done spinning
                rotateBottle.setFillAfter(true);

                //Setting the bottle to speed up and speed down
                rotateBottle.setInterpolator(new AccelerateDecelerateInterpolator());

                //Setting the animation listener to delay the toast
                rotateBottle.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        //Determines whether the left or right person was chosen based off of the degree of the circle
                        if (spinDegreeActual >= 0 && spinDegreeActual <= 90) {
                            Toast.makeText(MainActivity.this, "Top Right Person chosen", Toast.LENGTH_SHORT).show();
                        }

                        else if (spinDegreeActual >= 91 && spinDegreeActual <= 180) {
                            Toast.makeText(MainActivity.this, "Bottom Right Person chosen", Toast.LENGTH_SHORT).show();
                        }

                        else if (spinDegreeActual >= 181 && spinDegreeActual <= 270) {
                            Toast.makeText(MainActivity.this, "Bottom Left Person chosen", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            Toast.makeText(MainActivity.this, "Top Left Person chosen ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                //Starting the animation on the bottle object
                bottle.startAnimation(rotateBottle);
            }
        });
    }
}