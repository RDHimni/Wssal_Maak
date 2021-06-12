package ridaz.wssalmaak.ui.ceeroffer;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.adapters.AdresseAdapter;
import ridaz.wssalmaak.adapters.PlaceAutoSuggestAdapter;

import ridaz.wssalmaak.databinding.CreeOfferFragmentBinding;
import ridaz.wssalmaak.models.CarModel;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class CreeOfferFragment extends Fragment {

    private static final String TAG = "CreeOfferFragment";

    private CreeOfferViewModel mViewModel;

    private CreeOfferFragmentBinding binding;

    int hour, minute;



    private String villeDepart = "";
    private String LatitudeMapVilleDepart = "";
    private String LongitudeMapVilleDepart = "";

    private String adresseDepart = "";
    private String LatitudeMapAdresseDepart = "";
    private String LongitudeMapAdresseDepart = "";

    private String villeArrive = "";
    private String LatitudeMapVilleArrive = "";
    private String LongitudeMapVilleArrive = "";

    private String heureArive,heuredepart;
    private String dateDepart,dateArive;
    private String dateAndHeureDepart;
    private String dateAndHeureArive;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CreeOfferViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.cree_offer_fragment, container, false);
        DatePicker();
        TimePicker();

        villeDepart = getArguments().getString("villeDepart");
        LatitudeMapVilleDepart = getArguments().getString("LatitudeMapVilleDepart");
        LongitudeMapVilleDepart = getArguments().getString("LongitudeMapVilleDepart");

        adresseDepart = getArguments().getString("adresseDepart");
        LatitudeMapAdresseDepart = getArguments().getString("LatitudeMapAdresseDepart");
        LongitudeMapAdresseDepart = getArguments().getString("LongitudeMapAdresseDepart");

        villeArrive = getArguments().getString("villeArrive");
        LatitudeMapVilleArrive = getArguments().getString("LatitudeMapVilleArrive");
        LongitudeMapVilleArrive = getArguments().getString("LongitudeMapVilleArrive");

        binding.SuivantBtnIncreeOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dateAndHeureDepart = dateDepart + " "+ heuredepart;
                dateAndHeureArive = dateArive + " "+ heureArive;

                if (!dateDepart.isEmpty()){
                    if (!heuredepart.isEmpty()){
                        if (!dateArive.isEmpty()){
                            if (!heureArive.isEmpty()){

                                Bundle bundle = new Bundle();
                                bundle.putString("villeDepart",villeDepart);
                                bundle.putString("LatitudeMapVilleDepart", LatitudeMapVilleDepart);
                                bundle.putString("LongitudeMapVilleDepart", LongitudeMapVilleDepart);

                                bundle.putString("adresseDepart",adresseDepart);
                                bundle.putString("LatitudeMapAdresseDepart", LatitudeMapAdresseDepart);
                                bundle.putString("LongitudeMapAdresseDepart", LongitudeMapAdresseDepart);

                                bundle.putString("villeArrive",villeArrive);
                                bundle.putString("LatitudeMapVilleArrive", LatitudeMapVilleArrive);
                                bundle.putString("LongitudeMapVilleArrive", LongitudeMapVilleArrive);

                                bundle.putString("dateAndHeureDepart",dateAndHeureDepart);
                                bundle.putString("dateAndHeureArrive",dateAndHeureArive);

                                Navigation.findNavController(view).navigate(R.id.action_creeOfferFragment_to_stopeVillesFragment,bundle);

                            }else {
                                Toast.makeText(getContext(), "Saisissez un date de départ !", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(getContext(), "Saisissez une heure de départ !", Toast.LENGTH_SHORT).show();

                        }

                    }else {
                        Toast.makeText(getContext(), "Saisissez un date d'arrivée !", Toast.LENGTH_SHORT).show();

                    }

                }else {
                    Toast.makeText(getContext(), "Saisissez une heure d'arrivée !", Toast.LENGTH_SHORT).show();

                }


            }
        });



        goBack();
        return binding.getRoot();
    }

    private void DatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener0 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String d = day +"/"+month+"/"+year;
                dateDepart = makeDateString(day, month, year).toLowerCase();
                binding.dateDepartBtn.setText(dateDepart);
            }
        };

        DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String d = day +"/"+month+"/"+year;
                dateArive = makeDateString(day, month, year).toLowerCase();
                binding.dateArriveBtn.setText(dateArive);
            }
        };


        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

       // int style = AlertDialog.THEME_HOLO_LIGHT;

       DatePickerDialog datePickerDialog0 = new DatePickerDialog(getContext(),  dateSetListener0, year, month, day);
       DatePickerDialog datePickerDialog1 = new DatePickerDialog(getContext(),  dateSetListener1, year, month, day);

       binding.dateDepartBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               datePickerDialog0.show();
           }
       });


        binding.dateArriveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog1.show();
            }
        });

        /**
        Calendar calendar = Calendar.getInstance();
        calendar.clear();


        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("select Date");
        builder.setSelection(MaterialDatePicker.todayInUtcMilliseconds());

        final MaterialDatePicker materialDatePicker = builder.build();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getChildFragmentManager(), "DATE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                binding.textView.setText(materialDatePicker.getHeaderText());
            }
        });
         */
    }

    private String makeDateString(int day, int month, int year)
    {
        return  day + " " + getMonthFormat(month) + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAI";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }



    private void TimePicker() {

        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        TimePickerDialog.OnTimeSetListener onTimeSetListener0 = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                heuredepart = hour+"h : "+minute+ "min";
                binding.heureDepartBtn.setText(heuredepart);
            }
        };
        TimePickerDialog.OnTimeSetListener onTimeSetListener1 = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                heureArive = hour+"h : "+minute+ "min";
                binding.heureArriveBtn.setText(heureArive);
            }
        };
         int style = MaterialDatePicker.STYLE_NORMAL;

        TimePickerDialog timePickerDialog0 = new TimePickerDialog(getContext(), style, onTimeSetListener0, h, min, true);
        TimePickerDialog timePickerDialog1 = new TimePickerDialog(getContext(), style, onTimeSetListener1, h, min, true);

        //timePickerDialog.setTitle("Select Time");

        binding.heureDepartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog0.show();
            }
        });
        binding.heureArriveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog1.show();
            }
        });

    }

        /**
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        MaterialTimePicker picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(hour)
                .setMinute(minute)
                .setTitleText("Select Appointment time")
                .build();


        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.show(getChildFragmentManager(), "tag");
            }
        });


        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.textView2.setText(picker.getHour()+"h :"+picker.getMinute());

            }
        });
         */



    private void goBack() {
        binding.GoBackIconInCreerOfferFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }


}