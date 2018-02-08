package com.in.nyk.multithreading;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.in.nyk.multithreading.core.DefaultExecutorSupplier;

/**
 * Created by nikhilkanse on 08/02/18.
 * The following code demonstrates the usage of an handler from a view.
 */

public class ProgressTestActivity extends Activity {
    private ProgressBar progress;
    private TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = (ProgressBar) findViewById(R.id.progressBar1);
        text = (TextView) findViewById(R.id.textView1);
    }

    public void startProgress(View view) {

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i <= 10; i++) {
//                    final int value = i;
//                    doFakeWork();
//                    progress.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            text.setText("Updating");
//                            progress.setProgress(value);
//                        }
//                    });
//                }
//            }
//        };
//
//        Thread thread = new Thread(runnable);
//        thread.start();
        doSomeBackgroundWork();
    }

    public void doSomeBackgroundWork() {
        DefaultExecutorSupplier.getInstance().forBackgroundTasks()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        // do some background work here.
                        for (int i = 0; i <= 10; i++) {
                            final int value = i;
                            doFakeWork();
                            progress.post(new Runnable() {
                                @Override
                                public void run() {
                                    text.setText("Updating");
                                    progress.setProgress(value);
                                }
                            });
                        }
                    }
                });
    }

    // Simulating something timeconsuming
    private void doFakeWork() {
        SystemClock.sleep(5000);
    }
}
