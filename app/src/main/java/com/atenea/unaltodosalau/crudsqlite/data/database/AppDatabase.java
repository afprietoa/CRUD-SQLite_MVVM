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
package com.atenea.unaltodosalau.crudsqlite.data.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;

import com.atenea.unaltodosalau.crudsqlite.data.dao.UserDAO;
import com.atenea.unaltodosalau.crudsqlite.domain.model.User;
/**
 * <h1>AppDatabase<h1>
 * Clase que representa la base de datos de la aplicación.
 * @author afprietoa
 */
@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Instancia única de la base de datos.
     */
    private static AppDatabase INSTANCE;

    //-----------------------------------------------------------------
    // Métodos
    //----------------------------------------------------------------

    /**
     * Retorna la instancia del DAO de usuario.
     *
     * @return El DAO de usuario.
     */
    public abstract UserDAO userDAO();
    /**
     * Retorna la instancia de la base de datos.
     *
     * @param context El contexto de la aplicación.
     * @return La instancia de la base de datos.
     * <b>pre: </b> El contexto no es nulo.
     * <b>post: </b> Se retornó la instancia de la base de datos.
     */
    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "userDB")
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    //-----------------------------------------------------------------
    // Migraciones
    //----------------------------------------------------------------

    /**
     * Migración de la versión 1 a la versión 2 de la base de datos.
     */
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `users_new` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `name` TEXT, `email` TEXT)");
            database.execSQL("INSERT INTO users_new (id, name, email) SELECT id, name, email FROM users");
            database.execSQL("DROP TABLE users");
            database.execSQL("ALTER TABLE users_new RENAME TO users");
        }
    };
}
