package dk.itu.shopping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.moduleinstall.ModuleInstall;
import com.google.android.gms.common.moduleinstall.ModuleInstallClient;
import com.google.android.gms.tflite.java.TfLite;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;

public class ShoppingActivity extends AppCompatActivity {
    //Shopping using SQLite database and Camera to get a barcode

    // Model: Database of items
    private static ItemsDB itemsDB;

    private TextView newWhat, newWhere;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ModuleInstallClient moduleInstallClient = ModuleInstall.getClient(this);
        OptionalModuleApi optionalModuleApi = TfLite.getClient(this);
        moduleInstallClient
                .areModulesAvailable(optionalModuleApi)
                .addOnSuccessListener(
                        response -> {
                            if (!response.areModulesAvailable()) {
                                moduleInstallClient.deferredInstall(optionalModuleApi);
                            }
                        })
                .addOnFailureListener(
                        e -> {
                            Log.e("ML KIT MODULES", "Something went wrong installing the ML Kit module:", e);
                        });

        setContentView(R.layout.shopping);
        itemsDB = ItemsDB.get(this);

        //Text Fields
        newWhat = findViewById(R.id.what_text);
        newWhere = findViewById(R.id.where_text);
        Button scanBarcode = findViewById(R.id.barcode_button);
        Button listItems = findViewById(R.id.list);

        listItems.setOnClickListener(view -> {
            Intent intent = new Intent(ShoppingActivity.this, ListActivity.class);
            startActivity(intent);
        });

        // GUI variables
        Button addItem = findViewById(R.id.add_button);
        // adding a new thing
        addItem.setOnClickListener(view -> {
            if ((newWhat.getText().length() > 0) && (newWhere.getText().length() > 0)) {
                Item newItem = new Item(
                        newWhat.getText().toString(), newWhere.getText().toString());
                itemsDB.addItem(newItem);
                newWhat.setText("");
                newWhere.setText("");
            } else
                Toast.makeText(ShoppingActivity.this, R.string.empty_toast, Toast.LENGTH_LONG).show();
        });

        //Handling pictures of things
        scanBarcode.setOnClickListener(v -> {
            GmsBarcodeScanner scanner = GmsBarcodeScanning.getClient(this);
            scanner
                    .startScan()
                    .addOnSuccessListener(
                            barcode -> {
                                String rawValue = barcode.getRawValue();
                                newWhat.setText(rawValue);
                            })
                    .addOnCanceledListener(
                            () -> {
                                newWhat.setText("No Barcode");
                            })
                    .addOnFailureListener(
                            e -> {
                                newWhat.setText("No Barcode");
                            });
        });
    }
}
