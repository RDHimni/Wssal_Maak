package ridaz.wssalmaak.ui.villesdepart;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.adapters.PlaceAutoSuggestAdapter;
import ridaz.wssalmaak.databinding.FragmentVilleDepartBinding;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class VilleDepartFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "VilleDepartFragment";

    private FragmentVilleDepartBinding binding;
    private GoogleMap mMap;
    private LatLng latLng;
    private Address address;

    private String ville = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_ville_depart, container, false);
        goBack();


        SupportMapFragment mapFragment =(SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapFragInVilleDepart);
        mapFragment.getMapAsync(this);
        villeDepartAutocompletHandler();

        binding.SuivantBtnInVilleDepFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!ville.isEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("ville", ville);
                    Navigation.findNavController(view).navigate(R.id.action_villeDepartFragment_to_adresseDepartFragment,bundle);

                }
                else {
                    Toast.makeText(getContext(), "Choisir la ville de départ !", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return binding.getRoot();
    }

    private void villeDepartAutocompletHandler() {
        String[] villes = getResources().getStringArray(R.array.villes);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),R.layout.ville_list_item,villes);
        binding.searchVilleDepartTv.setAdapter(arrayAdapter);



        binding.searchVilleDepartTv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                binding.searchVilleDepartTv.clearFocus();
                binding.validVilleDepartIc.setVisibility(View.VISIBLE);
                ville = binding.searchVilleDepartTv.getText().toString();

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

                goToAdresse("Morocco, "+ville);
            }
        });

        binding.searchVilleDepartTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.searchVilleDepartTv.requestFocus();
                binding.validVilleDepartIc.setVisibility(View.INVISIBLE);


            }
        });

        binding.searchVilleDepartTv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                binding.validVilleDepartIc.setVisibility(View.INVISIBLE);
            }
        });


        binding.searchVilleDepartTv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_GO) {

                    String input = binding.searchVilleDepartTv.getText().toString();
                    boolean exist = false;
                    for (String s: villes) {

                        if (s.equalsIgnoreCase(input)){

                            exist = true;
                            break;
                        }
                    }


                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    binding.searchVilleDepartTv.clearFocus();
                    Log.d(TAG, "rida onEditorAction: "+exist);
                    //binding.validModeleIc.setVisibility(View.VISIBLE);


                    if (exist){
                        binding.validVilleDepartIc.setVisibility(View.VISIBLE);
                        Log.d(TAG, "rida onEditorAction: "+exist);
                    }
                    else {
                        binding.validVilleDepartIc.setVisibility(View.GONE);
                        Log.d(TAG, "rida onEditorAction: "+exist);
                    }

                    ville = binding.searchVilleDepartTv.getText().toString();

                    goToAdresse("Morocco, "+ville);

                    return true;
                }


                return false;
            }
        });
    }


    private void goBack() {
        binding.GoBackIconInVilleDepartFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }


    private void goToAdresse(String adr) {
        LatLng latLng = getLatLngFromAddress(adr);
        if(latLng!=null) {

            Log.d("rida Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
            address=getAddressFromLatLng(latLng);
            if(address!=null) {
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)).setTitle(address.getAddressLine(0));
                onMapReady(mMap);

                Log.d("rida Address : ", "" + address.toString());
                Log.d("rida Address Line : ",""+address.getAddressLine(0));
                Log.d("rida Phone : ",""+address.getPhone());
                Log.d("rida Pin Code : ",""+address.getPostalCode());
                Log.d("rida Feature : ",""+address.getFeatureName());
                Log.d("rida More : ",""+address.getLocality());
            }
            else {
                Log.d("rida Adddress","Address Not Found");
            }
        }
        else {
            Log.d("rida Lat Lng","Lat Lng Not Found");
        }
    }


    private LatLng getLatLngFromAddress(String address){

        Geocoder geocoder=new Geocoder(getContext());
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(address, 1);
            if(addressList!=null){
                Address singleaddress=addressList.get(0);
                latLng=new LatLng(singleaddress.getLatitude(),singleaddress.getLongitude());
                return latLng;
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    private Address getAddressFromLatLng(LatLng latLng){
        Geocoder geocoder=new Geocoder(getContext());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5);
            if(addresses!=null){
                Address address=addresses.get(0);
                return address;
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);



        LatLng northeast = new LatLng(40.9344, -1.9969759);
        LatLng southwest = new LatLng(15.6672693,-15.3044001 );

        LatLngBounds latLngBounds = new LatLngBounds(southwest,northeast);

        mMap.setLatLngBoundsForCameraTarget(latLngBounds);

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds,10));

        /**
        try {
            // Customize the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getContext(), R.raw.map_style));

            if (!success) {
                Log.e(TAG, "rida Style parsing failed.");

            }else  Log.e(TAG, "rida Style parsing success.");


        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "rida Can't find style. Error: ", e);
        }
         */





        LatLng mylatlang = latLng;
        // Add a marker in Sydney and move the camera
        if (latLng != null) {
            address=getAddressFromLatLng(latLng);

            mMap.addMarker(new MarkerOptions()
                    .position(mylatlang)
                    .icon(getBitmapDescriptor(getContext(),R.drawable.ic_local_taxi))
            );

            if (address != null) mMap.addMarker(new MarkerOptions()
                    .position(mylatlang)
                    .icon(getBitmapDescriptor(getContext(),R.drawable.ic_local_taxi))
            )
                    .setTitle(address.getAddressLine(0))

                    ;

            GroundOverlayOptions homeOverlay = new GroundOverlayOptions()
                    .image(BitmapDescriptorFactory.fromResource(R.drawable.ic_car)).position(latLng, 100);;
           // mMap.addGroundOverlay(homeOverlay);

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylatlang,8));
        }

    }

    private BitmapDescriptor getBitmapDescriptor(Context context,int vectoreResId){

        Drawable vectorDrawable = ContextCompat.getDrawable(context,vectoreResId);
        vectorDrawable.setBounds(1,1,vectorDrawable.getIntrinsicHeight(),vectorDrawable.getIntrinsicHeight());

        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        vectorDrawable.draw(canvas);

        return BitmapDescriptorFactory.fromBitmap(bitmap);

    }
}