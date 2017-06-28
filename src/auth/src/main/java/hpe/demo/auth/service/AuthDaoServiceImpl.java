package hpe.demo.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hpe.demo.auth.dao.AuthDao;
import hpe.demo.auth.entity.Auth;

@Service("AuthDaoService")
public class AuthDaoServiceImpl implements AuthDaoService {
	@Autowired
	private AuthDao dao;

	@Override
	public Auth findByAppkeyAndUsername(int appkey, String username) {
		return dao.findByAppkeyAndUsername(appkey, username);
	}

	@Override
	public Auth findById(int id) {
		return dao.findById(id);
	}

	@Override
	public Auth findByAppkey(int appkey) {
		List<Auth> list = dao.findByAppkey(appkey);
		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	@Override
	public Auth save(Auth au) {
		return dao.save(au);
	}
}
