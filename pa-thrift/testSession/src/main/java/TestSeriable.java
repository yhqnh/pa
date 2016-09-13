import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/8/12.
 */
public class TestSeriable {

    public static void main(String[] args) throws Exception{
        UserParam user = new UserParam();
        user.setId(1);
        user.setName("name");
        System.out.println(new String(new JdkSerializationRedisSerializer().serialize(user),"utf8"));
    }
}
