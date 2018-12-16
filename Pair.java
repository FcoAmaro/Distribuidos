public class Pair{

  private final int id;
  private final String procedimiento;

  public Pair(int left, String right) {
    this.id = left;
    this.procedimiento = right;
  }

  public int getId() { return id; }
  public String getProcedimiento() { return procedimiento; }



}