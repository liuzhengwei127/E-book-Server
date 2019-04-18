package cn.liuzhengwei.ebook.controller;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@RestController
public class SrcController {
    //图片写入路径
    private String pathRoot = "C:\\Users\\75667\\vueProject\\E-book\\public\\images\\";
    //private String pathRoot = "/var/www/html/ebook/images/";

    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private GridFSBucket gridFSBucket;

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {

        //传入图片文件名
        String fileName = file.getOriginalFilename();

        //处理文件，将文件写入指定位置
        try {
            byte[] file_b = file.getBytes();
            File targetFile = new File(pathRoot);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(pathRoot+fileName);
            out.write(file_b);
            out.flush();
            out.close();
        } catch (Exception e) {
            return e.getMessage();
        }

        try {
            InputStream inputStream = file.getInputStream();
            gridFsTemplate.store(inputStream, fileName);
        }catch (Exception e) {
            return e.getMessage();
        }

        // 返回图片的存放路径
        return fileName;
    }

    @RequestMapping(value="/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(@RequestParam("filename") String filename) {
        String result;
        String path = pathRoot+filename;
        File file = new File(path);

        //删除图片
        if (file.exists()) {
            if (file.delete()) {
                result =  "删除成功";
            } else {
                result =  "删除失败";
            }
        } else {
            result = "文件不存在";
        }

        //返回字符串形式结果
        return result;
    }

    @RequestMapping(value = "/downloadFile")
    public void downloadFile(@RequestParam(name = "filename") String filename, HttpServletResponse response) throws Exception {
        Query query = Query.query(Criteria.where("filename").is(filename));
        // 查询单个文件
        GridFSFile gfsfile = gridFsTemplate.findOne(query);
        if (gfsfile == null) {
            return;
        }

        GridFsResource resource = new GridFsResource(gfsfile, gridFSBucket.openDownloadStream(gfsfile.getObjectId()));
        InputStream inputStream = resource.getInputStream();
        BufferedImage bi = ImageIO.read(inputStream);
        ImageIO.write(bi,"JPG",response.getOutputStream());

    }
}
