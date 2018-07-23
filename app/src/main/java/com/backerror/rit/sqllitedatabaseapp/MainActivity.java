package com.backerror.rit.sqllitedatabaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

   EditText userIdET;
   EditText userNameET;
   EditText phoneNumberET;
   Button saveBtn;
   TextView fullDataTV;
    DatabaseHelper databaseHelper=new DatabaseHelper(this);
    List<UserInfo>user=new ArrayList <>( );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        List<UserInfo>user=databaseHelper.getAllData();
       if(user.size()==0) {

           getReadyToDatabase();
       }
       else {
           Toast.makeText( this, "Data All ready saved in your database", Toast.LENGTH_LONG ).show();

       }

        userIdET=findViewById( R.id.userIdEditText );
        userNameET=findViewById( R.id.userNameEditText );
        phoneNumberET=findViewById( R.id.phoneNumberEditText );
        saveBtn=findViewById( R.id.saveDataBtn );
        fullDataTV=findViewById( R.id.fullDataTextView);
        saveBtn.setOnClickListener(this);


    }
    public void getReadyToDatabase(){
        databaseHelper.addUserInfo(new UserInfo( " towfiqul"," 01679168987cw"));
        databaseHelper.addUserInfo( new UserInfo( " rabby"," 1265564cvcg"));
        databaseHelper.addUserInfo( new UserInfo( " Hello"," 297546575"));
        databaseHelper.updateUserInfo( new UserInfo( 1,"tanvir","01578956911" ) );
        databaseHelper.updateUserInfo( new UserInfo( 2,"towfiqul","01679168987" ) );

        Toast.makeText( this,"Data Saved",Toast.LENGTH_LONG ).show();
        //user=databaseHelper.getAllData();
    }

    @Override
    public void onClick(View v) {

       fullDataTV.setText(" "+databaseHelper.getData() + " \nCount user contact: " + databaseHelper.getCountUser());


        }



}
