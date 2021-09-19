package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;

public class Program2 {

	
	private static DepartmentDao departmentDao = null;
	private static Department department = null;
	
	public static void main(String[] args) {
		inserir();
	}
	
	private static void inserir() {
		System.out.println("=== Teste 1: Department INSERT ===");
		
		departmentDao = DaoFactory.createDeparmentDao();
		
		department = new Department();
		department.setName("Financial");
		departmentDao.insert(department);
		
		System.out.println("Inserted! New id = "+department.getId());
	}
}
