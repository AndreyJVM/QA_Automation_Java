package stepik;

import org.testng.annotations.Test;
import stepik.data.provider.MyDataProvider;

public class DataProviderTest {

    @Test(dataProvider = "dataProvider", dataProviderClass = MyDataProvider.class)
    public void testSample(Integer digit, String word) {
        System.out.printf("%s is %s\n", digit, word);
    }
}