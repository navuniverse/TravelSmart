package com.travelportal.dao;

import java.util.List;

import com.travelportal.exception.DaoException;

/**
 * 
 * DAO interface to fetch Source and Destination dynamically
 * 
 * @author naveen.kumar
 * 
 */
public interface IRouteDao {

    String FROM_CITY = "fromCity";
    String DESTINATION_VIA_QUERY = "Select distinct(via) from  RouteModel where via is not null and source=:fromCity";
    String DESTINATION_DIRECT_QUERY = "Select distinct(destination) from  RouteModel where source=:fromCity";
    String SOURCE_STOP_QUERY = "Select distinct(via) from  RouteModel where via is not null";
    String SOURCE_DIRECT_QUERY = "Select distinct(source) from  RouteModel";
    String UNCHECKED = "unchecked";

    /**
     * 
     * DAO Method to fetch list of source cities dynamically from DB
     * 
     * @return list of source cities
     * @throws DaoException
     */
    List<String> getSourceCities() throws DaoException;

    /**
     * 
     * DAO Method to fetch list of destination cities dynamically from DB
     * corresponding to source city
     * 
     * @param fromCity
     *            {@link String}
     * @return list of destination cities corresponding to source city
     * @throws DaoException
     */
    List<String> getDestinationCities(String fromCity) throws DaoException;

}