package ca.qc.bdeb.travail08;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private List<String> pathPhotos;
    int pathIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        pathPhotos = new ArrayList<>();
        pathPhotos.add("http://www.sportbienetre.ca/images/accueil/Administrateur.jpg");
        pathPhotos.add("http://www.sportbienetre.ca/images/accueil/Athletecentre.jpg");
        pathPhotos.add("http://www.sportbienetre.ca/images/accueil/Entraineur.jpg");
        pathPhotos.add("http://www.sportbienetre.ca/images/accueil/Officiel.jpg");
    }

    public void telechargerSuivant(View view) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(pathPhotos.get(pathIndex));
                    HttpURLConnection con=(HttpURLConnection)url.openConnection();
                    con.connect();
                    InputStream input=con.getInputStream();
                    Bitmap myBitmap= BitmapFactory.decodeStream(input);
                    //mise à jour de UI
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(myBitmap);
                            pathIndex++;
                            if(pathIndex == pathPhotos.size()){
                                pathIndex = 0;
                            }
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        service.shutdown();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void AllerAuMicroOnde(View view) {
        startActivity(new Intent(this, MicroOndeActivity.class));
    }
}