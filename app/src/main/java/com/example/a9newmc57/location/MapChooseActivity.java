/**
 * Created by 9newmc57 on 10/10/2017.
 */
package com.example.a9newmc57.location;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class MapChooseActivity extends Activity implements OnClickListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mca);

        Button regular = (Button)findViewById(R.id.btnRegular);
        regular.setOnClickListener(this);
        Button publictransport = (Button)findViewById(R.id.btnPublictransport);
        publictransport.setOnClickListener(this);

    }

    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        boolean publictransport = false;
        if (v.getId() == R.id.btnPublictransport) {
            publictransport = true;
        }
        bundle.putBoolean("com.example.publictransport", publictransport);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
