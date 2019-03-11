package clients.factory;
import constants.Constants;

public enum ClientType {
    AXIS(Constants.AXIS),
    REST(Constants.REST),
    CXF(Constants.CXF);

    private final String name;

    ClientType(final String name) { this.name = name; }

    public String getName() { return this.name; }

}