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
package com.atenea.unaltodosalau.crudsqlite;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.atenea.unaltodosalau.crudsqlite.presentation.adapter.UserListAdapter;
import com.atenea.unaltodosalau.crudsqlite.domain.model.User;
import com.atenea.unaltodosalau.crudsqlite.presentation.viewModel.UserViewModel;

import java.util.List;

/**
 * <h1>MainActivity<h1>
 * Actividad principal de la aplicación.
 * @author afprietoa
 */
public class MainActivity extends AppCompatActivity {
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * ViewModel de usuarios.
     */
    private UserViewModel userViewModel;
    /**
     * Campo de texto para el nombre del usuario.
     */
    private EditText etlName;

    /**
     * Campo de texto para el correo electrónico del usuario.
     */
    private EditText etlEmail;
    /**
     * Botón para agregar un usuario.
     */
    private Button btnAdd;
    /**
     * Botón para ver todos los usuarios.
     */
    private Button btnView;
    /**
     * Lista para mostrar los usuarios.
     */
    private RecyclerView recyclerView;
    /**
     * Adaptador para la lista de usuarios.
     */
    private UserListAdapter userListAdapter;
    /**
     * Lista de usuarios.
     */
    private List<User> userList;

    //-----------------------------------------------------------------
    // Métodos
    //----------------------------------------------------------------

    /**
     * Método que se llama al crear la actividad.
     *
     * @param savedInstanceState Estado guardado de la actividad.
     * <b>pre: </b> La actividad no se ha creado aún.
     * <b>post: </b> La actividad se ha creado y se han inicializado los componentes.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        etlName = findViewById(R.id.etlName);
        etlEmail = findViewById(R.id.etlEmail);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userListAdapter = new UserListAdapter(this);
        recyclerView.setAdapter(userListAdapter);

        btnAdd.setOnClickListener(view -> addUser());

        userViewModel.getAllUsers().observe(this, users -> {
            userList = users;
            userListAdapter.submitList(users);
        });

        userListAdapter.setOnEditClickListener(position -> {
            User user = userList.get(position);
            etlName.setText(user.getName());
            etlEmail.setText(user.getEmail());
            int idUser = user.getId();
            btnAdd.setText("Save");
            btnAdd.setOnClickListener(view -> updateUser(idUser));
        });

        userListAdapter.setOnDeleteClickListener(position -> {
            User user = userList.get(position);
            deleteUser(user);
        });
    }
    /**
     * Elimina un usuario de la base de datos.
     *
     * @param user El usuario a eliminar.
     * <b>pre: </b> El usuario no es nulo.
     * <b>post: </b> El usuario se eliminó de la base de datos.
     */

    private void deleteUser(User user) {
        userViewModel.deleteUser(user);
        Toast.makeText(MainActivity.this, "User successfully deleted", Toast.LENGTH_SHORT).show();
    }
    /**
     * Actualiza un usuario en la base de datos.
     *
     * @param idUser El ID del usuario a actualizar.
     * <b>pre: </b> El ID del usuario es válido.
     * <b>post: </b> El usuario se actualizó en la base de datos.
     */
    private void updateUser(int idUser) {
        String name = etlName.getText().toString().trim();
        String email = etlEmail.getText().toString().trim();

        if (!name.isEmpty() && !email.isEmpty()) {
            User user = new User(name, email);
            user.setId(idUser);
            userViewModel.updateUser(user);
            Toast.makeText(MainActivity.this, "User successfully updated", Toast.LENGTH_SHORT).show();
            resetForm();
        } else {
            Toast.makeText(MainActivity.this, "Please complete all fields", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Agrega un nuevo usuario a la base de datos.
     * <b>pre: </b> Los campos de nombre y correo no están vacíos.
     * <b>post: </b> Se agregó un nuevo usuario a la base de datos.
     */
    private void addUser() {
        String name = etlName.getText().toString().trim();
        String email = etlEmail.getText().toString().trim();

        if (!name.isEmpty() && !email.isEmpty()) {
            User user = new User(name, email);
            userViewModel.insertUser(user);
            Toast.makeText(MainActivity.this, "User successfully added", Toast.LENGTH_SHORT).show();
            resetForm();
        } else {
            Toast.makeText(MainActivity.this, "Please complete all fields", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Resetea el formulario de entrada de datos.
     */
    private void resetForm() {
        etlName.setText("");
        etlEmail.setText("");
        btnAdd.setText("Add");
        btnAdd.setOnClickListener(view -> addUser());
    }
}