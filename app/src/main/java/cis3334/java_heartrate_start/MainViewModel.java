package cis3334.java_heartrate_start;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private HeartrateRepository heartrateRepository;

    private LiveData<List<Heartrate>> heartrateList;

    // CHANGED: Added an in-memory cache list so RecyclerView adapter can access by position.
    // This solves the issue where your adapter calls getHeartrateAt(position) / getHeartrateCount()
    // but those methods didn't exist and there was no list stored in the ViewModel.
    private List<Heartrate> cachedHeartrates = new ArrayList<>();

    public MainViewModel (Application application) {
        super(application);
        heartrateRepository = new HeartrateRepository(application);
        heartrateList = heartrateRepository.getAll();
    }

    public void insert(Integer heartrate, Integer age) {
        Heartrate hr = new Heartrate(heartrate, age);
        heartrateRepository.insert(hr);
    }

    public LiveData<List<Heartrate>> getAllHeartrates () {
        // CHANGED: return the existing LiveData (no need to call repository.getAll() repeatedly)
        return heartrateList;
    }

    // CHANGED: Called from MainActivity's observer to keep the latest list for RecyclerView
    public void setCachedHeartrates(List<Heartrate> list) {
        if (list == null) {
            cachedHeartrates = new ArrayList<>();
        } else {
            cachedHeartrates = list;
        }
    }

    // CHANGED: Adapter uses this for getItemCount()
    public int getHeartrateCount() {
        return cachedHeartrates == null ? 0 : cachedHeartrates.size();
    }

    // CHANGED: Adapter uses this for onBindViewHolder()
    public Heartrate getHeartrateAt(int position) {
        if (cachedHeartrates == null) return null;
        if (position < 0 || position >= cachedHeartrates.size()) return null;
        return cachedHeartrates.get(position);
    }
}
