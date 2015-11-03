package training.cd;

import training.cd.model.Account;
import training.cd.repository.Database;

import java.util.HashMap;
import java.util.Map;

public class TestDatabase implements Database {
  private Map<Long, Account> accounts;
  private long maxId;

  public TestDatabase() {
    accounts = new HashMap<>();
    maxId = 0L;
  }

  @Override
  public void create(Account account) {
    account.id = ++maxId;
    update(account);
  }

  @Override
  public void update(Account account) {
    accounts.put(account.id, account);
  }

  @Override
  public Account account(long id) {
    return accounts.get(id);
  }
}
