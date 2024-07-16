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
package com.atenea.unaltodosalau.crudsqlite.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.atenea.unaltodosalau.crudsqlite.domain.model.User;

import java.util.List;
/**
 * <h1>UserDAO<h1>
 * DAO para acceder a los datos de los usuarios.
 * @author afprietoa
 */
@Dao
public interface UserDAO {
    //-----------------------------------------------------------------
    // Métodos
    //----------------------------------------------------------------

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param user El usuario a insertar.
     * @return El ID del usuario insertado.
     * <b>pre: </b> El usuario no es nulo.
     * <b>post: </b> El usuario se insertó en la base de datos.
     */
    @Insert
    long insertUser(User user);
    /**
     * Actualiza un usuario existente en la base de datos.
     *
     * @param user El usuario a actualizar.
     * <b>pre: </b> El usuario no es nulo.
     * <b>post: </b> El usuario se actualizó en la base de datos.
     */
    @Update
    void updateUser(User user);
    /**
     * Elimina un usuario de la base de datos.
     *
     * @param user El usuario a eliminar.
     * <b>pre: </b> El usuario no es nulo.
     * <b>post: </b> El usuario se eliminó de la base de datos.
     */
    @Delete
    void deleteUser(User user);
    /**
     * Obtiene todos los usuarios de la base de datos.
     *
     * @return Una lista de todos los usuarios.
     * <b>post: </b> Se retornó una lista de todos los usuarios en la base de datos.
     */
    @Query("SELECT * FROM users")
    LiveData<List<User>> getAllUsers();
}