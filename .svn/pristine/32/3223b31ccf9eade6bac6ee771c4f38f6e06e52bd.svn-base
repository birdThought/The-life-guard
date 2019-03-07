package com.lifeshs.utils.image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.lang3.StringUtils;

import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.model.image.SavePathReturn;
import com.lifeshs.utils.PropertiesUtil;
import com.lifeshs.utils.ResourceUtil;
import com.lifeshs.utils.UUID;
import com.sun.imageio.plugins.jpeg.JPEGImageWriter;

import sun.misc.BASE64Decoder;

public class ImageUtilV2 {

    static {
        PropertiesUtil p = new PropertiesUtil("environment.properties");
        ROOT_PATH = p.readProperty("imageSaveRootPath");
    }
    
    public static String ROOT_PATH;

    /** 服务器上传文件访问目录 */
    private static final String TOMCAT_UPLOAD_VISIT_PATH = "/lifekeepers_files";

    private static String separator = ResourceUtil.getSeparator();

    /**
     *  保存base64的图片
     *  @author yuhang.weng 
     *	@DateTime 2017年7月24日 下午4:09:15
     *
     *  @param base64Str base64字符串
     *  @param categoryPath 目录
     *  @param tmpFile 是否为临时文件
     *  @return
     *  @throws Exception
     */
    public static String saveBase64(String base64Str, String categoryPath, boolean tempFile) throws Exception {
        categoryPath = categoryPath(tempFile, categoryPath);
        // 1. 生成文件名
        String formatName = getFormatName(base64Str);
        String fileName = UUID.generate() + "." + formatName;
        // 2. 生成图片的保存地址
        SavePathReturn savePathReturn = generateSavePath(categoryPath, fileName);
        // 3. 将base64的字符串转换为byte数组
        byte[] data = readBase64(base64Str);
        // 4. 写文件
        writeByteFile(data, savePathReturn.getFilePath());
        // 5. 返回网络图片的路径
        String netPath = relativePath2NetPath(savePathReturn.getRelativePath());
        return netPath;
    }
    
    /**
     *  从网络上获取图片
     *  @author yuhang.weng 
     *  @DateTime 2017年9月4日 上午10:39:40
     *
     *  @param urlStr 网络路径
     *  @param categoryPath 文件目录
     *  @return 图片的网络路径
     * @throws Exception 
     */
    public static String saveUrl(String urlStr, String categoryPath, boolean tempFile) throws IOException {
        categoryPath = categoryPath(tempFile, categoryPath);
        
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置超时间为20秒
        conn.setConnectTimeout(20 * 1000);
        // 防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        // 得到输入流
        InputStream inputStream = conn.getInputStream();
        // 获取自己数组
        byte[] data = readInputStream(inputStream);
        inputStream.close();
        
        // 获取文件名称
        String formatName = formatName(data);
        // 生成一个文件名
        String fileName = UUID.generate() + "." + formatName;
        
        // 生成图片的保存地址
        SavePathReturn savePathReturn = generateSavePath(categoryPath, fileName);
        // byte写文件
        writeByteFile(data, savePathReturn.getFilePath());
        // 获取网络路径
        String netPath = relativePath2NetPath(savePathReturn.getRelativePath());
        return netPath;
    }
    
    /**
     *  保存图片
     *  @author yuhang.weng 
     *  @DateTime 2017年9月6日 上午9:10:41
     *
     *  @param data 图片源
     *  @param categoryPath 文件目录
     *  @param tempFile 是否为临时文件
     *  @return
     *  @throws IOException
     */
    public static String saveByte(byte[] data, String categoryPath, boolean tempFile) throws IOException {
        categoryPath = categoryPath(tempFile, categoryPath);
        String formatName = formatName(data);
        // 生成一个文件名
        String fileName = UUID.generate() + "." + formatName;
        // 生成图片的保存地址
        SavePathReturn savePathReturn = generateSavePath(categoryPath, fileName);
        // byte写文件
        writeByteFile(data, savePathReturn.getFilePath());
        // 获取网络路径
        String netPath = relativePath2NetPath(savePathReturn.getRelativePath());
        return netPath;
    }
    
