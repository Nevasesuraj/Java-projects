

public class OracleDb {
	private String user;
	private String pass;
	private Oracle[] DB = new Oracle[10];
	private boolean checkLogin;
	private int size;
	
	public OracleDb() {
		
	}
	
	public OracleDb(String user,String pass) {
		this.user = user;
		this.pass = pass;
		System.out.println("Db created");
	}
	//====================================================================
//method to login into the database
	public boolean login(String user,String pass) {
		if(this.user == user && this.pass == pass) {
			checkLogin = true;
			System.out.println("login sucessful");
			return true;
		}
		System.out.println("invalid Credentials");
		return false;
	}
	//=============================================================================
	//method to addthe employee in the database
	public boolean addEmployee(int empNo,
	String Ename,String Job,int MGR,String hireDate,double sal,Integer comm,int deptno) {
		if(checkLogin) {
			if(size<DB.length*0.75) {
				DB[size] = new Oracle(empNo,Ename,Job,MGR,hireDate,sal,comm,deptno);
				size++;
				return true;
			}
			else {
				Oracle[] temp = new Oracle[DB.length *2];
				for(int i=0; i<size; i++) {
					temp[i] = DB[i];
				}
				DB = temp;
				DB[size] = new Oracle(empNo,Ename,Job,MGR,hireDate,sal,comm,deptno);
				size++;
				return true;
			}
		}
		
		System.out.println(" login please");
		return false;
	}
	// ================================================================================
	
	//method to update the database
	public boolean update(int empNo,String[] fields,Object[] newValues) {
		if(!checkLogin) {
			System.out.println("invalid credentials please login");
			return false;
		}
		Oracle oracle = null;
		for(int i=0; i<size; i++) {
			if(DB[i].empNo == empNo) {
				oracle = DB[i];
				break;
			}
		}
		if(oracle == null) {
			System.out.println("Employee with roll no "+ empNo + " is not found in database");
			return false;
			
		}
		if(fields.length!= newValues.length) {
			   System.out.println("Fields and values count mismatch");
		        return false;
		}
		for(int i=0; i<fields.length; i++) {
			switch (fields[i].toLowerCase()) {
		    case "empno": {
		        oracle.empNo = (Integer) newValues[i];
		        break;
		    }
		    case "ename": {
		        oracle.Ename = (String) newValues[i];
		        break;
		    }
		    case "job": {
		        oracle.Job = (String) newValues[i];
		        break;
		    }
		    case "mgr": {
		        oracle.MGR = (Integer) newValues[i];
		        break;
		    }
		    case "hiredate": {
		        oracle.hireDate = (String) newValues[i];
		        break;
		    }
		    case "sal": {
		        oracle.sal = (Double) newValues[i];
		        break;
		    }
		    case "comm": {
		        oracle.comm = (Integer) newValues[i];
		        break;
		    }
		    case "deptno": {
		        oracle.deptno = (Integer) newValues[i];
		        break;
		    }
		    default:
		        throw new IllegalArgumentException("Unexpected field: " + fields[i]);
		}

		}
		return true;

		
	}
	// ============================================================================================
	
