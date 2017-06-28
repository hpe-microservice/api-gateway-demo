package hpe.demo.auth.dao;

import hpe.demo.auth.entity.Auth;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AuthDao extends CrudRepository<Auth, Integer> {
	public Auth findById(int id);

	public List<Auth> findByAppkey(int appkey);

	public Auth findByAppkeyAndUsername(int appkey, String username);
}
