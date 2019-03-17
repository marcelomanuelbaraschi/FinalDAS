package clients;
import constants.Constants;

public enum ClientType {
    REST(Constants.REST),
    SOAP(Constants.SOAP);

    private final String name;

    ClientType(final String name) { this.name = name; }

    public String getName() { return this.name; }

}