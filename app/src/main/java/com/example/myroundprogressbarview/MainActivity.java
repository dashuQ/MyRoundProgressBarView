package com.example.myroundprogressbarview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.myroundprogressbarview.view.MyRoundProgressBarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.my_round_progress_bar_view)
    MyRoundProgressBarView myRoundProgressBarView;
    private int progress;
    private boolean pbFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.my_round_progress_bar_view)
    public void onViewClicked() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(pbFlag){
                    return;
                }
                   while(progress<=100){
                       pbFlag=true;
                       myRoundProgressBarView.setProgress(progress);
                       progress+=1;
                       try {
                           Thread.sleep(100);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }

                   }
                if(progress>100){
                    progress=0;
                    pbFlag=false;
                }
            }
        }).start();
    }
}
