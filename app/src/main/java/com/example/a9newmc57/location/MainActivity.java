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
        mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);

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
            //react tp the menu item being selected
            Intent intent = new Intent(this,MapChooseActivity.class);
            startActivity(intent);
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



}
