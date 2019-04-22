package com.limra.jaipurilohar.dao;

import androidx.room.*;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Insert
    void insertIntoUserInfo (User user);

    @Insert
    void insertIntoUserList (List<User> userList);

    @Query("DELETE FROM user")
    public void deleteAll();

    @Query("SELECT * FROM user WHERE uid = :userId")
    User getUserById(int userId);

    @Query("SELECT * FROM user WHERE user_name LIKE :userName")
    User getUserByUserName(String userName);

    @Update
    void updateUser (User user);

    @Query("SELECT * FROM user WHERE gotra LIKE :gotra AND city LIKE :city AND (first_name LIKE :name OR last_name LIKE :name) ")
    List<User> getSearchedContacts( String gotra, String city, String name);

    @Query("SELECT * FROM user WHERE first_name LIKE :name OR last_name LIKE :name ")
    List<User> getSearchedContactsFromName(String name);

    @Query("SELECT * FROM user WHERE city LIKE :city")
    List<User> getSearchedContactsFromCity(String city);

    @Query("SELECT * FROM user WHERE gotra LIKE :gotra")
    List<User> getSearchedContactsFromGotra( String gotra);

    @Query("SELECT * FROM user WHERE gotra LIKE :gotra AND city LIKE :city")
    List<User> getSearchedContactsFromGotraCity( String gotra, String city);

    @Query("SELECT * FROM user WHERE gotra LIKE :gotra AND (first_name LIKE :name OR last_name LIKE :name) ")
    List<User> getSearchedContactsFromGotraName( String gotra, String name);

    @Query("SELECT * FROM user WHERE city LIKE :city AND (first_name LIKE :name OR last_name LIKE :name) ")
    List<User> getSearchedContactsFromNameCity(String city, String name);


}