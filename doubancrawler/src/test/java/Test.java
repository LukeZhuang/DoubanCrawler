import com.luke.doubancrawler.dal.mapper.DoubanMapper;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by luke on 2017/4/10.
 */
public class Test {


    public static void main(String[] args) throws UnsupportedEncodingException {
        String a="https://movie.douban.com/tag/%E7%88%B1%E6%83%85?start=20&type=T";
        String TYPE_INDEX="https://movie\\.douban\\.com/tag/.+?(start=\\d+&type=T)?$";
        System.out.println(a.matches(TYPE_INDEX));
    }
}
