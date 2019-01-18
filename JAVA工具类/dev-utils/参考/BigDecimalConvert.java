

import java.math.BigDecimal;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class BigDecimalConvert extends  StrutsTypeConverter{
	
    public Object convertFromString(Map context, String[] values, Class toClass) {

        if (values.length > 0 && values[0] != null && values[0].trim().length() > 0) {
            try {
                return new BigDecimal(values[0]);
            }
            catch(Exception e) {
               
            }
        }
        return null;
    }

    public String convertToString(Map context, Object o) {

        if (o!=null) {
           return o.toString();
        }
        return null;
    }
}
