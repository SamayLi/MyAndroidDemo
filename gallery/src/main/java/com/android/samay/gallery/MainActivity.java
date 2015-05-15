package com.android.samay.gallery;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Gallery;
import android.widget.AdapterView;



public class MainActivity extends Activity {

    private int[] myImageIds={R.drawable.photo1,R.drawable.photo2,R.drawable.photo3,
    R.drawable.photo4,R.drawable.photo5,R.drawable.photo6,R.drawable.photo7};

    private  int lastSelectedPosition=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gallery gallery=(Gallery)findViewById(R.id.gallery);


        ImageAdapter imageAdapter=new ImageAdapter(this,myImageIds);
        gallery.setAdapter(imageAdapter);

        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AnimatorSet animatorSet=new AnimatorSet();
                ObjectAnimator imgScaleUpYAnim=ObjectAnimator.ofFloat(view,"scaleY",0.7f,1.05f);
                imgScaleUpYAnim.setDuration(600);
                ObjectAnimator imgScaleUpXAnim=ObjectAnimator.ofFloat(view,"scaleX",0.7f,1.05f);
                imgScaleUpXAnim.setDuration(600);
                animatorSet.playTogether(imgScaleUpYAnim,imgScaleUpXAnim);
                animatorSet.start();

                for(int i=0;i<parent.getChildCount();i++){
                    if(parent.getChildAt(i)!=view){
                        View s=parent.getChildAt(i);
                        ObjectAnimator imgScaleDownYAnim=ObjectAnimator.ofFloat(s,"scaleY",1.05f,0.7f);
                        imgScaleDownYAnim.setDuration(100);
                        ObjectAnimator imgScaleDownXAnim=ObjectAnimator.ofFloat(s,"scaleX",1.05f,0.7f);
                        imgScaleDownXAnim.setDuration(100);
                        animatorSet.playTogether(imgScaleDownXAnim,imgScaleDownYAnim);
                        animatorSet.start();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
