/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad Nacional de Colombia (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Proyecto Todos a la U (http://unaltodosalau.com)
 * Ejercicio: Crud de Usuarios
 * Autor: afprietoa
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.atenea.unaltodosalau.crudsqlite.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.atenea.unaltodosalau.crudsqlite.R;
import com.atenea.unaltodosalau.crudsqlite.core.BaseViewHolder;
import com.atenea.unaltodosalau.crudsqlite.domain.model.User;

/**
 * <h1>UserListAdapter<h1>
 * Adaptador para la lista de usuarios.
 * @author afprietoa
 */
public class UserListAdapter extends ListAdapter<User, BaseViewHolder> {
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Inflador de vistas.
     */
    private final LayoutInflater inflater;
    /**
     * Listener para la edición de usuarios.
     */
    private OnEditClickListener editClickListener;
    /**
     * Listener para la eliminación de usuarios.
     */
    private OnDeleteClickListener deleteClickListener;
    //-----------------------------------------------------------------
    // Interfaces
    //-----------------------------------------------------------------

    /**
     * Interfaz para el listener de edición.
     */
    public interface OnEditClickListener {
        void onEditClick(int position);
    }
    /**
     * Interfaz para el listener de eliminación.
     */
    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }
    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

    /**
     * Constructor del adaptador de la lista de usuarios.
     *
     * @param context El contexto de la aplicación.
     * <b>pre: </b> El contexto no es nulo.
     * <b>post: </b> Se inicializó el adaptador con el contexto proporcionado.
     */
    public UserListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.inflater = LayoutInflater.from(context);
    }
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Establece el listener de edición.
     *
     * @param listener El listener de edición.
     * <b>post: </b> Se estableció el listener de edición.
     */
    public void setOnEditClickListener(OnEditClickListener listener) {
        this.editClickListener = listener;
    }
    /**
     * Establece el listener de eliminación.
     *
     * @param listener El listener de eliminación.
     * <b>post: </b> Se estableció el listener de eliminación.
     */
    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteClickListener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_user_item, parent, false);
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        User user = getItem(position);
        holder.bind(user, editClickListener, deleteClickListener, position);
    }
    /**
     * Callback para calcular las diferencias entre dos listas de usuarios.
     */
    private static final DiffUtil.ItemCallback<User> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<User>() {
                @Override
                public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                    return oldItem.getName().equals(newItem.getName()) &&
                            oldItem.getEmail().equals(newItem.getEmail());
                }
            };
}