    /**
     *  保存图片
     *  @author yuhang.weng 
     *  @DateTime 2017年9月6日 上午9:15:56
     *
     *  @param data 图片源
     *  @param categoryPath 文件目录
     *  @param tempFile 是否为临时文件
     *  @param width 自定义宽度
     *  @param height 自定义高度
     *  @return
     *  @throws IOException
     */
    public static String saveByte(byte[] data, String categoryPath, boolean tempFile, int width, int height) throws IOException {
        categoryPath = categoryPath(tempFile, categoryPath);
        String formatName = formatName(data);
        // 生成一个文件名
        String fileName = UUID.generate() + "." + formatName;
        // 生成图片的保存地址
        SavePathReturn savePathReturn = generateSavePath(categoryPath, fileName);

        File file = new File(savePathReturn.getFilePath());
        File parentFile = file.getParentFile();
        // 创建目录
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        FileOutputStream out = new FileOutputStream(file); // 输出到文件流
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        BufferedImage image = new BufferedImage(width, height, BufferedImage.SCALE_SMOOTH);

        InputStream in = new ByteArrayInputStream(data);
        BufferedImage img = ImageIO.read(in);
        image.getGraphics().drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

        ImageOutputStream ios = ImageIO.createImageOutputStream(out);

        JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpeg").next();
        imageWriter.setOutput(ios);

        IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(image), null);

        imageWriter.write(imageMetaData, new IIOImage(image, null, null), null);
        ios.close();
        imageWriter.dispose();
        out.close();

