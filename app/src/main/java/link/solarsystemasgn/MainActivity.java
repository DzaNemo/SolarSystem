package link.solarsystemasgn;

import android.animation.ValueAnimator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    ImageView ivSun, ivMercury ,ivVenus, ivEarth, ivMars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivSun = findViewById(R.id.sun);
        ivMercury = findViewById(R.id.mercury);
        ivVenus = findViewById(R.id.venus);
        ivEarth = findViewById(R.id.earth);
        ivMars = findViewById(R.id.mars);


        //set image view of planet to move and speed of moving
        ValueAnimator anim1 = animatePointer(ivMercury, TimeUnit.SECONDS.toMillis(5));
        ValueAnimator anim2 = animatePointer(ivVenus,TimeUnit.SECONDS.toMillis(10));
        ValueAnimator anim3 = animatePointer(ivEarth,TimeUnit.SECONDS.toMillis(20));
        ValueAnimator anim4 = animatePointer(ivMars,TimeUnit.SECONDS.toMillis(30));

        anim1.start();
        anim2.start();
        anim3.start();
        anim4.start();

    }

    private ValueAnimator animatePointer(final ImageView planet, long orbitDuration) {
        ValueAnimator anim = ValueAnimator.ofInt(0, 359);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) planet.getLayoutParams();
                layoutParams.circleAngle = val;
                planet.setLayoutParams(layoutParams);
            }
        });
        anim.setDuration(orbitDuration);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatMode(ValueAnimator.RESTART);
        anim.setRepeatCount(ValueAnimator.INFINITE);

        return anim;
    }

    //OPTIONAL , this is anticlockwise rotation for planet because it was asked to do in assignment
    private ValueAnimator rotation(final ImageView planet, long orbitDuration) {
        ValueAnimator rotateBack = ValueAnimator.ofInt(359, 0);

        rotateBack.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) planet.getLayoutParams();
                layoutParams.circleAngle = val;
                planet.setLayoutParams(layoutParams);
            }
        });
        rotateBack.setDuration(orbitDuration);
        rotateBack.setInterpolator(new LinearInterpolator());
        rotateBack.setRepeatMode(ValueAnimator.RESTART);
        rotateBack.setRepeatCount(ValueAnimator.INFINITE);

        return rotateBack;
    }
}
