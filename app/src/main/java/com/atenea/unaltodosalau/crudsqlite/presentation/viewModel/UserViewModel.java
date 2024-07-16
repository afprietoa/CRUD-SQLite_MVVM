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
package com.atenea.unaltodosalau.crudsqlite.presentation.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.atenea.unaltodosalau.crudsqlite.domain.model.User;
import com.atenea.unaltodosalau.crudsqlite.domain.repository.UserRepository;

import java.util.List;

/**
 * <h1>UserViewModel<h1>
 * ViewModel para la gestión de usuarios.
 * @author afprietoa
 */
public class UserViewModel extends AndroidViewModel {
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Repositorio de usuarios.
     */
    private final UserRepository repository;
    /**
     * Lista de todos los usuarios.
     */
    private final LiveData<List<User>> allUsers;
    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

    /**
     * Constructor del ViewModel de usuarios.
     *
     * @param application La aplicación.
     * <b>pre: </b> La aplicación no es nula.
     * <b>post: </b> Se inicializó el ViewModel con el repositorio y la lista de usuarios.
     */
    public UserViewModel(Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
    }
    //-----------------------------------------------------------------
    // Métodos
    //----------------------------------------------------------------

    /**
     * Obtiene todos los usuarios.
     *
     * @return Una lista de todos los usuarios.
     * <b>post: </b> Se retornó una lista de todos los usuarios en la base de datos.
     */
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
    /**
     * Inserta un nuevo usuario.
     *
     * @param user El usuario a insertar.
     * <b>pre: </b> El usuario no es nulo.
     * <b>post: </b> El usuario se insertó en la base de datos.
     */
    public void insertUser(User user) {
        repository.insertUser(user);
    }
    /**
     * Actualiza un usuario existente.
     *
     * @param user El usuario a actualizar.
     * <b>pre: </b> El usuario no es nulo.
     * <b>post: </b> El usuario se actualizó en la base de datos.
     */
    public void updateUser(User user) {
        repository.updateUser(user);
    }
    /**
     * Elimina un usuario.
     *
     * @param user El usuario a eliminar.
     * <b>pre: </b> El usuario no es nulo.
     * <b>post: </b> El usuario se eliminó de la base de datos.
     */
    public void deleteUser(User user) {
        repository.deleteUser(user);
    }
}