package com.example.practica03corte02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class AdapterAlumno extends ArrayAdapter<AlumnoItem> implements Filterable {
    private Context context;
    private List<AlumnoItem> originalList;
    private List<AlumnoItem> filteredList;

    public AdapterAlumno(Context context, int resource, List<AlumnoItem> items) {
        super(context, resource, items);
        this.context = context;
        this.originalList = items;
        this.filteredList = new ArrayList<>(items);
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public AlumnoItem getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.activity_adapter_alumno, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.imgAlumno = convertView.findViewById(R.id.imgAlumno);
            viewHolder.txtNombre = convertView.findViewById(R.id.lblNombre);
            viewHolder.txtMatricula = convertView.findViewById(R.id.lblMatricula);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        AlumnoItem item = filteredList.get(position);
        viewHolder.imgAlumno.setImageResource(item.getImageId());
        viewHolder.txtNombre.setText(item.getTextNombre());
        viewHolder.txtMatricula.setText(item.getTextMatricula());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<AlumnoItem> filteredItems = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredItems.addAll(originalList);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (AlumnoItem item : originalList) {
                        if (item.getTextNombre().toLowerCase().contains(filterPattern) ||
                                item.getTextMatricula().toLowerCase().contains(filterPattern)) {
                            filteredItems.add(item);
                        }
                    }
                }

                results.values = filteredItems;
                results.count = filteredItems.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList.clear();
                filteredList.addAll((List<AlumnoItem>) results.values);
                notifyDataSetChanged();
            }
        };

        return filter;
    }

    private static class ViewHolder {
        ImageView imgAlumno;
        TextView txtNombre;
        TextView txtMatricula;
    }
}


