import java.io.ObjectInputStream.GetField;

public class OracleDbDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OracleDb OD = new OracleDb("Root","Root");
		OD.login("Root", "Root");
		OD.addEmployee(7369, "SMITH", "CLERK", 7902, "17-DEC-80", 800, null, 20);
		OD.addEmployee(7499, "ALLEN", "SALESMAN", 7698, "20-FEB-81", 1600, 300, 30);
		OD.addEmployee(7521, "WARD", "SALESMAN", 7698, "22-FEB-81", 1250, 500, 30);
		OD.addEmployee(7566, "JONES", "MANAGER", 7839, "02-APR-81", 2975, null, 20);
		OD.addEmployee(7654, "MARTIN", "SALESMAN", 7698, "28-SEP-81", 1250, 1400, 30);
		OD.addEmployee(7698, "BLAKE", "MANAGER", 7839, "01-MAY-81", 2850, null, 30);
		OD.addEmployee(7782, "CLARK", "MANAGER", 7839, "09-JUN-81", 2450, null, 10);
		OD.addEmployee(7788, "SCOTT", "ANALYST", 7566, "19-APR-87", 3000, null, 20);
		OD.addEmployee(7839, "KING", "PRESIDENT", 0, "17-NOV-81", 5000, null, 10); 
		OD.addEmployee(7844, "TURNER", "SALESMAN", 7698, "08-SEP-81", 1500, 0, 30);
		OD.addEmployee(7876, "ADAMS", "CLERK", 7788, "23-MAY-87", 1100, null, 20);
		OD.addEmployee(7900, "JAMES", "CLERK", 7698, "03-DEC-81", 950, null, 30);
		OD.addEmployee(7902, "FORD", "ANALYST", 7566, "03-DEC-81", 3000, null, 20);
		OD.addEmployee(7934, "MILLER", "CLERK", 7782, "23-JAN-82", 1300, null, 10);

		OD.PrintDB();
		System.out.println("============================================");
		
		
	
      OD.update(7499, 
              new String[]{"ename", "sal", "comm"}, 
              new Object[]{"lucifer", 2000.0, null});
      
      OD.PrintDB();
  	System.out.println("==========================================");
  	
  	
  	 OD.remove("empNo",7369);
  	 OD.PrintDB();
  	System.out.println("==========================================");
      
      
  	Oracle emp = OD.selectByEmpNo(7521); 
  	if (emp != null) {
      System.out.println("Employee found with employee no :- "+ emp.empNo +"  " + emp);
  	} else {
      System.out.println("Employee not found!");
  	}
  	System.out.println("==========================================");
	
	
	
	
	
	
	String[] select2 = {"empno","ename", "sal", "deptno"};
	OD.selectColumnsWhere(select2, "deptno", 30);
  	System.out.println("==========================================");

	
  	
  	OD.selectByDept(20);	
  	System.out.println("==========================================");

  	OD.selectByJob("MANAGER"); 
	System.out.println("==========================================");

	OD.selectSalaryGreaterThan(1900);
	System.out.println("==========================================");

	
	OD.selectSalaryLessThan(3000);
	System.out.println("==========================================");

	
	System.out.println(OD.getMaxSalary());
	System.out.println("===============================================");
   
	System.out.println(OD.getMinSalary());
	System.out.println("===============================================");
	
	System.out.println(OD.getAvgSalary());
	System.out.println("===============================================");
//		
	System.out.println(OD.getTotalSal());
	
	System.out.println("===============================================");
	
	
	System.out.println(OD.count());
	System.out.println("===============================================");
//		
		
			
	}

}
