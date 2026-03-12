import java.time.LocalDate;
import java.time.Period;
import java.util.StringJoiner;

public class Worker {
  private String name;
  private String birthDate;
  protected String endDate;


  public Worker(){

  }

    public Worker(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getAge(){
      int currentYear = 2026;
      int birthYear = Integer.parseInt(birthDate.substring(6));
      int age = currentYear - birthYear;
      return age;
    }

    public double collectPay(){
      return 0.0;
    }

    public void terminate(String endDate){
      this.endDate = endDate;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Worker.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("birthDate='" + birthDate + "'")
                .add("endDate='" + endDate + "'")
                .toString();
    }
}
