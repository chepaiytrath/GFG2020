package spacedrepetition;

public class Test {
    public static void main(String[] args) {
        Employee e1 = new Employee(1);
        Employee e3 = new Employee(3);
        Employee e2 = e1;

        e1 = e3;

        System.out.println(e2.id);
        System.out.println(e1.id);
    }

    static class Employee {
        int id;

        Employee(int id) {
            this.id = id;
        }
    }
}


