package jdbc_programing_tea3.dto;

import java.util.List;

public class Title {
    private int no;
    private String name;
    private List<Employee> list;

    public Title() {
        // TODO Auto-generated constructor stub
    }

    public Title(int no) {
        this.no = no;
    }

    public Title(int no, String name) {
        this.no = no;
        this.name = name;
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

    public List<Employee> getList() {
        return list;
    }

    public void setList(List<Employee> list) {
        this.list = list;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + no;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Title other = (Title) obj;
        if (no != other.no)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Title [%s, %s]", no, name);
    }

}
