package com.tecnositaf.centrobackend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tecnositaf.centrobackend.model.User;

@Mapper
public interface MapperInterface {

	@Insert("INSERT INTO User (username, email, password) VALUES(#{username}, #{email}, #{password})")
	@Options(useGeneratedKeys = true, keyProperty = "idUser", keyColumn = "idUser")
	public void insertUser(User user);

	@Select("SELECT * FROM User WHERE idUser = #{idUser}")
	public User getUserById(@PathVariable("idUser") Integer idUser);

	@Select("SELECT * FROM User")
	public List<User> getAllUsers();

	@Update("UPDATE User SET username = #{username}, email = #{email}, password = #{password} WHERE idUser = #{idUser}")
	public void updateUser(@RequestBody User updateUser);

	@Delete("DELETE FROM User WHERE idUser = #{idUser}")
	public void deleteUser(@PathVariable("idUser") Integer idUser);

	@Select("SELECT COUNT(*) FROM User WHERE idUser = #{idUser} LIMIT 1")
	public long existById(Integer idUser);
}
