package training.cd.repository;

import training.cd.model.Account;

public interface Database {
  void create(Account account);
  void update(Account account);

  Account account(long id);
}
