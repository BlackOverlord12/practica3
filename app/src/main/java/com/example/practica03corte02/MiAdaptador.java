package com.example.practica03corte02;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolder> implements View.OnClickListener {
    protected ArrayList<Alumno> listaAlumnos;
    private View.OnClickListener listener;
    private List<Alumno> originalList;
    private List<Alumno> filteredList;
    private Context context;
    private LayoutInflater inflater;

    public MiAdaptador(ArrayList<Alumno> listaAlumnos, Context context) {
        this.listaAlumnos = listaAlumnos;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.originalList = new ArrayList<>(listaAlumnos);
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.alumnos_items, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Alumno alumno = listaAlumnos.get(position);
        holder.txtMatricula.setText(alumno.getMatricula());
        holder.txtNombre.setText(alumno.getNombre());
        holder.idImagen.setImageURI(Uri.parse(alumno.getImg()));
        holder.txtCarrera.setText(alumno.getCarrera());

    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        if (listener != null) listener.onClick(v);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private LayoutInflater inflater;
        private TextView txtNombre;
        private TextView txtMatricula;
        private TextView txtCarrera;

        private ImageView idImagen;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.txtAlumnoNombre);
            txtMatricula = (TextView) itemView.findViewById(R.id.txtMatricula);
            txtCarrera = (TextView) itemView.findViewById(R.id.txtCarrera);


            idImagen = (ImageView) itemView.findViewById((R.id.foto));

        }
    }


    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Alumno> filteredItems = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredItems.addAll(originalList);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (Alumno item : originalList ) {
                        if (item.getNombre().toLowerCase().contains(filterPattern) ||
                                item.getMatricula().toLowerCase().contains(filterPattern)) {
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
                listaAlumnos.clear();
                listaAlumnos.addAll((List<Alumno>) results.values);
                notifyDataSetChanged();
            }
        };
    }

}