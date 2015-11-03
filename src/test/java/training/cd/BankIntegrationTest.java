package training.cd;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import training.cd.model.Account;
import training.cd.model.Person;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BankIntegrationTest {
  private Bank bank;
  private Person personOne;

  @Before
  public void setUp() throws Exception {
    flushDatabase();
    bank = new Bank();
    personOne = new Person(1, "First User");
  }

  @Test
  public void shouldBeAbleToCreateAccountAndAddBalance() throws Exception {
    //Given
    Account account = bank.createAccount(personOne);
    bank.deposit(100.0, account);

    //When
    Account savedAccount = bank.account(account.id);

    //Then
    assertThat(savedAccount.owner.id, is(personOne.id));
    assertThat(savedAccount.id, is(1L));
    assertThat(savedAccount.balance, is(100.0));
  }

  private String flushDatabase() {
    return new Jedis().flushAll();
  }
}