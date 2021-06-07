package ridaz.wssalmaak.ui.updateprofile.cars;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ridaz.wssalmaak.repo.Repository;

/**
 * Rida Dhimni
 * 23/04/2021
 **/

@HiltViewModel
public class CreateNewCarViewModel extends ViewModel {

    private static final String TAG = "CreateNewCarViewModel";
    private Repository repository;

    @Inject
    public CreateNewCarViewModel(Repository repository) {
        this.repository = repository;
    }
}