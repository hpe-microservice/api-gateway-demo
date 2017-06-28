package hpe.demo.auth.service;

import hpe.demo.auth.entity.Auth;

public interface AuthDaoService {

	public Auth findById(int id);
	
	public Auth findByAppkey(int appkey);

	public Auth findByAppkeyAndUsername(int appkey, String username);

	public Auth save(Auth au);
}
