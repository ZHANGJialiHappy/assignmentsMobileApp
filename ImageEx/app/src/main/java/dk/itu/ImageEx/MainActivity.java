package dk.itu.ImageEx;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

  private final static String ImageIntent= "android.media.action.IMAGE_CAPTURE";
  private final static int IMAGE_REQUEST= 2;

  private ImageView image;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    image= findViewById(R.id.photo_view);
    Button newImage = findViewById(R.id.take_photo_button);

    newImage.setOnClickListener(v -> {
      Intent intent= new Intent(ImageIntent);
      startActivityForResult(intent, IMAGE_REQUEST);
    });
  }

  public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    super.onActivityResult(requestCode, resultCode, intent);
    if (resultCode == Activity.RESULT_OK) {
      if (requestCode == IMAGE_REQUEST) {
        Bundle extras= intent.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        image.setVisibility(View.VISIBLE);
        image.setImageBitmap(imageBitmap);
      }
    }
  }

}