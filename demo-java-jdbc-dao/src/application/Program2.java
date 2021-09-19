package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	private static DepartmentDao departmentDao = null;
	private static Department department = null;

	public static void main(String[] args) {
//		insert();
		
		findByID();
	}

	private static void insert() {
		System.out.println("=== Teste 1: Department INSERT ===");

		departmentDao = DaoFactory.createDeparmentDao();

		department = new Department();
		department.setName("Financial");
		
		departmentDao.insert(department);

		System.out.println("Inserted! New id = " + department.getId());
	}

	private static void findByID() {
		System.out.println("=== Test 2: Department FindByID ===");

		departmentDao = DaoFactory.createDeparmentDao();

		department = departmentDao.findById(1);
		
		if(department != null) {
			System.out.println("Deparment found: "+department);
		}else {
			System.out.println("Not found deparment!");
		}
	}
}
