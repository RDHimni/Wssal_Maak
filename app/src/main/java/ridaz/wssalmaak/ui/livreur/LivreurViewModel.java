package ridaz.wssalmaak.ui.livreur;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LivreurViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LivreurViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Livreur fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}