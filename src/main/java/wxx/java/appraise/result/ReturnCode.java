package wxx.java.appraise.result;

/**
 * 返回码
 */


import java.util.HashMap;
import java.util.Map;

public class ReturnCode {

    private static Map<Object, Object> codeMap = new HashMap();

    static {

        codeMap.put(0, "操作成功！");
        codeMap.put(-1, "error");
        codeMap.put(2, "time is out");
        codeMap.put(1, "失效的Token");
        codeMap.put(10,"用户名或密码为空");
        codeMap.put(11,"身份证号码和真实姓名不能为空");

    }

    public static Object getCode(int code) {

        codeMap.put(code, codeMap.get(code));

        return codeMap.get(code);

    }


}
