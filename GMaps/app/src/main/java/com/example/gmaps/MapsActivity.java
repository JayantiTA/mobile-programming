package com.example.gmaps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.gmaps.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        View.OnClickListener op = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn) {
                    hideKeyBoard(view);
                    goToLocation();
                } else if (view.getId() == R.id.btn_search) {
                    hideKeyBoard(view);
                    goSearch();
                }
            }
        };

        Button go = findViewById(R.id.btn);
        go.setOnClickListener(op);

        Button search = findViewById(R.id.btn_search);
        search.setOnClickListener(op);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ITS = new LatLng(-7.2819705,112.795323);
        mMap.addMarker(new MarkerOptions().position(ITS).title("Marker in ITS"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ITS, 15));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.normal) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            Toast.makeText(this, "Normal", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.terrain) {
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            Toast.makeText(this, "Terrain", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.satellite) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            Toast.makeText(this, "Satellite", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.hybrid) {
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            Toast.makeText(this, "Hybrid", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.none) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
            Toast.makeText(this, "None", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToLocation()  {
        EditText lat = findViewById(R.id.lati);
        EditText lng = findViewById(R.id.longi);
        EditText zoom = findViewById(R.id.zoom);

        Double dbllat = Double.parseDouble(lat.getText().toString());
        Double dbllng = Double.parseDouble(lng.getText().toString());
        float dblzoom = Float.parseFloat(zoom.getText().toString());

        Toast.makeText(this,"Move to Lat:" + dbllat + " Long:" + dbllng,Toast.LENGTH_LONG).show();
        goToMap(dbllat,dbllng,dblzoom);
    }

    private void goToMap(Double lat, Double lng, float z){
        LatLng newLoc = new LatLng(lat,lng);
        mMap.addMarker(new MarkerOptions().
            position(newLoc).
            title("Marker in  " + lat + ":" + lng));
        mMap.moveCamera(CameraUpdateFactory.
            newLatLngZoom(newLoc,z));
    }

    @SuppressLint("SetTextI18n")
    private void goSearch() {
        EditText location = findViewById(R.id.search);
        Geocoder g = new Geocoder(getBaseContext());
        try {
            List<Address> addressList = g.getFromLocationName(location.getText().toString(), 1);
            Address address = addressList.get(0);
            String addressLine =  address.getAddressLine(0);
            double latitude = address.getLatitude();
            double longitude = address.getLongitude();

            Toast.makeText(getBaseContext(),"Found " + addressLine,Toast.LENGTH_LONG).show();
            EditText zoom = findViewById(R.id.zoom);
            float dblzoom = Float.parseFloat(zoom.getText().toString());
            Toast.makeText(this,"Move to " + addressLine + " Lat:" + latitude + " Long:" + longitude,Toast.LENGTH_LONG).show();
            goToMap(latitude, longitude, dblzoom);
            EditText lat = findViewById(R.id.lati);
            EditText lng = findViewById(R.id.longi);
            lat.setText(Double.toString(latitude));
            lng.setText(Double.toString(longitude));

            double dbllat = Double.parseDouble(lat.getText().toString());
            double dbllng = Double.parseDouble(lng.getText().toString());
            countDistance(dbllat, dbllng, latitude, longitude);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void countDistance(double latFrom, Double lngFrom,
                             double latTo, double lngTo)
    {
        Location from = new Location("from");
        Location to = new Location("to");
        to.setLatitude(latTo);
        to.setLatitude(lngTo);
        from.setLatitude(latFrom);
        from.setLongitude(lngFrom);
        float dist = from.distanceTo(to)/1000;
        String dist_str = String.valueOf(dist);
        Toast.makeText(getBaseContext(), "distance : " + dist_str + " km ", Toast.LENGTH_LONG).show();
    }


    private void hideKeyBoard(View v){
        InputMethodManager a = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        a.hideSoftInputFromWindow(v.getWindowToken(),0);
    }

}