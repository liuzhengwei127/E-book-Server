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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    public String upload(@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {

        // 判断是否需要删除书籍封面图片
        String filename = (String)session.getAttribute("fileDelete");
        if (filename != null) {
            // mongodb图片数据删除
            Query query = Query.query(Criteria.where("filename").is(filename));
            gridFsTemplate.delete(query);
            session.removeAttribute("fileDelete");
        }

        session.setAttribute("file", file.getInputStream());

        //获取图片文件名
        String fileName = file.getOriginalFilename();

        // 返回图片名
        return fileName;
    }

    @RequestMapping(value="/delete", method = RequestMethod.GET)
    @ResponseBody
    public void delete(@RequestParam("filename") String filename, HttpSession session) {
        session.setAttribute("fileDelete", filename);
        session.removeAttribute("file");
        return;
    }

    @RequestMapping(value = "/images/{filename}")
    public void downloadFile(@PathVariable String filename, HttpServletResponse response) throws Exception {
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
