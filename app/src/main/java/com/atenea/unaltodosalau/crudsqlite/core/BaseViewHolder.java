
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
package com.atenea.unaltodosalau.crudsqlite.core;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.atenea.unaltodosalau.crudsqlite.R;
import com.atenea.unaltodosalau.crudsqlite.domain.model.User;
import com.atenea.unaltodosalau.crudsqlite.presentation.adapter.UserListAdapter;
/**
 * <h1>BaseViewHolder<h1>
 * Clase que representa un ViewHolder para la lista de usuarios.
 * @author afprietoa
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Texto que muestra el ID del usuario.
     */
    private final TextView txtId;
    /**
     * Texto que muestra el nombre del usuario.
     */
    private final TextView txtName;
    /**
     * Texto que muestra el correo electrónico del usuario.
     */
    private final TextView txtEmail;
    /**
     * Botón para editar el usuario.
     */
    private final Button btnEdit;
    /**
     * Botón para eliminar el usuario.
     */
    private final Button btnDelete;

    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

    /**
     * Constructor que inicializa los componentes del ViewHolder.
     *
     * @param view La vista que contiene los componentes.
     * <b>post: </b> Se inicializaron los componentes del ViewHolder.
     */
    public BaseViewHolder(View view) {
        super(view);
        txtId = view.findViewById(R.id.etId);
        txtName = view.findViewById(R.id.etName);
        txtEmail = view.findViewById(R.id.etEmail);
        btnEdit = view.findViewById(R.id.btnEdit);
        btnDelete = view.findViewById(R.id.btnDelete);
    }

    //-----------------------------------------------------------------
    // Métodos
    //----------------------------------------------------------------

    /**
     * Método para enlazar los datos del usuario a los componentes del ViewHolder.
     *
     * @param user El usuario a mostrar.
     * @param editClickListener El listener para el botón de editar.
     * @param deleteClickListener El listener para el botón de eliminar.
     * @param position La posición del usuario en la lista.
     * <b>pre: </b> El usuario no es nulo.
     * <b>post: </b> Se enlazaron los datos del usuario a los componentes del ViewHolder.
     */
    public void bind(User user, UserListAdapter.OnEditClickListener editClickListener, UserListAdapter.OnDeleteClickListener deleteClickListener, int position) {
        txtId.setText(String.valueOf("User Id: " + user.getId()));
        txtName.setText("Name: " + user.getName());
        txtEmail.setText("Email: " + user.getEmail());

        btnEdit.setOnClickListener(view -> {
            if (editClickListener != null) {
                editClickListener.onEditClick(position);
            }
        });

        btnDelete.setOnClickListener(view -> {
            if (deleteClickListener != null) {
                deleteClickListener.onDeleteClick(position);
            }
        });
    }
}