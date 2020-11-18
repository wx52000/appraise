package wxx.java.appraise.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("down")
public class down360Controller {

    @RequestMapping("down360")
    public void down360(HttpServletResponse response) {
        String s = "";
        try {
            String fileName = "360急速浏览器.exe";
            InputStream inputStream = new FileInputStream("D:\\excel\\360CSE.exe");
            response.reset();
            response.setContentType("bin");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            // 循环取出流中的数据
            byte[] b = new byte[100];
            int len;
            try {
                while ((len = inputStream.read(b)) > 0)
                    response.getOutputStream().write(b, 0, len);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
