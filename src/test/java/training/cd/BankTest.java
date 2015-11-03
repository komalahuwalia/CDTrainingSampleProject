package training.cd;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import training.cd.infrastructure.Logger;
import training.cd.model.Account;
import training.cd.model.Person;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class BankTest {
  @Mock
  private Logger logger;

  private TestDatabase database;
  private Person personOne;
  private Bank bank;

  @Before
  public void setUp() throws Exception {
    database = new TestDatabase();
    bank = new Bank(database, logger);
    personOne = new Person(1, "Some User");
  }

  @Test(expected = RuntimeException.class)
  public void shouldNotCreateAccountWhenPersonIsNull() throws Exception {
    bank.createAccount(null);
  }

  @Test
  public void shouldCreateAccount() throws Exception {
    Account account = bank.createAccount(personOne);

    assertThat(account.owner.id, is(1L));
    assertThat(account.balance, is(0.0));
  }

  @Test
  public void shouldFetchAccountFromId() throws Exception {
    Account account = bank.createAccount(personOne);
    Account savedAccount = bank.account(account.id);

    assertThat(savedAccount.id, is(account.id));
  }
}