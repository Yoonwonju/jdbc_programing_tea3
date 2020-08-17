package jdbc_programing_tea3.dto;

public class Employee {
    private int no;
    private String name;
    private Title tno;
    private Employee manager;
    private int salary;
    private Department dno;

    public Employee() {
        // TODO Auto-generated constructor stub
    }

    public Employee(int no) {
        this.no = no;
    }

    public Employee(int no, String name, Title tno, Employee manager, int salary, Department dno) {
        this.no = no;
        this.name = name;
        this.tno = tno;
        this.manager = manager;
        this.salary = salary;
        this.dno = dno;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Title getTno() {
        return tno;
    }

    public void setTno(Title tno) {
        this.tno = tno;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Department getDno() {
        return dno;
    }

    public void setDno(Department dno) {
        this.dno = dno;
    }

    @Override
    public String toString() {
        return String.format("Employee [no=%s, name=%s, tno=%s, manager=%s, salary=%s, dno=%s]", no, name, tno, manager,
                salary, dno);
    }

}
