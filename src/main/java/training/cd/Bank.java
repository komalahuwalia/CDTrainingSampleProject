package training.cd;

import training.cd.model.Account;
import training.cd.model.Person;
import training.cd.repository.Database;
import training.cd.repository.RedisDatabase;

public class Bank {
  public static final String MAX_ID = "maxId";

  public Account createAccount(Person person) {
    if (person == null) throw new RuntimeException("Person cannot be null");

    Database database = new RedisDatabase();

    Account account = new Account(0.0, person);
    database.create(account);

    return account;
  }

  public Account account(long id) {
    return new RedisDatabase().account(id);
  }

  public void deposit(double amount, Account account) {
    account.balance += amount;
    new RedisDatabase().update(account);
  }

  public void withdraw(double amount, Account account) {
    account.balance -= amount;
    new RedisDatabase().update(account);
  }

  public void transfer(double amount, Account from, Account to) {

  }
}
