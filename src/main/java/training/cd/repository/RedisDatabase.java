package training.cd.repository;

import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;
import training.cd.model.Account;

import java.io.IOException;

public class RedisDatabase implements Database {
  public static final String MAX_ACCOUNT_ID = "maxAccountId";
  public static final String ACCOUNT = "account";

  private final Jedis jedis;

  public RedisDatabase() {
    jedis = new Jedis();
  }

  @Override
  public void create(Account account) {
    account.id = jedis.incr(MAX_ACCOUNT_ID);
    update(account);
  }

  @Override
  public void update(Account account) {
    String accountJson;

    try {
      accountJson = new ObjectMapper().writeValueAsString(account);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    jedis.set(key(account.id), accountJson);
  }

  @Override
  public Account account(long id) {
    String accountJson = jedis.get(key(id));
    Account account;
    try {
      account = new ObjectMapper().readValue(accountJson, Account.class);
    } catch (IOException e) {
      throw new RuntimeException(e);

    }
    return account;
  }

  private String key(long accountId) {
    return ACCOUNT + "." + accountId;
  }
}
