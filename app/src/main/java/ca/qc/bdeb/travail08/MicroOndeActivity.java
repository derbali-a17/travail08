package ca.qc.bdeb.travail08;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MicroOndeActivity extends AppCompatActivity {

    private TextView lblTimer;
    private ImageView imgOption1;
    private ImageView imgOption2;
    private ImageView imgOption3;
    private ImageView imgOption4;
    private ImageView imgOption5;
    private ImageView imgOption6;
    private ImageView imgOptionStart;
    private ImageView imgOptionStopReset;

    DecompteSecondes decompteSecondes;
    ExecutorService service;
    Future<?> future;
    int nbSeconds = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micro_onde);
        initView();
    }

    public void clickOption(View view) {
        switch (view.getId()) {
            case R.id.imgOption1:
                Log.d("OPTION", "Option 1 cliqué !");
                nbSeconds = 10;
                lblTimer.setText(Utils.convertSecondsToString(nbSeconds));
                break;
            case R.id.imgOption2:
                Log.d("OPTION", "Option 2 cliqué !");
                nbSeconds = 20;
                lblTimer.setText(Utils.convertSecondsToString(nbSeconds));
                break;
            case R.id.imgOption3:
                Log.d("OPTION", "Option 3 cliqué !");
                nbSeconds = 30;
                lblTimer.setText(Utils.convertSecondsToString(nbSeconds));
                break;
            case R.id.imgOption4:
                Log.d("OPTION", "Option 4 cliqué !");
                nbSeconds = 45;
                lblTimer.setText(Utils.convertSecondsToString(nbSeconds));
                break;
            case R.id.imgOption5:
                Log.d("OPTION", "Option 5 cliqué !");
                nbSeconds = 60;
                lblTimer.setText(Utils.convertSecondsToString(nbSeconds));
                break;
            case R.id.imgOption6:
                Log.d("OPTION", "Option 6 cliqué !");
                nbSeconds = 90;
                lblTimer.setText(Utils.convertSecondsToString(nbSeconds));
                break;
            case R.id.imgOptionStart:
                Log.d("OPTION", "Option Start cliqué !");
                if(future !=null && !future.isDone()){
                    future.cancel(true);
                }
                if(nbSeconds != 0)
                    runDecompteSecondes(nbSeconds);
                break;
            case R.id.imgOptionStopReset:
                Log.d("OPTION", "Option Stop/Reset cliqué !");
                if(future != null && !future.isCancelled()){ // mode PAUSE
                    Log.d("OPTION", "MODE PAUSE");
                    nbSeconds = Utils.convertStringSecondsToInt(lblTimer.getText().toString());
                    future.cancel(true);
                } else { // MODE RESET
                    Log.d("OPTION", "MODE RESET");
                    future.cancel(true);
                    nbSeconds = 0;
                    lblTimer.setText(Utils.convertSecondsToString(0));
                }
                break;
        }
    }

    private void runDecompteSecondes(int totalSeds) {

        //créer un thread
        decompteSecondes = new DecompteSecondes(this, totalSeds, lblTimer);
        //créer un service pour demarrer le thread
        service = Executors.newSingleThreadExecutor();
        future = service.submit(decompteSecondes);
        service.shutdown();
    }

    private void initView() {
        lblTimer = (TextView) findViewById(R.id.lbl_timer);
        imgOption1 = (ImageView) findViewById(R.id.imgOption1);
        imgOption2 = (ImageView) findViewById(R.id.imgOption2);
        imgOption3 = (ImageView) findViewById(R.id.imgOption3);
        imgOption4 = (ImageView) findViewById(R.id.imgOption4);
        imgOption5 = (ImageView) findViewById(R.id.imgOption5);
        imgOption6 = (ImageView) findViewById(R.id.imgOption6);
        imgOptionStart = (ImageView) findViewById(R.id.imgOptionStart);
        imgOptionStopReset = (ImageView) findViewById(R.id.imgOptionStopReset);
    }
}