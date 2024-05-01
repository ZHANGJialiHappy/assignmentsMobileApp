package dk.itu.busdepart;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  final static String RejseplanURL = "https://xmlopen.rejseplanen.dk/bin/rest.exe/departureBoard?offsetTime=0&format=json&id=";
  final static String ITU = "000000900";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TextView contents = findViewById(R.id.content);
    contents.setText("Næste 4 busafgange fra IT-Universitetet\n\n");

    Runnable r= new HttpThread(RejseplanURL+ITU, contents);
    new Thread(r).start();
  }
}