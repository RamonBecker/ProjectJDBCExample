package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.sound.midi.SysexMessage;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	private static List<Seller> list;

	public static void main(String[] args) {
		SellerDao sellerDao = DaoFactory.createSellerDao();

//		System.out.println("=== Teste 1: Seller findById ===");
//		Seller seller = sellerDao.findById(3);

		// System.out.println(seller);

//		System.out.println("\n=== Teste 2: Seller findById ===");
//
//		Department department = new Department(2, null);
//		List<Seller> list = sellerDao.findByDeparment(department);
//		for (Seller obj : list) {
//			System.out.println(obj);
//		}

//		System.out.println("\n=== Teste 3: Seller findAll ===");

//		list = sellerDao.findAll();
//		for (Seller obj : list) {
//			System.out.println(obj);
//		}
//		
//		System.out.println("\n=== Teste 4: Seller INSERT ===");
//
//		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, new Department(2, null));
//		
//		sellerDao.insert(newSeller);
//		
//		System.out.println("Inserted! New id = "+newSeller.getId());

//		System.out.println("\n=== Teste 5: Seller UPDATE ===");
//
//		Seller seller = new Seller(1, "Greg", "greg@gmail.com", new Date(), 4000.0, new Department(2, null));
//		
//		seller.setName("Marta Waine");
//
//		sellerDao.update(seller);
//		
//		System.out.println("Update completed");
		
		System.out.println("\n=== Teste 6: Seller DELETE ===");
		
		System.out.println("Enter id for delete test: ");
		
		int id = new Scanner(System.in).nextInt();
		
		sellerDao.deleteById(id);
		System.out.println("Delete completed");
	}

}
