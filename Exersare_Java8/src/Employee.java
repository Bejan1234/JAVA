import java.util.StringJoiner;

public class Employee extends Worker {

    public long employeeId;
    public String hireDate;

    private static int employeeeNo = 1;

    public Employee(String name, String birthDate, String hireDate) {
        super(name, birthDate);
        this.employeeId = Employee.employeeeNo++;
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                .add("employeeId=" + employeeId)
                .add("hireDate='" + hireDate + "'")
                + super.toString();
    }
}
