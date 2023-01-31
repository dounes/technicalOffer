package com.fr.technicaltestoffer.usermanagement.repository;

import com.fr.technicaltestoffer.usermanagement.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * myBatis ORM repository
 * */
@Mapper
public interface IUserRepository {
    @Select("SELECT * FROM USERAF WHERE NAME = #{name} AND BIRTHDATE = #{birthDate}")
    User getUser(String name, String birthDate);

    @Insert("INSERT INTO USERAF (NAME, COUNTRYOFRESIDENCE, BIRTHDATE, PHONENUMBER, GENDER) VALUES (#{name}, #{countryOfResidence}, #{birthDate}, #{phoneNumber}, #{gender})")
    boolean registerUser(User user);
}
