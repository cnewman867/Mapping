package com.example.a9newmc57.location;

/**
 * Created by 9newmc57 on 17/10/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class MapSetLocationActivity extends Activity implements OnClickListener{

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);


        Button setLocation = (Button)findViewById(R.id.btnOK);
        setLocation.setOnClickListener(this);

    }

    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        EditText latitude = (EditText)findViewById(R.id.latitude);
        EditText longitude = (EditText)findViewById(R.id.longitude);

        bundle.putDouble("com.example.setLatitude", Double.parseDouble(latitude.getText().toString()));
        bundle.putDouble("com.example.setLongitude", Double.parseDouble(longitude.getText().toString()));
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

}