	// method to remove the employee from the database
	public boolean remove(String field, Object value) {
		if(!checkLogin) {
			System.out.println("invalid credentials ");
			return false;
		}
		
		for(int i=0; i<size; i++) {
			boolean match = false;
			switch (field.toLowerCase()) {
			case "ename": {
				
				match = DB[i].Ename.equalsIgnoreCase(value.toString());
				break;
			}
			case "empno" : {
				match = DB[i].empNo== (int) value;
				break;
			}
			case "job": {
				
				match = DB[i].Job.equalsIgnoreCase(value.toString());
				break;
			}
			
			case "mgr": {
				
				match = DB[i].MGR==(int) value;
				break;
			}
			
			case "hiredate":{
				match = DB[i].hireDate.equalsIgnoreCase(value.toString());
				break;
			}
			case "sal" :{
				match = DB[i].sal == (double)value;
				break;
			}
			
			case "deptno":{
				match = DB[i].deptno == (int) value;
				break;
			}
			case "comm" :{
				
				 match = (DB[i].comm == null && value == null) ||
	                        (DB[i].comm != null && DB[i].comm.equals((Integer) value));
	                break;
			}
			
			default:
			{
				throw new IllegalArgumentException("Unexpected value: " + field.toLowerCase());
			}
			
		}
			
			if(match) {
				 for (int j = i; j < size - 1; j++) {
		                DB[j] = DB[j + 1];
		            }
		            DB[size - 1] = null; 
		            size--;

		            System.out.println(field + " = " + value + " removed successfully.");
		            return true;
		        }
		    }

		    System.out.println("Record not found for " + field + " = " + value);
		    return false;
			}
	

	
	// ==============================================================================================
	// method to find the employee by empno
	
	public Oracle selectByEmpNo(int empNo) {
		if(checkLogin) {
			for(int i =0; i<size; i++) {
				if(DB[i].empNo == empNo) {
					return DB[i];
				}
			}
			
		}
		System.out.println("Invalid credentials");
		return null;
	}
	//====================================================================================================================
	//method to print employee using where clause
	public void selectColumnsWhere(String[] selectFields,String whereField,Object whereValue) {
		if (!checkLogin) {
	        System.out.println("invalid credentials ");
	        return;
	    }
		
		for(int i=0; i<size; i++) {
			Oracle emp = DB[i];
			
			if(isMatch(emp, whereField, whereValue)) {
				for(int j=0; j<selectFields.length; j++) {
					printField(emp, selectFields[j]);
					System.out.print(" ");
				}
				System.out.println();
			}
			
		}
	}
	
	//=====================================================================================================
	// method to print the employee by deptno
	public void selectByDept(int deptNo) {
		if (!checkLogin) {
	        System.out.println("invalid credentials ");
	        return;
	    }
		boolean found = false;
		for(int i=0; i<size; i++) {
			Oracle emp = DB[i];
			if(emp.deptno == deptNo) {
				System.out.println(DB[i]);
				found = true;
			}
			
		}
		if (!found) {
	        System.out.println("No employees found with this deptno " + deptNo);
	    }
		
	}
	//=====================================================================================
	// mehtod to print the employee using job
	public void selectByJob(String job) {
		if (!checkLogin) {
	        System.out.println("invalid credentials ");
	        return;
	    }
		boolean found = false;
		for(int i =0; i<size; i++) {
			Oracle emp = DB[i];
			if(emp.Job == job) {
				System.out.println(DB[i]);
				found = true;
			}
		}
		if (!found) {
	        System.out.println("No employees found with this job " + job);
	    }
		
		
	}
	//==================================================================================
	// method to print the salary greater than given salary
	public void selectSalaryGreaterThan(double sal) {
		if (!checkLogin) {
	        System.out.println("invalid credentials ");
	        return;
	    }
		if (size == 0) {
	        System.out.println("No employees in the database");
	        
	    }
		boolean found = false;
		for(int i =0; i<size; i++) {
			Oracle emp = DB[i];
			if(emp.sal > sal) {
				System.out.println(DB[i]);
				found = true;
			}
		}
		if (!found) {
	        System.out.println("No employees found with this salary greater than " + sal);
	    }
		
	}
	//===================================================================================
		// method to print the salary less than given salary
	public void selectSalaryLessThan(double sal) {
		if (!checkLogin) {
	        System.out.println("invalid credentials ");
	        return;
	    }
		if (size == 0) {
	        System.out.println("No employees in the database");
	        
	    }
		boolean found = false;
		for(int i =0; i<size; i++) {
			Oracle emp = DB[i];
			if(emp.sal < sal) {
				System.out.println(DB[i]);
				found = true;
			}
		}
		if (!found) {
	        System.out.println("No employees found with this salary less than " + sal);
	    }
		
	}
	//===================================================================================
	//method to print the max salary in database
	public double getMaxSalary() {
		if (!checkLogin) {
	        System.out.println("invalid credentials ");
	        return -1;
	    }
		if (size == 0) {
	        System.out.println("No employees in the database");
	        return 0;
	    }
		double max = Double.MIN_NORMAL;
		for(int i=0; i<size; i++) {
			Oracle emp = DB[i];
		
				if(emp.sal > max) {
					max = DB[i].sal;
				}
			
		}
		return max;
	}
	//===================================================================================
	//method to print the min salary from database
	public double getMinSalary() {
		if (!checkLogin) {
	        System.out.println("invalid credentials ");
	        return -1;
	    }
		if (size == 0) {
	        System.out.println("No employees in the database");
	        return 0;
	    }
		double min = Double.MAX_VALUE;
		for(int i=0; i<size; i++) {
			Oracle emp = DB[i];
		
				if(emp.sal < min) {
					min = DB[i].sal;
				}
			
		}
		return min;
	}
	//=========================================================================================
	//method to print the avg salary
	public double getAvgSalary() {
		if (!checkLogin) {
	        System.out.println("invalid credentials ");
	        return -1;
	    }
		if (size == 0) {
	        System.out.println("No employees in the database");
	        return 0;
	    }
		double avg = 0;
		for(int i=0; i<size; i++) {
			Oracle emp = DB[i];
			avg = avg +DB[i].sal; 
		}
		
		return avg/size;
	}
	//==================================================================================
	// method to print the avg salary 
	public double getTotalSal() {
		if (!checkLogin) {
	        System.out.println("invalid credentials ");
	        return -1;
	    }
		if (size == 0) {
	        System.out.println("No employees in the database");
	        return 0;
	    }
		double total = 0;
		for(int i=0; i<size; i++) {
			Oracle emp = DB[i];
			total = total +DB[i].sal;
		}
		return total;
		
	}
	//===========================================================================
	//method to return total no of employees in the database
	
