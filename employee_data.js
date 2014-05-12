// Load base set of test data for employees
// Point of Sale system
// 08 May 2014, version 1.0
//
// usage:
// mongo employee_data.js

conn = new Mongo()
db = conn.getDB("pos_test")
db.employees.drop()
db.employees.insert({ "employee_id" : "302", "last_name" : "Pankow", "first_name" : "Dan", "position" : "cashier", "date_of_birth" : "1998-08-14", "hire_date" : "2014-03-15" })
db.employees.insert({ "employee_id" : "119", "last_name" : "Preston", "first_name" : "Bonnie", "position" : "cashier", "date_of_birth" : "1992-04-26", "hire_date" : "2012-08-05" })
db.employees.insert({ "employee_id" : "160", "last_name" : "Esposito", "first_name" : "Vicente", "position" : "stocker", "date_of_birth" : "2000-12-02", "hire_date" : "2013-11-18" })
db.employees.insert({ "employee_id" : "200", "last_name" : "Austin", "first_name" : "Chantal", "position" : "stocker", "date_of_birth" : "1978-02-20", "hire_date" : "2013-09-30" })
db.employees.insert({ "employee_id" : "030", "last_name" : "Davis", "first_name" : "Marilyn", "position" : "manager", "date_of_birth" : "1980-05-17", "hire_date" : "2014-01-16" })



