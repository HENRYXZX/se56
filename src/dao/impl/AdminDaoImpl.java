package dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.AdminDao;
import domain.Admin;

public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	private AdminDao admin;
	@Override
	public List<Admin> findAll() {
		
		return admin.findAll();
	}
	@Override
	public Admin findById(int id) {
		// TODO Auto-generated method stub
		return admin.findById(id);
	}

}
