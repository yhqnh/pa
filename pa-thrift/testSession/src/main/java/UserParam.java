import lombok.Data;

import java.io.Serializable;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/8/2.
 */
@Data
public class UserParam implements Serializable{

    private String name;

    private int id;

    private School school;
}
