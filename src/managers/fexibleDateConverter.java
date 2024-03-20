package managers;

import com.thoughtworks.xstream.converters.ErrorWritingException;
import com.thoughtworks.xstream.converters.basic.DateConverter;

public class fexibleDateConverter extends DateConverter {
    public fexibleDateConverter() {
        super("yyyy-MM-dd HH:mm:ss.SSS z", new String[]{});
    }

    @Override
    public Object fromString(String str) {
        try {
            return super.fromString(str);
        } catch (Exception e) {
            throw new ErrorWritingException(str) {
            };
        }
    }
}
