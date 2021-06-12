package ridaz.wssalmaak.ui.stopevilles;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

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

import java.util.ArrayList;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.adapters.StopeVillesCreateAdapter;
import ridaz.wssalmaak.databinding.FragmentStopeVillesBinding;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class StopeVillesFragment extends Fragment {

    private static final String TAG = "StopeVillesFragment";

    private FragmentStopeVillesBinding binding;

    private String villeDepart = "";
    private String LatitudeMapVilleDepart = "";
    private String LongitudeMapVilleDepart = "";

    private String adresseDepart = "";
    private String LatitudeMapAdresseDepart = "";
    private String LongitudeMapAdresseDepart = "";

    private String villeArrive = "";
    private String LatitudeMapVilleArrive = "";
    private String LongitudeMapVilleArrive = "";

    private String dateAndHeureDepart,dateAndHeureArrive;

    private StopeVillesCreateAdapter stopeVillesCreateAdapter;

private ArrayList<String> villesStopList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_stope_villes, container, false);
        goBack();


        villeDepart = getArguments().getString("villeDepart");
        LatitudeMapVilleDepart = getArguments().getString("LatitudeMapVilleDepart");
        LongitudeMapVilleDepart = getArguments().getString("LongitudeMapVilleDepart");

        adresseDepart = getArguments().getString("adresseDepart");
        LatitudeMapAdresseDepart = getArguments().getString("LatitudeMapAdresseDepart");
        LongitudeMapAdresseDepart = getArguments().getString("LongitudeMapAdresseDepart");

        villeArrive = getArguments().getString("villeArrive");
        LatitudeMapVilleArrive = getArguments().getString("LatitudeMapVilleArrive");
        LongitudeMapVilleArrive = getArguments().getString("LongitudeMapVilleArrive");


        dateAndHeureDepart = getArguments().getString("dateAndHeureDepart");
        dateAndHeureArrive = getArguments().getString("dateAndHeureArrive");






        villesStopeAutocompletHandler();


        binding.SuivantBtnIncStopeVillesFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                bundle.putString("dateAndHeureArrive",dateAndHeureArrive);

                String villesStope = "";

                if (!villesStopList.isEmpty()){


                    StringBuffer stringBuffer = new StringBuffer();

                    for (String v: villesStopList) {
                        stringBuffer.append(v);
                        stringBuffer.append(";");
                    }
                    if (stringBuffer.length()>0)stringBuffer.deleteCharAt(stringBuffer.length()-1);

                    villesStope = stringBuffer.toString();


                }
                bundle.putString("villesStope",villesStope);


                Navigation.findNavController(view).navigate(R.id.action_stopeVillesFragment_to_sizeAndPriceFragment2,bundle);

            }
        });


        return binding.getRoot();
    }


    private void villesStopeAutocompletHandler() {
        String[] villes = getResources().getStringArray(R.array.villes);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),R.layout.ville_list_item,villes);
        binding.searchStopeVillesTv.setAdapter(arrayAdapter);

        stopeVillesCreateAdapter=  new StopeVillesCreateAdapter(getContext(),villesStopList);
        binding.ListVilleStopeInStopeVilleFrag.setAdapter(stopeVillesCreateAdapter);
        binding.ListVilleStopeInStopeVilleFrag.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));


        binding.searchStopeVillesTv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // binding.validStopeVillesIc.setVisibility(View.VISIBLE);
                villesStopList.add(binding.searchStopeVillesTv.getText().toString());

                stopeVillesCreateAdapter.notifyDataSetChanged();
                binding.searchStopeVillesTv.setText("");


                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

            }
        });

        binding.searchStopeVillesTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.searchStopeVillesTv.requestFocus();
                binding.validStopeVillesIc.setVisibility(View.INVISIBLE);


            }
        });

        binding.searchStopeVillesTv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                binding.validStopeVillesIc.setVisibility(View.INVISIBLE);
            }
        });


        binding.searchStopeVillesTv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_GO) {

                    String input = binding.searchStopeVillesTv.getText().toString();
                    boolean exist = false;
                    for (String s: villes) {

                        if (s.equalsIgnoreCase(input)){

                            exist = true;
                            break;
                        }
                    }


                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    Log.d(TAG, "rida onEditorAction: "+exist);
                    //binding.validModeleIc.setVisibility(View.VISIBLE);


                    if (exist){
                       // binding.validStopeVillesIc.setVisibility(View.VISIBLE);
                        Log.d(TAG, "rida onEditorAction: "+exist);
                    }
                    else {
                        //binding.validStopeVillesIc.setVisibility(View.GONE);
                        Log.d(TAG, "rida onEditorAction: "+exist);
                    }

                    villesStopList.add(binding.searchStopeVillesTv.getText().toString());
                    stopeVillesCreateAdapter.notifyDataSetChanged();

                    binding.searchStopeVillesTv.setText("");

                    return true;
                }


                return false;
            }
        });
    }


    private void goBack() {
        binding.GoBackIconInStopeVillesFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }
}