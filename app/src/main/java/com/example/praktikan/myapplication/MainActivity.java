package com.example.praktikan.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText inputText;
    TextView outputText;
    String PREFS_NAME="MYTEXT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText=(EditText)findViewById(R.id.inputText);
        outputText=(TextView)findViewById(R.id.ouputText);
    }
    public void saveSP(View v){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("mytext", inputText.getText().toString());
        editor.commit();
    }
    public void loadSP(View v){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String mytext = settings.getString("mytext", "");
        outputText.setText(mytext);
    }

    public void saveIS(View v){
        String fileName = "MyFile.txt";
        FileOutputStream fos=null;
        try {
            fos =openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(inputText.getText().toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadIS(View v){
        String fileName = "MyFile.txt";
        FileInputStream fis=null;
        try {
            fis =openFileInput(fileName);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            String myData="";
            while ((strLine = br.readLine()) != null) {
                myData = myData + strLine;
            }
            in.close();
            outputText.setText(myData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveES(View v){
        String fileName = "MyFile2.txt";
        File file;
        FileOutputStream fos=null;
        try {
            Log.d("CLOG",Environment.getExternalStorageDirectory().toString());
            file = new File(Environment.getExternalStorageDirectory(), fileName);
            fos = new FileOutputStream(file);
            fos.write(inputText.getText().toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadES(View v){
        String fileName = "MyFile2.txt";
        File file;
        FileInputStream fis=null;
        try {
            file = new File(Environment.getExternalStorageDirectory(), fileName);
            fis =new FileInputStream(file);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            String myData="";
            while ((strLine = br.readLine()) != null) {
                myData = myData + strLine;
            }
            in.close();
            outputText.setText(myData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
