package testCases;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

public class ApiCompare {

    @Test(priority = 0)
    public static void apiCompare() throws IOException, ParseException {

        JSONObject obj1 = (JSONObject) new JSONParser().parse(new FileReader("C:\\Users\\devbase\\Projects\\opencart\\resources1\\CatalogAPI.json"));
        JSONObject obj2 = (JSONObject) new JSONParser().parse(new FileReader("C:\\Users\\devbase\\Projects\\opencart\\resources1\\HybrisAPI.json"));

        ObjectMapper mapper = new ObjectMapper();
        obj1.

        Assert.assertEquals(mapper.readTree(obj1.toJSONString()), mapper.readTree(obj2.toJSONString()));

    }

    //public static void main(String[] args) throws IOException, ParseException {
        //
}
