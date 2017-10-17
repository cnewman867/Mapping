package com.example.a9newmc57.location;

import android.app.Activity;
import android.os.Bundle;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import android.location.LocationManager;
import android.location.LocationListener;
import android.location.Location;
import android.content.Context;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;


public class MainActivity extends Activity implements LocationListener {
    /** Called when the activity is first created. */
    MapView mv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager mgr=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);

        mv = (MapView)findViewById(R.id.map1);

        mv.setTileSource(TileSourceFactory.MAPNIK);
        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(14);
        mv.getController().setCenter(new GeoPoint(51.05,-0.72));


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.choosemap) {
            //react to the menu item being selected
            Intent intent = new Intent(this,MapChooseActivity.class);
            startActivityForResult(intent,0);
            return true;
        }

        else if(item.getItemId() == R.id.setLocation) {
            Intent intent = new Intent(this,MapSetLocationActivity.class);
            startActivityForResult(intent,1);
            return true;
        }
        return false;
    }

    public void onLocationChanged(Location newLoc) {
        Toast.makeText(
                this, "Location=" +
                        newLoc.getLatitude()+" " +
                        newLoc.getLongitude(), Toast.LENGTH_LONG).show();
        mv.getController().setCenter(new GeoPoint(newLoc.getLatitude(), newLoc.getLongitude()));
    }

    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Provider " + provider +
        " disabled", Toast.LENGTH_LONG).show();
    }

    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Provider " + provider +
        " enabled", Toast.LENGTH_LONG).show();
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(this, "Status changed: " + status,
                Toast.LENGTH_LONG).show();
    }

    protected  void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {
                Bundle extras = intent.getExtras();
                boolean publictransport = extras.getBoolean("com.example.publictransport");
                if (publictransport == true) {
                    mv.setTileSource(TileSourceFactory.PUBLIC_TRANSPORT);
                } else {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }

            else if(requestCode == 1){
                Bundle extras = intent.getExtras();
                double latitude = extras.getDouble("com.example.setLatitude");
                double longitude = extras.getDouble("com.example.setLongitude");

                //set the latitude and longitude to what has been selected
                mv.getController().setCenter(new GeoPoint(latitude, longitude));
            }
        }


    }

}
