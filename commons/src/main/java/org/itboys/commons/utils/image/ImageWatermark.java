package org.itboys.commons.utils.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 图片加水印
 * @author ChenJunhui
 *
 */
public class ImageWatermark {
	
	private  static Logger logger = LoggerFactory.getLogger(ImageWatermark.class);
	
	 /**图片格式：JPG*/
    private static final String PICTRUE_FORMATE_JPG = "jpg";
    public static final int FLAG_FLET_TOP = 1;//默认左上角
    public static final int FLAG_CENTER = 2;//默认中间
    public static final int FLAG_RIGHT_BUTTOM = 3;//默认右下角
    
    /**
     * 添加图片水印
     * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
     * @param waterImg  水印图片路径，如：C://myPictrue//logo.png
     * @param x 水印图片距离目标图片左侧的偏移量，如果x<0, 视flag定
     * @param y 水印图片距离目标图片上侧的偏移量，如果y<0, 视flag定
     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     * @param flag 1:x<0 或 y<0 左上角 2:x<0 或 y<0 右下角 3:x<0 或 y<0 中间
*/
    public final static void pressImage(String targetImg, String waterImg, int x, int y, float alpha,int flag) {
            try {
                File file = new File(targetImg);
                Image image = ImageIO.read(file);
                int width = image.getWidth(null);//原图宽
                int height = image.getHeight(null);//原图长
                BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = bufferedImage.createGraphics();
                g.drawImage(image, 0, 0, width, height, null);
            
                Image waterImage = ImageIO.read(new File(waterImg));    // 水印文件
                int width_1 = waterImage.getWidth(null);//水印文件宽
                int height_1 = waterImage.getHeight(null);//水印文件长
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
                
                int widthDiff = width - width_1;
                int heightDiff = height - height_1;
                if(x < 0){
                	if(FLAG_CENTER==flag){
                		 x = widthDiff / 2;
                	}else if(FLAG_FLET_TOP==flag){
                		x=0;
                	}else{
                		x = widthDiff;
                	}
                }else if(x > widthDiff){
                    x = widthDiff;
                }
                if(y < 0){
                	if(FLAG_CENTER==flag){
                		y = heightDiff / 2;
                	}else if(FLAG_FLET_TOP==flag){
                		y=0;
                	}else{
                		y = heightDiff;
                	}
                }else if(y > heightDiff){
                    y = heightDiff;
                }
                g.drawImage(waterImage, x, y, width_1, height_1, null); // 水印文件结束
                g.dispose();
                ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
            } catch (IOException e) {
            	logger.error("pressImage error ", e);
                throw new RuntimeException(e);
            }
    }

    /**
     * 添加文字水印
     * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
     * @param pressText 水印文字， 如：杭州圣代网络
     * @param fontName 字体名称，    如：宋体
     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize 字体大小，单位为像素
     * @param color 字体颜色
     * @param x 水印文字距离目标图片左侧的偏移量，如果x<0, 则在右下角
     * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在右下角
     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     *   @param flag 1:x<0 或 y<0 左上角 2:x<0 或 y<0 右下角 3:x<0 或 y<0 中间
*/
    public static void pressText(String targetImg, String pressText, String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha,int flag) {
        try {
            File file = new File(targetImg);
            
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setColor(color);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            
            int width_1 = fontSize * getLength(pressText);
            int height_1 = fontSize;
            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if(x < 0){
            	if(FLAG_CENTER==flag){
            		 x = widthDiff / 2;
            	}else if(FLAG_FLET_TOP==flag){
            		x=0;
            	}else{
            		x = widthDiff;
            	}
            }else if(x > widthDiff){
                x = widthDiff;
            }
            if(y < 0){
            	if(FLAG_CENTER==flag){
            		y = heightDiff / 2;
            	}else if(FLAG_FLET_TOP==flag){
            		y=0;
            	}else{
            		y = heightDiff;
            	}
            }else if(y > heightDiff){
                y = heightDiff;
            }
            
            g.drawString(pressText, x, y + height_1);
            g.dispose();
            ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
     * @param text
     * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
     */
    public static int getLength(String text) {
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
                length++;
            }
        }
        return (length % 2 == 0) ? length / 2 : length / 2 + 1;
    }

    /**
     * 图片缩放
     * @param filePath 图片路径
     * @param height 高度
     * @param width 宽度
     * @param bb 比例不对时是否需要补白
*/
    public static void resize(String filePath, int height, int width, boolean bb) {
        try {
            double ratio = 0; //缩放比例    
            File f = new File(filePath);   
            BufferedImage bi = ImageIO.read(f);   
            Image itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);   
            //计算比例   
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {   
                if (bi.getHeight() > bi.getWidth()) {   
                    ratio = (new Integer(height)).doubleValue() / bi.getHeight();   
                } else {   
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();   
                }   
                AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);   
                itemp = op.filter(bi, null);   
            }   
            if (bb) {   
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);   
                Graphics2D g = image.createGraphics();   
                g.setColor(Color.white);   
                g.fillRect(0, 0, width, height);   
                if (width == itemp.getWidth(null))   
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);   
                else 
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);   
                g.dispose();   
                itemp = image;   
            }
            ImageIO.write((BufferedImage) itemp, "jpg", f);   
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
      ImageWatermark.pressText("D:\\1.jpg", "俊哥哥", "宋体", Font.BOLD, 24, Color.red, 50, 50, 0.3f, 1);
      ImageWatermark.pressImage("D:\\1.jpg", "D:\\logo.jpg", -1,-1, 0.3f,3);
    }
}
