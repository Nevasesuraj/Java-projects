
public class Oracle {
	public int empNo;
	String Ename;
	String Job;
	int MGR;
	String hireDate;
	double sal;
	Integer comm;
	int deptno;
	
	
	public Oracle() {
		
	}
	public Oracle(int empNO,String Ename,String Job,int MGR,String hireDate,double sal,Integer comm,int deptno) {
		this.empNo = empNO;
		this.Ename = Ename;
		this.Job = Job;
		 this.MGR = MGR;
		 this.hireDate = hireDate;
		 this.sal = sal;
		 this.comm = comm;
		 this.deptno = deptno;
	}
	
	public String toString() {
		return "[ EmpNo :- " + empNo + " ,Ename:- " + Ename +" ,Job:- "+Job + " ,Manager:- " 
				+ MGR +" ,hireDate:- "+ hireDate + " ,Salary:- " + sal +" ,Commision:- " +comm +
				" ,DepartmentNo:- " + deptno  + " ]" ;
		
	}
	
	public void printDB() {
		System.out.println("Employee No :-" + empNo);
		System.out.println("Employee Name :-" +Ename);
		System.out.println("Job:-" + Job);
		System.out.println("Manager Name :-" +MGR);
		System.out.println("Employee HireDate :-" + hireDate);
		System.out.println("Employee Salary :-" + sal);
		 System.out.println("Employee Commision :-" + (comm == null ? "" : comm));
		System.out.println("Employee Department No :-" + deptno);
	}

}
