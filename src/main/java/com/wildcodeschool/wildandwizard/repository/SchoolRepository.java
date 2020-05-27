package com.wildcodeschool.wildandwizard.repository;

import com.wildcodeschool.wildandwizard.entity.School;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;

public class SchoolRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/spring_jdbc_quest?serverTimezone=GMT";
    private final static String DB_USER = "h4rryp0tt3r";
    private final static String DB_PASSWORD = "Horcrux4life!";

    public List<School> findAll() {

        // TODO : find all schools
        try {
            Connection connection = DriverManager.getConnection(
            DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
            "SELECT * FROM school;"
            );

            ResultSet resultSet = statement.executeQuery();

            List<School> schools = new ArrayList<>();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                long capacity = resultSet.getLong("capacity");
                String country = resultSet.getString("country");

                schools.add(new School(id, name, capacity, country));
                }
            
            return schools;
        } catch (
            SQLException e) {
                e.printStackTrace();
            }
        return null;    
        
    }



    public School findById(Long id) {

        try {
            Connection connection = DriverManager.getConnection(
            DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM school WHERE id=?;"
            );
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();

            
            
            School school = new School();
                        
            while (resultSet.next()) {
                school.setId(resultSet.getLong("id"));
                school.setName(resultSet.getString("name"));
                school.setCapacity(resultSet.getLong("capacity"));
                school.setCountry(resultSet.getString("country"));
                
            }
              
            return school;
            
            
        } catch (
            SQLException e) {
                e.printStackTrace();
            }
        return null;  
    }

    public List<School> findByCountry(String country) {

        // TODO : search schools by country
        try {
            Connection connection = DriverManager.getConnection(
            DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
            "SELECT * FROM school WHERE country LIKE ?;"
            );
            statement.setString(1,country);
            ResultSet resultSet = statement.executeQuery();

            List<School> schools = new ArrayList<>();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                long capacity = resultSet.getLong("capacity");
                

                schools.add(new School(id, name, capacity, country));
                
                }
            
            return schools;
        } catch (
            SQLException e) {
                e.printStackTrace();
            }
        return null;  

    }
}
