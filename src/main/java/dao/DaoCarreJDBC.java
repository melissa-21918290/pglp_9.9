package dao;

import java.sql.Connection;
import java.util.ArrayList;

import forme.Carre;
/**
 * 
 * @author Melissa
 *
 */
public class DaoCarreJDBC extends AbstractDao<Carre> {
	/**
	 * connexion a BDD
	 *
	 */
private final Connection connect;
/**
 * constructeur de la classe
 *
 * @param c connection pour la BDD
 */
public DaoCarreJDBC(final Connection c){
	connect =c;
}


	@Override
	public Carre create(Carre object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carre find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Carre> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carre update(Carre object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Carre object) {
		// TODO Auto-generated method stub
		
	}

	

}
