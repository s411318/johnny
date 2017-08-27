package com.PetYM.aMain;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {

	/**
	 * 將�?�形縮�?��?��?�傳，�?��?�發??�錯誤就?��?��??�傳??��?��?? 例�?��?�imageSize??<=1?�無法�?��?��?��?�寬高�?�發??�Exception
	 * 
	 * @param srcImageData
	 *            來�?��?�形資�??
	 * @param newSize
	 *            欲�?��?�形縮至多�?�尺寸以下�??50�?表�?�尺寸縮小至50x50以內
	 * @return 縮�?��?�畢??��?�形資�??
	 */
	public static byte[] shrink(byte[] srcImageData, int newSize) {
		ByteArrayInputStream bais = new ByteArrayInputStream(srcImageData);
		// 縮�?��?��?��??4�?表除�?4
		double sampleSize = 1;
		int imageWidth = 0;
		int imageHeight = 0;

		// 如�?�欲縮�?��?�尺寸�?��?��?�就?��?��定為128
		if (newSize <= 1) {
			newSize = 128;
		}

		try {
			BufferedImage srcBufferedImage = ImageIO.read(bais);
			int type = srcBufferedImage.getType();
			String format = "";
			if (type == BufferedImage.TYPE_4BYTE_ABGR || type == BufferedImage.TYPE_4BYTE_ABGR_PRE
					|| type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_ARGB_PRE) {
				format = "png";
			} else {
				format = "jpg";
			}
			imageWidth = srcBufferedImage.getWidth();
			imageHeight = srcBufferedImage.getHeight();
			if (imageWidth == 0 || imageHeight == 0) {
				return srcImageData;
			}
			// ?��要�?��?��?�長??��???��?��?��?��?�長�?(desireSize)，就計�?�欲縮�?��?��?��??
			// 並�?��?��?�寬高都?��以欲縮�?��?��??
			int longer = Math.max(imageWidth, imageHeight);
			if (longer > newSize) {
				sampleSize = longer / (long) newSize;
				imageWidth = (int) (srcBufferedImage.getWidth() / sampleSize);
				imageHeight = (int) (srcBufferedImage.getHeight() / sampleSize);
			}

			BufferedImage scaledBufferedImage = new BufferedImage(imageWidth, imageHeight, type);
			Graphics graphics = scaledBufferedImage.createGraphics();
			graphics.drawImage(srcBufferedImage, 0, 0, imageWidth, imageHeight, null);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(scaledBufferedImage, format, baos);
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return srcImageData;
		}
	}
}
