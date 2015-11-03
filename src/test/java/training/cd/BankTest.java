package training.cd;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BankTest {
  private Bank bank;

  @Before
  public void setUp() throws Exception {
    bank = new Bank();
  }

  @Test(expected = RuntimeException.class)
  public void shouldNotCreateAccountWhenPersonIsNull() throws Exception {
    bank.createAccount(null);
  }
}