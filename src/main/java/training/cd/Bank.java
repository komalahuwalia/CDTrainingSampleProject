package training.cd;

import training.cd.infrastructure.Logger;
import training.cd.model.Account;
import training.cd.model.Person;
import training.cd.repository.Database;
import training.cd.repository.RedisDatabase;

public class Bank {

  public Account createAccount(Person person) {
    if (person == null) throw new RuntimeException("Person cannot be null");

    Database database = new RedisDatabase();

    Account account = new Account(0.0, person);
    database.create(account);

    new Logger().debug(String.format("account created with id: %d", account.id));

    return account;
  }

  public Account account(long id) {
    return new RedisDatabase().account(id);
  }

  public void deposit(double amount, Account account) {
    account.balance += amount;

    new Logger().debug(String.format("deposited %s to %d", amount, account.id));

    new RedisDatabase().update(account);
  }

  public boolean withdraw(double amount, Account account) {
    if (account.balance < amount) return false;

    account.balance -= amount;
    new RedisDatabase().update(account);

    new Logger().debug(String.format("withdrew %s from %d", amount, account.id));

    return true;
  }

  public boolean transfer(double amount, Account from, Account to) {
    boolean status = withdraw(amount, from);
    if (!status) return false;

    try {
      deposit(amount, to);
    } catch (Exception e) {
      deposit(amount, from);
      return false;
    }

    new Logger().debug(String.format("transferred %s from %d to %d", amount, from.id, to.id));

    return true;
  }
}
