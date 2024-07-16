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
package com.atenea.unaltodosalau.crudsqlite.domain.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 * <h1>User<h1>
 * Clase que representa un usuario en la base de datos.
 * @author afprietoa
 */
@Entity(tableName = "users")
public class User {
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * ID del usuario.
     */
    @PrimaryKey(autoGenerate = true)
    private int id;
    /**
     * Nombre del usuario.
     */
    private String name;
    /**
     * Correo electrónico del usuario.
     */
    private String email;
    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

    /**
     * Constructor que inicializa un nuevo usuario.
     *
     * @param name El nombre del usuario.
     * @param email El correo electrónico del usuario.
     * <b>pre: </b> El nombre y el correo no son nulos.
     * <b>post: </b> Se inicializó un nuevo usuario con el nombre y el correo proporcionados.
     */
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    /**
     * Constructor vacío requerido por Room.
     * <b>post: </b> Se inicializó un nuevo usuario vacío.
     */
    public User() {}

    //-----------------------------------------------------------------
    // Métodos
    //----------------------------------------------------------------

    /**
     * Obtiene el ID del usuario.
     *
     * @return El ID del usuario.
     */
    public int getId() {
        return id;
    }
    /**
     * Establece el ID del usuario.
     *
     * @param id El nuevo ID del usuario.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Retorna el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getName() {
        return name;
    }
    /**
     * Establece el nombre del usuario.
     *
     * @param name El nuevo nombre del usuario.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Retorna el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email El nuevo correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}