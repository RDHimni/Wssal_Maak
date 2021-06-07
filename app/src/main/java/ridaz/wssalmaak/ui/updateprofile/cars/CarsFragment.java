package ridaz.wssalmaak.ui.updateprofile.cars;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.adapters.CarAdapter;
import ridaz.wssalmaak.databinding.CarsFragmentBinding;
import ridaz.wssalmaak.models.Car;

public class CarsFragment extends Fragment {

    private CarsViewModel mViewModel;
    private CarsFragmentBinding binding;
    private CarAdapter carAdapter;
    private ArrayList<Car> list_car;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CarsViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.cars_fragment, container, false);
        addNewCar();
        retrieveData();


        return binding.getRoot();
    }

    private void retrieveData() {
        ///////////////////////////viewModel////////////////////////
        list_car = new ArrayList<>();
        list_car.add(new Car("1234","C","2","DACIA DOCKER","Voiture UE","12/03/23","-6750054"));
        list_car.add(new Car("1234","","","ALFA-ROMEO","Voiture W","12/03/23","-16711680"));
        list_car.add(new Car("1234","","","DACIA DOCKER","Voiture W","12/03/24","-39219"));
        list_car.add(new Car("1234","","","DACIA DOCKER","Voiture W","12/03/23","-16724940"));
        list_car.add(new Car("1234","AB","3","DACIA DOCKER","Voiture UE","12/03/22","-26367"));
        list_car.add(new Car("1234","A","1","DACIA DOCKER","Voiture UE","12/03/23","-16763904"));
        list_car.add(new Car("1234","","","DACIA DOCKER","Voiture W","12/03/22","-6684774"));
        list_car.add(new Car("1234","C","2","DACIA DOCKER","Voiture UE","12/03/23","-6750054"));
        list_car.add(new Car("1234","AB","3","DACIA DOCKER","Voiture UE","12/03/22","-26367"));
        list_car.add(new Car("1234","C","2","DACIA DOCKER","Voiture UE","12/03/23","-6750054"));
        list_car.add(new Car("1234","C","2","DACIA DOCKER","Voiture UE","12/03/23","-6750054"));

        carAdapter = new CarAdapter(getContext(),list_car);
        binding.ListCar.setAdapter(carAdapter);
        binding.ListCar.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
    }

    private void addNewCar() {
        binding.AjouterunenouvellevoitureBtninCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateNewCarActivity.class);
                startActivity(intent);
            }
        });
    }


}