	public int count() {
		return size;
	}
	//===========================================================================
	//method to print database
	public void PrintDB() {
		if(checkLogin) {
			for(int i=0; i<size; i++) {
				System.out.println(DB[i]);
			}
			
		}
		else {
			System.out.println("please login");
		}
	}
	
	//==========================================================================================
	// helper methods  of main method
	
	private boolean isMatch(Oracle emp,String field,Object value) {
		switch (field.toLowerCase()){
		case "empno": {
			
			return emp.empNo == ((Number) value).intValue();

		
		}
		case "ename":{
			return emp.Ename.equalsIgnoreCase(value.toString());
		}
		case "job":{
			return emp.Job.equalsIgnoreCase(value.toString());
		}
		case "mgr":{
			 return emp.MGR == ((Number) value).intValue();
		}
		case "hiredate" :{
			return emp.hireDate.equalsIgnoreCase(value.toString());
		}
		case "sal":{
			 return emp.sal == ((Number) value).doubleValue();
		}
		case "comm":{
			 return (emp.comm == null && value == null) || 
	                   (emp.comm != null && emp.comm.equals((Integer) value));
		}
		case "deptno":{
			return emp.deptno == ((Number) value).intValue();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + field.toLowerCase());
			
		}
	}
	
	
	private void printField(Oracle emp,String fiedl) {
		switch (fiedl.toLowerCase()){
		case "empno": {
			System.out.println(emp.empNo);
			break;
		}
		case "ename":{
			System.out.println(emp.Ename);
			break;
		}
		case "job":{
			System.out.println(emp.Job);
			break;
		}
		case "mgr":{
			System.out.println(emp.MGR);
			break;
		}
		case "hiredate":{
			System.out.println(emp.hireDate);
			break;
		}
		case "sal":{
			System.out.println(emp.sal);
			break;
		}
		case "comm":{
			System.out.println(emp.comm);
			break;
		}
		case "deptno":{
			System.out.println(emp.deptno);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + fiedl.toLowerCase());
		}
	
	}
}
