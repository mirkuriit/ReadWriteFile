package com.example.readwritefile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.provider.Telephony.Mms.Part.FILENAME;
import static androidx.core.content.PackageManagerCompat.LOG_TAG;

public class MainActivity extends AppCompatActivity {
    void writeFile(EditText name, EditText surname, EditText patronymic, EditText age){
        try {
            BufferedWriter bw = new BufferedWriter(new
                    OutputStreamWriter(openFileOutput(FILENAME,
                    MODE_PRIVATE)));
            if ((age.getText().toString()).equals("") | (name.getText().toString()).equals("")
            | (surname.getText().toString()).equals("") | (patronymic.getText().toString()).equals("")){
                Toast.makeText(getApplicationContext(), "Вы никто", Toast.LENGTH_SHORT).show();
            }
            else{
                bw.write("Приветствую вас, " + surname.getText().toString() +
                        " " + patronymic.getText().toString() + " " + name.getText().toString() + ". \n" +
                        " Карты говорят, что ваш возраст: \n"  +
                        age.getText().toString() );
            }

            bw.close();
            Log.d("LOG_TAG", "Файл записан");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void readFile(TextView text_content_read) {
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(openFileInput (FILENAME)));
            String str_true = "";
            String str = "";
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                Log.d("LOG_TAG", str);
                str_true +=  str;

            }
            text_content_read.setText(str_true);

            br.close();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText name = findViewById(R.id.name);
        EditText surname = findViewById(R.id.surname);
        EditText patronymic = findViewById(R.id.patronymic);
        TextView text_content_read = findViewById(R.id.read_et);
        EditText age = findViewById(R.id.age);
        Button read = findViewById(R.id.read);
        Button write = findViewById(R.id.write);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile(text_content_read);
            }
        });
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFile(name, surname, patronymic, age);
            }
        });
    }
}