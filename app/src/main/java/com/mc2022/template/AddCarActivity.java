package com.mc2022.template;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mc2022.template.model.CarInfo;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class AddCarActivity extends AppCompatActivity {


    Button selectButton;
    Button addButton;
    private AutoCompleteTextView textBrand;
    private AutoCompleteTextView textTrans;
    private AutoCompleteTextView textFuel;
    private static final String PREF= "sharedPref";
    private EditText ManufactYear;
    private EditText KMtravelled;
    private EditText EngineSize;
    private EditText Mileage;
    SharedPreferences sharedPreferences;
    StorageReference storageRef;
    DatabaseReference dbRef;
    int REQUEST_IMAGE_GET=2;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        sharedPreferences = getSharedPreferences(PREF, (getApplicationContext()).MODE_PRIVATE);

        selectButton=(Button)findViewById(R.id.selectButton);
        addButton=(Button)findViewById(R.id.AddButton);


        storageRef= FirebaseStorage.getInstance().getReference();
        dbRef= FirebaseDatabase.getInstance().getReference("CarDetails");

        String strArr[]=getResources().getStringArray(R.array.CarBrand);
        ArrayAdapter<String> adap = new ArrayAdapter<String>(AddCarActivity.this,R.layout.dropdown_brand
                ,strArr);
        adap.setDropDownViewResource(R.layout.dropdown_brand);
        textBrand=findViewById(R.id.autoCompleteTextView5);
        textBrand.setAdapter(adap);

        String strArrTrans[]=getResources().getStringArray(R.array.TransType);
        ArrayAdapter<String> adaptrans = new ArrayAdapter<String>(AddCarActivity.this,R.layout.dropdown_transmission,strArrTrans);
        adaptrans .setDropDownViewResource(R.layout.dropdown_transmission);
        textTrans=findViewById(R.id.autoCompleteTextView6);
        textTrans.setAdapter(adaptrans);

        String strArrFuel[]=getResources().getStringArray(R.array.FuelType);
        ArrayAdapter<String> adapfuel = new ArrayAdapter<String>(AddCarActivity.this,R.layout.dropdown_fueltype,strArrFuel);
        adapfuel.setDropDownViewResource(R.layout.dropdown_fueltype);
        textFuel=findViewById(R.id.autoCompleteTextView7);
        textFuel.setAdapter(adapfuel);

        ManufactYear=findViewById(R.id.editText_manufact);
        KMtravelled=findViewById(R.id.editText_KMtravel);
        EngineSize=findViewById(R.id.editTextEngine);
        Mileage=findViewById(R.id.editTextMileage);




        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_IMAGE_GET);

//                selectImage.launch(new Intent(Intent.ACTION_GET_CONTENT));

            }

        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Brandstr=textBrand.getText().toString();
                String Transstr=textTrans.getText().toString();
                String Fuelstr=textFuel.getText().toString();
                String Manufactstr=ManufactYear.getText().toString();
                String KMtravstr=KMtravelled.getText().toString();
                String Enginestr=EngineSize.getText().toString();
                String Mileagestr=Mileage.getText().toString();

                Log.i("CHECK1","Brand val"+Brandstr);
                Log.i("CHECK2","Mileage val"+Mileagestr);




                if(imageUri!=null && !Brandstr.equals("Brand") && !Transstr.equals("Transmission") && !Fuelstr.equals("Fuel Type") &&
                        !Manufactstr.equals("Manufacturing Year") && !KMtravstr.equals("Kilometers Travelled") &&
                        !Mileagestr.equals("Mileage") && !Enginestr.equals("Engine Size")){
                 storageRef=storageRef.child("details/"+ UUID.randomUUID().toString());

                 storageRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                     @Override
                     public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                          CarInfo c =new CarInfo();
                         String seller_email= sharedPreferences.getString("Email","");
                         Log.i("seller",seller_email);
                          c.setImageUrl(storageRef.getDownloadUrl().toString());
                          c.setBrand(Brandstr);
                          c.setEngineSize(Enginestr);
                          c.setFuelType(Fuelstr);
                          c.setKmTravelled(KMtravstr);
                          c.setManYear(Manufactstr);
                          c.setMileage(Mileagestr);
                          c.setTransmission(Transstr);
                          c.setSeller_email(seller_email);
                          String i=dbRef.push().getKey();
                         dbRef.child(i).setValue(c);
                     }
                 })
                         .addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 Toast.makeText(getApplicationContext(),"Upload failed",Toast.LENGTH_SHORT).show();
                             }
                         });


                }
                else{
                    Toast.makeText(getApplicationContext(),"Please fill the details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getFileExtension(Uri uri) {

        ContentResolver contentResolver = Objects.requireNonNull(getApplicationContext()).getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==REQUEST_IMAGE_GET && resultCode == RESULT_OK && data != null){

            imageUri = data.getData();
            try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                            getContentResolver(),imageUri);
                    selectButton.setText("Image is selected");
                }
            catch (IOException e) {
                            // Log the exception
                            e.printStackTrace();
            }

        }
    }


}