package org.techtown.borrowing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class WriteActivity extends AppCompatActivity {
    private EditText mWriteTitleText;
    private EditText mWriteContentsText;
    private EditText mWriteNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        mWriteTitleText = findViewById(R.id.write_title_text);
        mWriteContentsText= findViewById(R.id.write_contents_text);
        mWriteNameText=findViewById(R.id.write_name_text);


    }
}