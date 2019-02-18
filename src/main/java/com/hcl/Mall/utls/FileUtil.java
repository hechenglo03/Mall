package com.hcl.Mall.utls;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;


public class FileUtil {

    //图片的相对位置
    private static final String PICFILE = FileUtil.class.getResource("/static/img").getPath().substring(1);


    /**
     * 保存图片，生成唯一名称，返回图片名称
     * @param file
     * @return
     * @throws Exception
     */
    public static String SavePic(MultipartFile file){
        String PicUid = UUID.randomUUID().toString().replace("-", "");
        String PicName = PicUid+file.getOriginalFilename();
        String PicUrl = PICFILE+"/"+PicName;
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(PicUrl);
            fileOutputStream.write(file.getBytes());
            return "/static/img/"+PicName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean deletePic(String pic){
        if(pic.startsWith("http"))//如果图片是以URL形式
            return true;
        String[] list = pic.split("/");
        String PicName = list[list.length-1];
        String picurl = PICFILE+"/"+PicName;
        File file = new File(picurl);
        return file.delete();//删除文件
    }
}
