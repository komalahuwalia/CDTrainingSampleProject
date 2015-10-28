package training.cd;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HelloTest {

  Hello subject;

  @Before
  public void setUp() throws Exception {
    subject = new Hello();
  }

  @Test
  public void shouldSayHelloWorld() throws Exception {
    assertThat(subject.sayHello(null), is("Hello World!"));
  }

  @Test
  public void shouldSayHelloWithSpecifiedName() throws Exception {
    String[] args = {"Name"};
    assertThat(subject.sayHello(args), is("Hello Name!"));
  }
}