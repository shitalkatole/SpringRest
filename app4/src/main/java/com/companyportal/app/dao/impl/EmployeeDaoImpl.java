package com.companyportal.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.companyportal.app.dao.EmployeeDao;
import com.companyportal.app.entity.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveEmployeeData(Employee employee) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(employee);

		session.getTransaction().commit();

		session.close();
	}

	@Override
	public List<Employee> getEmployeesData() {

		List<Employee> empList = new ArrayList<Employee>();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		empList = session.createQuery("From Employee").list();

		session.getTransaction().commit();

		session.close();

		return empList;

	}

	@Override
	public void deleteEmployee(int employeeId) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Employee empl = new Employee();
		empl.setEmployeeId(employeeId);
		session.delete(empl);

		session.getTransaction().commit();

		session.close();

	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String queryString = "UPDATE Employee SET  name= :name, project=:project,"
				+ "mailId=:mailId, phoneNo=:phoneNo WHERE employeeId= :employeeId";

		Query query = session.createQuery(queryString);
		query.setParameter("name", employee.getName());
		query.setParameter("project", employee.getProject());
		query.setParameter("mailId", employee.getMailId());
		query.setParameter("phoneNo", employee.getPhoneNo());
		query.setParameter("employeeId", employee.getEmployeeId());

		query.executeUpdate();

		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Employee> getEmployeesByName(String name) {
		// TODO Auto-generated method stub
		List<Employee> employeeList = new ArrayList<Employee>();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String queryString = "from Employee where name like: name";

		Query query = session.createQuery(queryString);
		query.setParameter("name", "%" + name + "%");
		employeeList = query.list();
		session.getTransaction().commit();
		session.close();

		return employeeList;
	}
}