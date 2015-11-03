package training.cd;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import redis.clients.jedis.Jedis;
import training.cd.model.Account;
import training.cd.model.Person;
import training.cd.type.IntegrationTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Category(IntegrationTest.class)
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
  public void shouldBeAbleToCreateAccountDepositAndWithdraw() throws Exception {
    //Given
    Account account = bank.createAccount(personOne);

    //When
    bank.deposit(100.0, account);
    bank.withdraw(30.0, account);

    //Then
    Account savedAccount = bank.account(account.id);
    assertThat(savedAccount.owner.id, is(personOne.id));
    assertThat(savedAccount.id, is(1L));
    assertThat(savedAccount.balance, is(70.0));
  }

  private String flushDatabase() {
    return new Jedis().flushAll();
  }
}