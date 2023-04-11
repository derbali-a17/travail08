package ca.qc.bdeb.travail08;

import android.app.Activity;
import android.widget.TextView;

public class DecompteSecondes implements Runnable{

    Activity activity;
    int totalSeconds;
    TextView lblTimer;

    public DecompteSecondes(Activity activity, int totalSeconds, TextView lblTimer) {
        this.activity = activity;
        this.totalSeconds = totalSeconds;
        this.lblTimer = lblTimer;
    }

    @Override
    public void run() {
        int currentSeconds = totalSeconds;
        while(currentSeconds > -1){
            try {
                Thread.sleep(1000);
                String value = Utils.convertSecondsToString(currentSeconds);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lblTimer.setText(value);
                    }
                });
                currentSeconds--;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
