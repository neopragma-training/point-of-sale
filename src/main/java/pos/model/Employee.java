package pos.model;

import static pos.common.Utils.databaseHost;
import static pos.common.Utils.databaseName;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * Encapsulates information about an employee.
 * 
 * @author neopragma
 */
public class Employee extends BasicDBObject{
	
	private static final long serialVersionUID = 585718079673038770L;
	private static final String EMPLOYEES = "employees";
	private static final String ID = "_id";
	private static final String EMPLOYEE_ID = "employee_id";
	private static final String FIRST_NAME = "first_name";
	private static final String LAST_NAME = "last_name";
	private static final String POSITION = "position";
	private static final String DATE_OF_BIRTH = "date_of_birth";
	private static final String HIRE_DATE = "hire_date";
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	ObjectId id;
	String employeeId;
	String firstName;
	String lastName;
	String position;
	Date dateOfBirth;
	Date hireDate;;
	
	public Employee(
			ObjectId id, 
			String employeeId,
			String firstName, 
			String lastName, 
			String position, 
			String dateOfBirth, 
			String hireDate) throws ParseException {
		this.id = id;
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.dateOfBirth = dateFormat.parse(dateOfBirth);
		this.hireDate = dateFormat.parse(hireDate);
	}
	
	public static Employee newEmployee(
			String employeeId,
			String firstName, 
			String lastName, 
			String position, 
			String dateOfBirth, 
			String hireDate			
			) throws UnknownHostException, DuplicateEmployeeException, ParseException {
		Employee employee = new Employee(null, employeeId, firstName, lastName, position, dateOfBirth, hireDate);
		if (exists(employeeId)) {
			throw new DuplicateEmployeeException();
		}
		employee.put(EMPLOYEE_ID, employeeId);
		employee.put(FIRST_NAME, firstName);
		employee.put(LAST_NAME, lastName);
		employee.put(POSITION, position);
		employee.put(DATE_OF_BIRTH, dateFormat.parse(dateOfBirth));
		employee.put(HIRE_DATE, dateFormat.parse(hireDate));
		collection().insert(employee);
		return employee;
	}
	
	public ObjectId getId() {
		return id;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPosition() {
		return position;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public Date getHireDate() {
		return hireDate;
	}
	
	public static boolean exists(String employeeId) throws UnknownHostException {
		DBObject searchBy = new BasicDBObject();
		searchBy.put(EMPLOYEE_ID, employeeId);
		DBObject returnFields = new BasicDBObject();
		returnFields.put(ID, 1 );
		return collection().findOne(searchBy, returnFields) != null;
	}
	
	public static List<Employee> findAll() throws UnknownHostException, ParseException {
		List<Employee> employees = new ArrayList<Employee>();
		DBCollection documents = collection();
		DBCursor cursor = documents.find();
		for (Iterator<DBObject> iter = cursor.iterator() ; iter.hasNext() ; ) {
			DBObject dbObject = iter.next();
			Employee employee = new Employee(
				(ObjectId) dbObject.get(ID),
				(String) dbObject.get(EMPLOYEE_ID),
				(String) dbObject.get(FIRST_NAME),
				(String) dbObject.get(LAST_NAME),
				(String) dbObject.get(POSITION),
				(String) dbObject.get(DATE_OF_BIRTH),
				(String) dbObject.get(HIRE_DATE));
			employees.add(employee);
		}
		return employees;
	}

	private static DBCollection collection() throws UnknownHostException {
		MongoClient mongo = new MongoClient(databaseHost());
		DB db = mongo.getDB(databaseName());
		DBCollection collection = db.getCollection(EMPLOYEES);
		return collection;
	}

}
