package com.dyzhome.common.util;

import cn.hutool.core.io.FileTypeUtil;
import com.dyzhome.common.exception.SysException;
import com.dyzhome.common.result.R;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author Dyz
 * 文件操作类
 */
@Component
public class FileUtil {

    @Value("${file-save-path}")
    private String uploadPath;

    private static final String PNG = "png";
    private static final String JPG = "jpg";
    private static final String JPEG = "jpeg";
    private static final String MP4 = "mp4";

    /**
     * 视频文件上传
     *
     * @param file 文件
     */
    public String uploadVideo(MultipartFile file) {
        try {
            String type = FileTypeUtil.getType(file.getInputStream());
            if (!type.equalsIgnoreCase(MP4)) {
                throw new SysException(R.ERROR_FILE_TYPE);
            }
            File newFile = upload(file, "video");
            return newFile.getName();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 头像上传
     *
     * @param file 上传的头像文件
     */
    public String uploadAvatar(MultipartFile file) {
        try {
            boolean isPic = isPic(file.getInputStream());
            if (!isPic){
                throw new SysException(R.ERROR_FILE_TYPE);
            }
            //上传头像
            File newFile = upload(file, "avatar");
            //计算图片大小
            int[] imgWidthHeight = getImgWidthHeight(newFile);
            int avatarSize = Math.min(imgWidthHeight[0], imgWidthHeight[1]);
            String fileName = newFile.getName();
            String newName = fileName.substring(0, fileName.lastIndexOf(".")) + ".jpg";
            //裁剪压缩格式转换
            trimCompressPic(newFile, avatarSize, avatarSize, newFile.getParent() + "/" + newName);
            if (!newName.equals(fileName)) {
                boolean isDel = newFile.delete();
            }
            return newName;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 封面上传
     *
     * @param file 上传的封面
     * @return 返回新的文件名
     */
    public String uploadCover(MultipartFile file) {
        try {
            boolean isPic = isPic(file.getInputStream());
            if (!isPic){
                throw new SysException(R.ERROR_FILE_TYPE);
            }
            //上传
            File newFile = upload(file, "cover");
            //计算大小
            int[] imgWidthHeight = getImgWidthHeight(newFile);
            int width = imgWidthHeight[0];
            int height = (int) (width / 1.65);
            String fileName = newFile.getName();
            String newName = fileName.substring(0, fileName.lastIndexOf(".")) + ".jpg";
            //如果图片的高度比长度/1.65还小，并且宽度大于1300，则直接压缩图片
            if (height > imgWidthHeight[1] && width > 1300) {
                compressPic(newFile, newFile.getParent() + "/" + newName);
                //否则裁剪图片比例至1.65:1，再压缩
            } else {
                trimCompressPic(newFile, width, height, newFile.getParent() + "/" + newName);
            }
            if (!newName.equals(fileName)) {
                boolean isDel = newFile.delete();
            }
            return newName;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private boolean isPic(InputStream inputStream){
        String type = FileTypeUtil.getType(inputStream);
        if (type.equalsIgnoreCase(JPG)||type.equalsIgnoreCase(PNG)||type.equalsIgnoreCase(JPEG)){
            return true;
        }
        return false;
    }
    /**
     * 其他文件上传
     *
     * @param file 要上传的文件
     */
    private File upload(MultipartFile file, String path) throws IOException {
        String rootPath = uploadPath + path;
        String fileName = file.getOriginalFilename();
        String newName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        File newFile = new File(rootPath, newName);
        if (!newFile.exists()) {
            boolean create = newFile.mkdirs();
        }
        file.transferTo(newFile);
        return newFile;
    }

    /**
     * 压缩图片
     *
     * @param file     上传的图片
     * @param savePath 保存的路径
     */
    private void compressPic(File file, String savePath) {
        try {
            Thumbnails.of(file).scale(1f).outputQuality(0.5f)
                    .outputFormat("jpg").toFile(savePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("图片上传异常！");
        }
    }

    /**
     * 裁剪并压缩图片
     *
     * @param file     上传的图片
     * @param width    裁剪后的宽
     * @param height   裁剪后的长
     * @param savePath 保存路径
     */
    private void trimCompressPic(File file, int width, int height, String savePath) {
        try {
            Thumbnails.of(file)
                    .sourceRegion(Positions.CENTER, width, height)
                    .size(width, height)
                    .keepAspectRatio(false)
                    .outputQuality(0.7f)
                    .outputFormat("jpg")
                    .toFile(savePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("图片上传异常！");
        }
    }

    /**
     * 获取图片宽度和高度
     *
     * @param file 上传的图片
     * @return 返回图片的宽度
     */
    private int[] getImgWidthHeight(File file) {
        InputStream is = null;
        BufferedImage bi = null;
        int[] result = {0, 0};
        try {
            // 获得文件输入流
            is = new FileInputStream(file);
            // 从流里将图片写入缓冲图片区
            bi = ImageIO.read(is);
            // 得到源图片宽
            result[0] = bi.getWidth(null);
            // 得到源图片高
            result[1] = bi.getHeight(null);
        } catch (Exception ef) {
            ef.printStackTrace();
            throw new RuntimeException("图片上传异常！");
        } finally {
            try {
                if (is != null) {
                    is.close();  //关闭输入流
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
