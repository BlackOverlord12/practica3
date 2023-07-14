package com.example.practica03corte02;

import android.widget.Filter;

import com.example.practica03corte02.Alumno;
import com.example.practica03corte02.MiAdaptador;

import java.util.ArrayList;
import java.util.List;

public class AlumnoFilter extends Filter {
    private List<Alumno> originalList;
    private List<Alumno> filteredList;
    private MiAdaptador adapter;

    public AlumnoFilter(List<Alumno> originalList, MiAdaptador adapter) {
        this.originalList = originalList;
        this.filteredList = new ArrayList<>(originalList);
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        filteredList.clear();

        if (constraint == null || constraint.length() == 0) {
            filteredList.addAll(originalList);
        } else {
            String filterPattern = constraint.toString().toLowerCase().trim();

            for (Alumno alumno : originalList) {
                if (alumno.getNombre().toLowerCase().contains(filterPattern)) {
                    filteredList.add(alumno);
                }
            }
        }

        results.values = filteredList;
        results.count = filteredList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.notifyDataSetChanged();
    }

    public List<Alumno> getFilteredList() {
        return filteredList;
    }
}
