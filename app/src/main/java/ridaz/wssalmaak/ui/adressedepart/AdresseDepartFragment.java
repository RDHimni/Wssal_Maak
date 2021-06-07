package ridaz.wssalmaak.ui.adressedepart;

import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.adapters.AdresseAdapter;
import ridaz.wssalmaak.databinding.FragmentAdresseDepartBinding;
import ridaz.wssalmaak.models.CarModel;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class AdresseDepartFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "AdresseDepartFragment";

    private GoogleMap mMap;
    private LatLng latLng;
    private Address address;
    private String ville = "";
    private String adresseDepart = "";

    private FragmentAdresseDepartBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_adresse_depart, container, false);
        goBack();

        SupportMapFragment mapFragment =(SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapFragInAdresseDepart);
        mapFragment.getMapAsync(this);
        AdresseDepartHandler();

        Suivant();
        return binding.getRoot();
    }

    private void goBack() {
        binding.GoBackIconInAdresseDepartFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void Suivant() {
        binding.SuivantBtnInAdresseDepFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!adresseDepart.isEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("villeDepart", ville);
                    bundle.putString("adresseDepart", adresseDepart);
                    Navigation.findNavController(view).navigate(R.id.action_adresseDepartFragment_to_villeAriveFragment,bundle);

                }
                else {
                    Toast.makeText(getContext(), "Choisez une adresse de depart départ !", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void AdresseDepartHandler() {
        AdresseAdapter adresseAdapter = new AdresseAdapter(getContext());
        binding.AdresseDepList.setAdapter(adresseAdapter);
        binding.AdresseDepList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));

        ville = getArguments().getString("ville");

        binding.searchAdresseDeptv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                adresseAdapter.getFilter().filter("Morocco, "+ville+", "+s);
                binding.AdresseDepList.setVisibility(View.VISIBLE);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        adresseAdapter.setOnItemClickListener(new AdresseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {
                adresseDepart = adresseAdapter.getResults().get(position);
                binding.searchAdresseDeptv.setText(adresseDepart);
                goToAdresse(adresseDepart);
                binding.validAdresseDepIc.setVisibility(View.VISIBLE);
                binding.AdresseDepList.setVisibility(View.GONE);
                binding.mapCrInAdresseDepart.setVisibility(View.VISIBLE);
                binding.SuivantBtnInAdresseDepFrag.setVisibility(View.VISIBLE);
            }
        });

        binding.searchAdresseDeptv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.searchAdresseDeptv.requestFocus();
                binding.validAdresseDepIc.setVisibility(View.INVISIBLE);
                binding.mapCrInAdresseDepart.setVisibility(View.GONE);
                binding.SuivantBtnInAdresseDepFrag.setVisibility(View.GONE);
                binding.AdresseDepList.setVisibility(View.VISIBLE);
            }
        });

        binding.searchAdresseDeptv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                binding.mapCrInAdresseDepart.setVisibility(View.GONE);
                binding.SuivantBtnInAdresseDepFrag.setVisibility(View.GONE);
                binding.validAdresseDepIc.setVisibility(View.INVISIBLE);
                binding.AdresseDepList.setVisibility(View.VISIBLE);

            }
        });

        binding.searchAdresseDeptv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_GO) {

                    String input = binding.searchAdresseDeptv.getText().toString();
                    boolean exist = false;

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    //binding.searchAdresseDeptv.clearFocus();
                   // binding.AdresseDepList.setVisibility(View.GONE);
                   // binding.CarInfosCns.setVisibility(View.VISIBLE);
                    Log.d(TAG, "rida onEditorAction: "+exist);
                    //binding.validModeleIc.setVisibility(View.VISIBLE);

                    adresseDepart =   binding.searchAdresseDeptv.getText().toString();

                    return true;
                }


                return false;
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

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylatlang,14));
        }



    }

    private BitmapDescriptor getBitmapDescriptor(Context context, int vectoreResId){

        Drawable vectorDrawable = ContextCompat.getDrawable(context,vectoreResId);
        vectorDrawable.setBounds(1,1,vectorDrawable.getIntrinsicHeight(),vectorDrawable.getIntrinsicHeight());

        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        vectorDrawable.draw(canvas);

        return BitmapDescriptorFactory.fromBitmap(bitmap);

    }

}