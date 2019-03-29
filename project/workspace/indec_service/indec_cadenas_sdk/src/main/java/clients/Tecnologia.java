package clients;
import constants.Constants;

public enum Tecnologia {
    REST(Constants.REST),
    SOAP(Constants.SOAP);

    private final String name;

    Tecnologia(final String name) { this.name = name; }

    public String getName() { return this.name; }

}