        String netPath = relativePath2NetPath(savePathReturn.getRelativePath());
        return netPath;
    }
    
    /**
     *  文件目录
     *  @author yuhang.weng 
     *  @DateTime 2017年9月5日 上午10:53:06
     *
     *  @param tempFile
     *  @param categoryPath
     *  @return
     */
    private static String categoryPath(boolean tempFile, String categoryPath) {
        // 如果不是临时文件，categoryPath加上upload
        // 如果是临时文件 categoryPath统一修改为tmp
        if (!tempFile) {
            categoryPath = "upload" + separator + categoryPath;
        } else {
            categoryPath = "temp";
        }
        return categoryPath;
    }
    
    /**
     *  复制图片到上传文件夹
     *  @author yuhang.weng 
     *  @DateTime 2017年9月5日 上午9:24:12
     *
     *  @param netPath 图片网络路径
     *  @param categoryPath 文件目录
     *  @return 新的图片网络路径
     *  @throws IOException
     */
    public static String copyImgFileToUploadFolder(String netPath, String categoryPath) throws IOException {
        return copyImgToUploadFolder(netPath, categoryPath, true);
    }
    
    /**
     *  复制图片到上传文件夹
     *  @author yuhang.weng 
     *  @DateTime 2017年9月5日 上午9:24:12
     *
     *  @param netPath 图片网络路径
     *  @param categoryPath 文件目录
     *  @param deleteOldFile 是否删除原文件（如果图片没有做任何移动的操作，就算是要求删除，也不会进行删除操作）
     *  @return 新的图片网络路径
     *  @throws IOException
     */
    public static String copyImgFileToUploadFolder(String netPath, String categoryPath, boolean deleteOldFile) throws IOException {
        return copyImgToUploadFolder(netPath, categoryPath, deleteOldFile);
    }
    
    /**
     *  复制图片到上传文件夹
     *  @author yuhang.weng 
     *  @DateTime 2017年9月5日 上午9:24:12
     *
     *  @param netPath 图片网络路径
     *  @param categoryPath 文件目录
     *  @param deleteOldFile 是否删除原文件（如果图片没有做任何移动的操作，就算是要求删除，也不会进行删除操作）
     *  @return 新的图片网络路径
     *  @throws IOException
     */
    private static String copyImgToUploadFolder(String netPath, String categoryPath, boolean deleteOldFile) throws IOException {
        String fileName = netPath.substring(netPath.lastIndexOf("/") + 1);

        String oldRelativePath = "";
        String preFolderName = Normal.PHOTO_PREFIX + TOMCAT_UPLOAD_VISIT_PATH;
        
        if (netPath.startsWith(preFolderName)) {
            oldRelativePath = netPath.substring(preFolderName.length() + 1);
        } else {
            if (netPath.startsWith(TOMCAT_UPLOAD_VISIT_PATH)) {
                oldRelativePath = netPath.substring(TOMCAT_UPLOAD_VISIT_PATH.length() + 1);
            } else {
                // 这里是错误的示范
                // 以前的图片保存路径都是相对路径 这里先把netPath当作相对路径处理
                if (netPath.startsWith("/")) {
                    netPath = netPath.substring(1);
                }
                oldRelativePath = netPath;
            }
            netPath = preFolderName + separator + oldRelativePath;
        }

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy" + separator + "MM" + separator + "dd");
        String newDir = format.format(date);

        String newPath = ROOT_PATH + separator + "upload" + separator + categoryPath + separator + newDir + separator + fileName;
        String newNetPath = TOMCAT_UPLOAD_VISIT_PATH + separator + "upload" + separator + categoryPath + separator + newDir + separator
                + fileName;

        String oldPath = ROOT_PATH + separator + oldRelativePath;

        File newFile = new File(newPath);
        if (newFile.exists())
            return relativePath2NetPath(newNetPath);
        File oldFile = new File(oldPath);
        if (!oldFile.exists()) {
            throw new IOException("找不到原文件");
        }
        
        int byteRead = 0;
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        
        InputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(oldFile.getPath());
            out = new FileOutputStream(newFile.getPath());
            byte[] buffer = new byte[1024];
            while ((byteRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteRead);
            }
        } catch (IOException ioE) {
            throw ioE;
        } finally {
            in.close();
            out.close();
        }
        if (deleteOldFile) {
            delImg(netPath);    // 删除原文件
        }
        return relativePath2NetPath(newNetPath);
    }
    
    /**
     * @param path
     * @author zhiguo.lin
     * @DateTime 2016年8月17日 上午11:25:53
     * @serverComment 删除上传的图片
     */
    public static boolean delImg(String netPath) {
        if (netPath != null) {
            String preFolderName = Normal.PHOTO_PREFIX + TOMCAT_UPLOAD_VISIT_PATH;
            String filePath = "";
            if (netPath.startsWith(preFolderName)) {
                filePath = ROOT_PATH + separator + netPath.substring(preFolderName.length() + 1);
            }
            if (netPath.startsWith(TOMCAT_UPLOAD_VISIT_PATH)) {
                filePath = ROOT_PATH + separator + netPath.substring(TOMCAT_UPLOAD_VISIT_PATH.length() + 1);
            }
            // 删除文件
            return deleteFile(filePath);
        }

        return false;
    }

    /**
     * 生成图片的保存路径
     * 
     * @param categoryPath 分类目录
     * @param fileName 文件名称
     *
     * @return
     */
    private static SavePathReturn generateSavePath(String categoryPath, String fileName) {
        String filePath = "";
        String relativePath = "";
        // rootPath 如果是根目录，就不需要添加多余的separator
        if (categoryPath.equals(separator)) {
            filePath = ROOT_PATH + separator;
        } else {
            filePath = ROOT_PATH + separator + categoryPath + separator;
            relativePath = categoryPath + separator;
        }

        // 生成 年/月/日 路径
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + separator + "MM" + separator + "dd");
        filePath += sdf.format(date);
        relativePath = StringUtils.isBlank(relativePath) ? sdf.format(date) : relativePath + sdf.format(date);
        filePath += separator + fileName;
        // 相对路径的文件路径
        relativePath = TOMCAT_UPLOAD_VISIT_PATH + separator + relativePath + separator + fileName;

        SavePathReturn savePath = new SavePathReturn();
        savePath.setFilePath(filePath);
        savePath.setRelativePath(relativePath);
        return savePath;
    }

    /**
     * 获取base64字符串所对应的图片格式
     * 
     * @author yuhang.weng
     * @DateTime 2017年5月19日 上午9:32:39
     *
     * @param base64Str
     * @return
     */
    private static String getFormatName(String base64Str) {
        if (base64Str.charAt(0) == '/') {
            return "jpg";
        } else if (base64Str.charAt(0) == 'R') {
            return "gif";
        } else if (base64Str.charAt(0) == 'i') {
            return "png";
        }
        return "jpg"; // 默认JPG格式
    }

    private static String relativePath2NetPath(String relativePath) {
        String netPath = "";
        if (StringUtils.isNotBlank(relativePath)) {
            // unix 与 window 的separator不同 需要替换
            relativePath = relativePath.replaceAll("\\\\", "/");
            relativePath = relativePath.replaceAll("//", "/");
            netPath = Normal.PHOTO_PREFIX + relativePath;
        }
        return netPath;
    }

    /**
     * @param path
     * @return
     * @author zhiguo.lin
     * @DateTime 2016年8月17日 上午10:59:41
     * @serverComment 删除文件
     */
    private static boolean deleteFile(String path) {
        Boolean flag = false;
        File file = new File(path);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
    
    /**
     * 匹配uedit上传的html带有图片的
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月30日
     * @serverComment
     */
    public static String regexPathFromHtml(String html) {
        if (html == null)
            return null;
        Pattern pattern = Pattern.compile("<img.+?>");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            String xml = matcher.group();
            int index = xml.indexOf("src=");
            int titleIndex = xml.indexOf("title=");// 下一个节点就是title
            String path = xml.substring(index + 5, titleIndex - 2);
            if (path.startsWith("upload"))// 编辑过的
                continue;
            try {
                String newPath = ImageUtilV2.copyImgFileToUploadFolder(path, "html", true);// 替换路径
                html = html.replace(path, newPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return html;
    }
    
    /**
     * 截取前三张图片
     *
     * @param html
     * @return
     */
    public static String get3IndexImgFromHtml(String html) {
        if (html == null)
            return null;
        Pattern pattern = Pattern.compile("<img.+?>");
        Matcher matcher = pattern.matcher(html);
        String pathList = "";
        int cursor = 0;
        while (matcher.find()) {
            String xml = matcher.group();
            int index = xml.indexOf("src=");
            int titleIndex = xml.indexOf("title=");// 下一个节点就是title
            String path = xml.substring(index + 5, titleIndex - 2);
            pathList += path + ",";
            if (cursor >= 2) {
                pathList = pathList.substring(0, pathList.length() - 1);
                break;
            }
            cursor++;
        }
        return pathList;
    }

    /**
     * 从输入流中获取字节数组
     * 
     * @param inputStream 输入流
     * @return
     * @throws IOException
     */
    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
    
    /**
     *  从base64中获取字节数组
     *  @author yuhang.weng 
     *  @DateTime 2017年9月5日 上午10:14:09
     *
     *  @param base64Str base64字符串
     *  @return
     *  @throws IOException 
     */
    private static byte[] readBase64(String base64Str) throws IOException {
        if (base64Str == null || base64Str.isEmpty()) // 图像数据为空
            throw new NullPointerException("不允许给入空值！");
        // Base64解码
        base64Str = base64Str.replaceAll("\n", "");
        base64Str = base64Str.replaceAll("\r", "");

        sun.misc.BASE64Decoder base64 = new BASE64Decoder();
        byte[] data = base64.decodeBuffer(base64Str);
        for (int i = 0; i < data.length; ++i) {
            if (data[i] < 0) {
                data[i] += 256;
            }
        }
        return data;
    }
    
    /**
     *  byte数组写文件
     *  @author yuhang.weng 
     *  @DateTime 2017年9月5日 上午10:07:03
     *
     *  @param data 文件数据
     *  @param filePath 想要保存的文件路径（包含文件名称）
     *  @throws IOException
     */
    private static void writeByteFile(byte[] data, String filePath) throws IOException {
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        // 创建目录
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        String fileName = file.getName();
        String formatName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        // 缓冲
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        BufferedImage image = ImageIO.read(bis);
        bis.close();
        // 4. 将BufferedImage写到磁盘中
        ImageIO.write(image, formatName, file);
    }
    
    private static String formatName(byte[] data) throws IOException {
        String formatName = "";
        InputStream in = new ByteArrayInputStream(data);
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        Iterator<ImageReader> reader = ImageIO.getImageReaders(iis);
        if (reader.hasNext()) {
            formatName = reader.next().getFormatName();
        }
        iis.close();
        in.close();
        
        switch (formatName) {
        case "JPEG":
            return "jpg";
        case "PNG":
        case "png":
            return "png";
        case "gif":
            return "gif";
        }
        return "jpg";
    }
}
