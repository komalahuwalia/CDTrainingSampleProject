package training.cd.model;

public class Account {
  public long id;
  public double balance;
  public Person owner;

  public Account() {
  }

  public Account(double balance, Person owner) {
    this.balance = balance;
    this.owner = owner;
  }
}
