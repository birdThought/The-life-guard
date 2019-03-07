package com.lifeshs.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/19.
 */
public class ZxingUtil {

    /** 二维码颜色*/
    private static final int BLACK = 0xFF000000;
    /** 二维码颜色*/
    private static final int WHITE = 0xFFFFFFFF;

    /**
     *   生成二维码 不带logo
     * @param text
     * @param width
     * @param height
     * @param outPutPath 保存路径
     * @param imageType 生成格式
     */
    public static void zxingCodeCreate(String text,int width,int height,String outPutPath,String imageType){
        Map<EncodeHintType, String> hint = new HashMap<>();
        hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            //生成二维码
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE,width,height,hint);
            int codeWidth = encode.getWidth();
            int codeheight = encode.getHeight();
            //放入缓冲流
            BufferedImage eredImage = new BufferedImage(codeWidth, codeheight, BufferedImage.TYPE_INT_RGB);
            for (int i= 0; i < codeWidth; i++){
                for (int j= 0; j < codeheight; j++){
                    eredImage.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
                }
            }

            File outPutImage = new File(outPutPath);
            // 不存在创建
            if (!outPutImage.exists())
                outPutImage.createNewFile();
            // 写入
            ImageIO.write(eredImage, imageType, outPutImage);

        } catch (WriterException e) {
            e.printStackTrace();
            System.out.println("二维码生成失败");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("生成二维码图片失败");
        }
    }

    /**
     *  解析二维码
     * @param analyzePath
     * @return
     */
    @SuppressWarnings({"rawtypes","unchecked"})
    public static Object zxingCodeAnalyze(String analyzePath){
        MultiFormatReader formatReader = new MultiFormatReader();
        Object result = "";
        try {
        File file = new File(analyzePath);
        if (!file.exists()){
            return "二维码不存在";
        }
            BufferedImage image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map hiths = new HashMap();
            hiths.put(EncodeHintType.CHARACTER_SET, "utf-8");
            result = formatReader.decode(binaryBitmap, hiths);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     *  带logo二维码
     * @param context 内容
     * @param width  宽
     * @param heigth  高
     * @param imagePath  生成保存地址
     * @param logoPath  logo地址
     * @return
     */
    public static boolean QrCodeCreate(String context,int width,int heigth,String imagePath,String logoPath){
        Map<EncodeHintType, Object> hints = new HashMap<>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 设置距离
        hints.put(EncodeHintType.MARGIN, 1);
        String foramt = "png";
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(context, BarcodeFormat.QR_CODE, width, heigth, hints);
            MatrixToImageWriter.writeToStream(bitMatrix,foramt,new FileOutputStream(imagePath));
            File fileCode = new File(imagePath);
            writeToLogo(bitMatrix, foramt, fileCode, logoPath);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("二维码生成成功");
        return true;
    }

    private static void writeToLogo(BitMatrix bitMatrix, String foramt, File fileCode, String logoPath) {
        Graphics2D graphics = null;
        BufferedImage image = null;
        BufferedImage Logo = null;
        // TODO 读取二维码图片 并构建绘图对象
        image = toBufferedImage(bitMatrix);
        graphics = image.createGraphics();
        try {
            // TODO  读取logo图片
            Logo = ImageIO.read(new File(logoPath));
            int codeWidth = image.getWidth();
            int height = image.getHeight();
            // TODO 设置logo大小 设置二维码图片的25% 过大会覆盖二维码
            int widthlogo = Logo.getWidth(null) > codeWidth * 2 / 13 ? (codeWidth * 2 / 13) : Logo.getWidth(null);
            int heigthlogo = Logo.getHeight(null) > height * 2 / 13 ? (height * 2 / 13) : Logo.getHeight(null);
            /** 计算图片的位置 */
            int x = (codeWidth - widthlogo) / 2;
            int y = (height - heigthlogo) / 2;
            // 圆角范围
            int redius = 14;
            // TODO 填充与logo大小类似的扁平化圆角矩形背景
            graphics.setComposite(AlphaComposite.Src);
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(Color.WHITE);
            graphics.fill(new RoundRectangle2D.Float(x - 2, y - 2, widthlogo + 4, heigthlogo + 4, redius, redius));
            graphics.setComposite(AlphaComposite.SrcAtop);
            // 开始绘图
            graphics.drawImage(Logo,x,y,widthlogo,heigthlogo,null);
            if (!ImageIO.write(image,foramt,fileCode)){
                throw new IOException("Could not write an image of format" + foramt + " to " + fileCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (graphics != null){
                graphics.dispose();
            }
            if (Logo != null){
                Logo.flush();
            }
            if (image != null){
                image.flush();
            }
        }
    }
    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {  // x
            for (int j = 0; j<height;j++){ // y
                image.setRGB(i, j, matrix.get(i, j) ? BLACK : WHITE);
            }
        }
        return image;
    }

}
