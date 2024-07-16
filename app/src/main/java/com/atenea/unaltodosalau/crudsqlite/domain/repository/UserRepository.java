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
package com.atenea.unaltodosalau.crudsqlite.domain.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.atenea.unaltodosalau.crudsqlite.data.dao.UserDAO;
import com.atenea.unaltodosalau.crudsqlite.data.database.AppDatabase;
import com.atenea.unaltodosalau.crudsqlite.domain.model.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * <h1>UserRepository<h1>
 * Repositorio que maneja las operaciones de los usuarios.
 * @author afprietoa
 */
public class UserRepository {
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * DAO de usuario para acceder a la base de datos.
     */
    private final UserDAO userDAO;
    /**
     * Servicio de ejecución para operaciones en segundo plano.
     */
    private final ExecutorService executorService;
    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

    /**
     * Constructor del repositorio de usuarios.
     *
     * @param application La aplicación.
     * <b>pre: </b> La aplicación no es nula.
     * <b>post: </b> Se inicializó el repositorio con el DAO y el servicio de ejecución.
     */
    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        userDAO = db.userDAO();
        executorService = Executors.newSingleThreadExecutor();
    }
    //-----------------------------------------------------------------
    // Métodos
    //----------------------------------------------------------------

    /**
     * Obtiene todos los usuarios de la base de datos.
     *
     * @return Una lista de todos los usuarios.
     * <b>post: </b> Se retornó una lista de todos los usuarios en la base de datos.
     */
    public LiveData<List<User>> getAllUsers() {
        return userDAO.getAllUsers();
    }
    /**
     * Inserta un usuario en la base de datos.
     *
     * @param user El usuario a insertar.
     * <b>pre: </b> El usuario no es nulo.
     * <b>post: </b> El usuario se insertó en la base de datos.
     */
    public void insertUser(User user) {
        executorService.execute(() -> userDAO.insertUser(user));
    }
    /**
     * Actualiza un usuario en la base de datos.
     *
     * @param user El usuario a actualizar.
     * <b>pre: </b> El usuario no es nulo.
     * <b>post: </b> El usuario se actualizó en la base de datos.
     */
    public void updateUser(User user) {
        executorService.execute(() -> userDAO.updateUser(user));
    }
    /**
     * Elimina un usuario de la base de datos.
     *
     * @param user El usuario a eliminar.
     * <b>pre: </b> El usuario no es nulo.
     * <b>post: </b> El usuario se eliminó de la base de datos.
     */
    public void deleteUser(User user) {
        executorService.execute(() -> userDAO.deleteUser(user));
    }
}