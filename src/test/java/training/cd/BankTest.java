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
  private Bank bank;

  @Before
  public void setUp() throws Exception {
    database = new TestDatabase();
    bank = new Bank(database, logger);
  }

  @Test(expected = RuntimeException.class)
  public void shouldNotCreateAccountWhenPersonIsNull() throws Exception {
    bank.createAccount(null);
  }

  @Test
  public void shouldCreateAccount() throws Exception {
    Person person = new Person(1, "Some User");
    Account account = bank.createAccount(person);

    assertThat(account.owner.id, is(1L));
  }
}