package training.cd;

public class Hello {
  public String sayHello(String[] args) {

    String name = "World";

    if (args != null && args.length > 0) {
      name = args[0];
    }

    return String.format("Hello %s!", name);
  }
}
