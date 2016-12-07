package main.java.com.fcastelain.cf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by fcastelain on 28/11/16.
 */
public interface DaoCrud<T> {
        /**
         * Delete the entity of the object in function of its id in the database
         * @param id of the element in the data base
         */
        void delete(final long id);

        /**
         * Use to get a list of elements in function of the page
         * @return the list of object on the precise page
         */
        List<T> get();

        /**
         * Return an instance of an object in function of its id
         * @param idObj is the id of the object in the data base
         * @return the object on with the precise id
         */
        T getById(final long idObj);

        /**
         * Return the number of entity in the tables.
         * @return the number
         * @throws DaoException in case of faillure
         */
        long getNbEntity();

        /**
         * update the informations of the object
         * @param obj the instance we want to update in the data base
         */
        T update(final T obj);

        /**
         * Create an object in the data base
         * @param obj we want to add to the data base
         */
        T add(final T obj);

        /**
         * Return the id of an object in function of a name
         * @param obj of the object in the data base
         * @return the id of the object
         */
        long getIdByObj(final T obj);

        /**
         * Use to know if the object exist or not in the data base
         * @param name of the object we want to know if he exist
         * @return a boolean who determine if the object exist or not
         */
        T isExist(final String name);

        /**
         * Use to close the connection.
         * @param conn connection to close
         * @param stmt prepared statement to save
         * @throws DaoException in case of failure
         */
        void close(Connection conn, PreparedStatement stmt);
}
