package cn.liuzhengwei.ebook.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class SrcController {
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request) {

        String contentType = file.getContentType();   //图片文件类型
        String fileName = file.getOriginalFilename();  //图片名字

        //文件存放路径
        String filePath = "C:\\Users\\75667\\Pictures\\ebook\\";

        //处理文件，将文件写入指定位置
        try {
            byte[] file_b = file.getBytes();
            File targetFile = new File(filePath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(filePath+fileName);
            out.write(file_b);
            out.flush();
            out.close();
        } catch (Exception e) {
            return "false";
        }

        // 返回图片的存放路径
        return filePath;
    }

    @RequestMapping(value="/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(@RequestParam("filename") String filename) {
        String resultInfo;
        String path = "C:\\Users\\75667\\Pictures\\ebook\\"+filename;
        File file = new File(path);
        if (file.exists()) {
            if (file.delete()) {
                resultInfo =  "删除成功";
            } else {
                resultInfo =  "删除失败";
            }
        } else {
            resultInfo = "文件不存在";
        }

        return resultInfo;
    }
}
