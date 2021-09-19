package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	private static DepartmentDao departmentDao = DaoFactory.createDeparmentDao();
	private static Department department = null;

	public static void main(String[] args) {
//		insert();
		
//		findByID();
		
//		findAll();
		
//		update();
		
		delete();
	}

	private static void insert() {
		System.out.println("=== Teste 1: Department INSERT ===");
		
		department = new Department();
		department.setName("Financial");
		
		departmentDao.insert(department);

		System.out.println("Inserted! New id = " + department.getId());
	}

	private static void findByID() {
		System.out.println("=== Test 2: Department FindByID ===");
		
		department = departmentDao.findById(1);
		
		if(department != null) {
			System.out.println("Deparment found: "+department);
		}else {
			System.out.println("Not found deparment!");
		}
	}
	
	private static void findAll() {
		
		System.out.println("=== Test 3 Department FindByAll");
		List<Department> departments = departmentDao.findAll();
		
		if(departments != null) {
			for (Department department : departments) {
				System.out.println(department);
			}
		}else {
			System.out.println("Not found departments!");
		}
	}

	private static void update() {
		System.out.println("=== Test 4 Department UPDATE ===");
		Department department = new Department(1, "Games");
		departmentDao.update(department);
		System.out.println("Update completed!");
	}
	
	private static void delete() {
		System.out.println("=== Test 4 Department DELETE ===");
		
		departmentDao.deleteById(2);
		
		System.out.println("Delete completed!");
	}
}
