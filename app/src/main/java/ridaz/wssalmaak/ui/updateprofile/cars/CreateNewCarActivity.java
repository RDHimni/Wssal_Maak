package ridaz.wssalmaak.ui.updateprofile.cars;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.res.ObbInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;
import petrov.kristiyan.colorpicker.ColorPicker;
import ridaz.wssalmaak.R;
import ridaz.wssalmaak.adapters.CarsModelAdapter;
import ridaz.wssalmaak.databinding.ActivityCreateNewCarBinding;
import ridaz.wssalmaak.models.CarModel;

@AndroidEntryPoint
public class CreateNewCarActivity extends AppCompatActivity {


    private static final String TAG = "CreateNewCarActivity";
    private ActivityCreateNewCarBinding binding;
    private CarsModelAdapter carsModelAdapter;
    private ArrayList<CarModel> model_list;

    private DatePickerDialog datePickerDialog;

    String dateAssurance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_new_car);
        binding.setLifecycleOwner(this);
        
        CarModelHandler();
        PickDate();
        MatriculeSwitchHandker();

        binding.EnregistrerBtnInCreateNewcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void MatriculeSwitchHandker() {
        binding.switchCarW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(TAG, "rida onCheckedChanged: "+b);
                if (b){
                    binding.switchCarUE.setChecked(false);
                    binding.MatriculeCarEdittext2Id.setFocusable(false);
                    binding.MatriculeCarEdittext3Id.setFocusable(false);
                    binding.MatriculeCarEdittext2Id.setBackground(getResources().getDrawable(R.drawable.button_bg_hided));
                    binding.MatriculeCarEdittext3Id.setBackground(getResources().getDrawable(R.drawable.button_bg_hided));

                }
                else {
                    binding.MatriculeCarEdittext2Id.setFocusable(true);
                    binding.MatriculeCarEdittext2Id.setFocusableInTouchMode(true);
                    binding.MatriculeCarEdittext3Id.setFocusable(true);
                    binding.MatriculeCarEdittext3Id.setFocusableInTouchMode(true);

                    binding.MatriculeCarEdittext2Id.setBackground(getResources().getDrawable(R.drawable.button_bg_slected));
                    binding.MatriculeCarEdittext3Id.setBackground(getResources().getDrawable(R.drawable.button_bg_slected));
                }
            }
        });
        binding.switchCarUE.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(TAG, "rida onCheckedChanged: "+b);
                if (b){
                    binding.switchCarW.setChecked(false);
                    binding.MatriculeCarEdittext2Id.setFocusable(true);
                    binding.MatriculeCarEdittext2Id.setFocusableInTouchMode(true);
                    binding.MatriculeCarEdittext3Id.setFocusable(true);
                    binding.MatriculeCarEdittext3Id.setFocusableInTouchMode(true);
                    binding.MatriculeCarEdittext2Id.setBackground(getResources().getDrawable(R.drawable.button_bg_slected));
                    binding.MatriculeCarEdittext3Id.setBackground(getResources().getDrawable(R.drawable.button_bg_slected));
                }
            }
        });
    }

    private void CarModelHandler() {
        model_list = new ArrayList<>();
        model_list.add(new CarModel("Alpha_Domio1"));
        model_list.add(new CarModel("Alpha_Romio2"));
        model_list.add(new CarModel("alpha_Aomio3"));
        model_list.add(new CarModel("Alpha_Homio4"));
        model_list.add(new CarModel("Alpha_Jomio5"));
        model_list.add(new CarModel("Alpha_Komio6"));
        model_list.add(new CarModel("Alpha_Momio7"));
        model_list.add(new CarModel("Alpha_Somio8"));
        model_list.add(new CarModel("Alpha_Tomio9"));
        model_list.add(new CarModel("Alpha_Jomio10"));
        model_list.add(new CarModel("Alpha_Nomio11"));
        model_list.add(new CarModel("Alpha_Qomio12"));
        model_list.add(new CarModel("Alpha_Womio13"));
        carsModelAdapter = new CarsModelAdapter(this, model_list);
        binding.carsModelList.setAdapter(carsModelAdapter);
        binding.carsModelList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


        carsModelAdapter.setOnItemClickListener(new CarsModelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {
                binding.searchModeletv.setText(model_list.get(position).getCarModel());
                binding.searchModeletv.clearFocus();
                binding.carsModelList.setVisibility(View.GONE);
                binding.CarInfosCns.setVisibility(View.VISIBLE);
                binding.validModeleIc.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            }
        });


        binding.searchModeletv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {


                carsModelAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        binding.searchModeleCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.searchModeletv.requestFocus();
                binding.validModeleIc.setVisibility(View.INVISIBLE);
                binding.CarInfosCns.setVisibility(View.GONE);
                binding.carsModelList.setVisibility(View.VISIBLE);
            }
        });

        binding.searchModeletv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                binding.CarInfosCns.setVisibility(View.GONE);
                binding.validModeleIc.setVisibility(View.INVISIBLE);
                binding.carsModelList.setVisibility(View.VISIBLE);

            }
        });

        binding.searchModeletv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_GO) {

                    String input = binding.searchModeletv.getText().toString();
                    boolean exist = false;
                    for (CarModel m: model_list) {

                        if (m.getCarModel().equals(input)){

                            exist = true;
                            break;
                        }
                    }


                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    binding.searchModeletv.clearFocus();
                    binding.carsModelList.setVisibility(View.GONE);
                    binding.CarInfosCns.setVisibility(View.VISIBLE);
                    Log.d(TAG, "rida onEditorAction: "+exist);
                    //binding.validModeleIc.setVisibility(View.VISIBLE);


                    if (exist){
                        binding.validModeleIc.setVisibility(View.VISIBLE);
                        Log.d(TAG, "rida onEditorAction: "+exist);
                    }
                    else {
                        binding.validModeleIc.setVisibility(View.GONE);
                        Log.d(TAG, "rida onEditorAction: "+exist);
                    }





                    return true;
                }


                return false;
            }
        });
    }


    private void PickDate() {
        initDatePicker();
        binding.DateBtnInCreateNewCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String d = day + "/" + month + "/" + year;
                binding.DateBtnInCreateNewCar.setText(d);
                dateAssurance = d;
            }
        };



        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String today = day + "/" + month + "/" + year;


        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        binding.DateBtnInCreateNewCar.setText(today);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    public void openDatePicker() {
        datePickerDialog.show();
    }


    public void PickColor(View view) {

        ArrayList<String> colors = new ArrayList<>();
        colors.add("#f2f2f2");
        colors.add("#ffff00");
        colors.add("#ccccb3");
        colors.add("#ff0000");
        colors.add("#000000");
        colors.add("#0000ff");
        colors.add("#00cc00");
        colors.add("#ff0066");
        colors.add("#663300");
        colors.add("#003366");
        colors.add("#ff9900");
        colors.add("#0099ff");
        colors.add("#996633");
        colors.add("#003300");
        colors.add("#800000");


        final ColorPicker colorPicker = new ColorPicker(this);
        colorPicker
                .setColors(colors)
                .setColumns(5)
                .setTitle("choisir la couleur")
                .setDefaultColorButton(binding.ColorBtnInCreateNewCar.getCardBackgroundColor().getDefaultColor())
                .setDismissOnButtonListenerClick(true)
                .setRoundColorButton(true)
                .setOnFastChooseColorListener(new ColorPicker.OnFastChooseColorListener() {
                    @Override
                    public void setOnFastChooseColorListener(int position, int color) {
                        binding.ColorBtnInCreateNewCar.setCardBackgroundColor(color);
                        Log.d(TAG, "rida setOnFastChooseColorListener: "+ color);
                    }

                    @Override
                    public void onCancel() {

                    }
                })
                .show();

    }





    public void GoBackIconInCreateNewCar(View view) {
        onBackPressed();
    }
}