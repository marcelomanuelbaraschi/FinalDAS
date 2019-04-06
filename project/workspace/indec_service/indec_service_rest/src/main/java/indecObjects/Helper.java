package indecObjects;

import clients.Tecnologia;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Helper {
    public static List<String> fromCsvToList(String commaSeparatedStr)
    {
        String[] commaSeparatedArr = commaSeparatedStr.split("\\s*,\\s*");
        List<String> result = Arrays.stream(commaSeparatedArr).collect(Collectors.toList());
        return result;
    }

}
