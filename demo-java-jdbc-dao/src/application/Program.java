package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	private static List<Seller> list;
	
	public static void main(String[] args) {
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		
		System.out.println("=== Teste 1: Seller findById ===");
		//Seller seller = sellerDao.findById(3);
		
		//System.out.println(seller);
		
		System.out.println("\n=== Teste 2: Seller findById ===");
//
//		Department department = new Department(2, null);
//		List<Seller> list = sellerDao.findByDeparment(department);
//		for (Seller obj : list) {
//			System.out.println(obj);
//		}
		
		System.out.println("\n=== Teste 3: Seller findAll ===");
	
		list = sellerDao.findAll();
		for (Seller obj : list) {
			System.out.println(obj);
		}
	}

}
