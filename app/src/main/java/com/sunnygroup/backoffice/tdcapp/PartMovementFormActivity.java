package com.sunnygroup.backoffice.tdcapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class PartMovementFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_movement_form);

        ImageView backButton = (ImageView) findViewById(R.id.back_image_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ImageView saveButton = (ImageView) findViewById(R.id.save_image_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSave();
            }
        });

    }

    public void attemptSave() {

        boolean cancel = false;
        View focusView = null;

//        if (!TextUtils.isEmpty()) {
//
//        }

        System.out.println("TESTING");
    }
}
