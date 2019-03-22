package com.limra.jaipurilohar.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.limra.jaipurilohar.R;

import java.util.ArrayList;
import java.util.List;

public class StateAdapter extends ArrayAdapter<StateCityModel> {

    private List<StateCityModel> stateList = new ArrayList<>();

    StateAdapter(@NonNull Context context, int resource, int spinnerText, @NonNull List<StateCityModel> stateList) {
        super(context, resource, spinnerText, stateList);
        this.stateList = stateList;
    }

    @Override
    public StateCityModel getItem(int position) {
        return stateList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position);

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position);
    }

    /**
     * Gets the state object by calling getItem and
     * Sets the state name to the drop-down TextView.
     *
     * @param position the position of the item selected
     * @return returns the updated View
     */
    private View initView(int position) {
        StateCityModel state = getItem(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.row_spinner, null);
        TextView textView =  v.findViewById(R.id.spinnerText);
        assert state != null;
        textView.setText(state.getStateName());
        return v;

    }
}
