package dk.itu.shopping;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class ShoppingActivity extends AppCompatActivity {
  //Shopping using SQLite database and Camera
  private final static String ImageIntent= "android.media.action.IMAGE_CAPTURE";
  private final static int IMAGE_REQUEST= 2;

  // Model: Database of items
  private static ItemsDB itemsDB;

  // GUI variables
  private Button addItem, listItems, newImage;
  private TextView newWhat, newWhere;
  private ImageView image;
  private Bitmap imageBitmap;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.shopping);
    itemsDB = ItemsDB.get(this);
    newWhat=  findViewById(R.id.what_text);
    newWhere= findViewById(R.id.where_text);
    image= findViewById(R.id.photo_view);
    newImage= findViewById(R.id.take_photo_button);
    listItems= findViewById(R.id.list);

    listItems.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent = new Intent(ShoppingActivity.this, ListActivity.class);
          startActivity(intent);
        }
      });

    addItem= findViewById(R.id.add_button);
    addItem.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if ((newWhat.getText().length() > 0) && (newWhere.getText().length() > 0)) {
          Item newItem= new Item(
              newWhat.getText().toString(), newWhere.getText().toString());
          if (imageBitmap != null)  newItem.setPict(Item.scaleAndConvert(imageBitmap));
          itemsDB.addItem(newItem);
          newWhat.setText("");
          newWhere.setText("");
          image.setVisibility(View.INVISIBLE);
          imageBitmap= null;
        } else Toast.makeText(ShoppingActivity.this, R.string.empty_toast, Toast.LENGTH_LONG).show();
      }
    });

    //Handling pictures of things
    newImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ImageIntent);
        startActivityForResult(intent, IMAGE_REQUEST);
      }
    });
  }

  public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    super.onActivityResult(requestCode, resultCode, intent);
    if (resultCode == Activity.RESULT_OK) {
      if (requestCode == IMAGE_REQUEST) {
        Bundle extras= intent.getExtras();
        imageBitmap= (Bitmap) extras.get("data");
        image.setVisibility(View.VISIBLE);
        image.setImageBitmap(imageBitmap);
      }
    }
  }
}
