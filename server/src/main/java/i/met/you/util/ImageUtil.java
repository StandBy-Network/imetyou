package i.met.you.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import i.met.you.bean.ImageResolution;

public class ImageUtil {
	
	private static final int IMG_WIDTH = 80;
	private static final int IMG_HEIGHT = 80;

	public static BufferedImage resizeImage(BufferedImage originalImage,
			int type) {
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,
				type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();

		return resizedImage;
	}

	public static BufferedImage resizeImage(BufferedImage originalImage,
			int type, Integer width, Integer height) {
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();

		return resizedImage;
	}

	public static BufferedImage resizeImageWithHint(
			BufferedImage originalImage, int type) {

		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,
				type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
	}

	public static void watermark(File file, String wmText, String destPath) {
		try {

			if (!file.exists()) {
				//System.out.println("File not Found");
			}
			ImageIcon icon = new ImageIcon(file.getPath());
			BufferedImage bufferedImage = new BufferedImage(
					icon.getIconWidth(), icon.getIconHeight(),
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
			g2d.drawImage(icon.getImage(), 0, 0, null);
			AlphaComposite alpha = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.5f);
			g2d.setComposite(alpha);
			g2d.setColor(Color.white);
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2d.setFont(new Font("Arial", Font.BOLD, 30));
			String watermark = wmText;
			FontMetrics fontMetrics = g2d.getFontMetrics();
			Rectangle2D rect = fontMetrics.getStringBounds(watermark, g2d);
			g2d.drawString(watermark,
					(icon.getIconWidth() - (int) rect.getWidth()) / 2,
					(icon.getIconHeight() - (int) rect.getHeight()) / 2);
			g2d.dispose();
			// File fileout = new
			// File("/home/levente/tmp/files/quadsky_wilson_big_w.jpg");
			File fileout = new File(destPath);
			ImageIO.write(bufferedImage, "jpg", fileout);
		} catch (IOException ioe) {
		}
	}

	public static ImageResolution getImageResolution(String location) {
		ImageResolution ir = new ImageResolution();
		Image image = null;
		try {
			image = ImageIO.read(new File(location));
			ir.setWidth(image.getWidth(null));
			ir.setHeight(image.getHeight(null));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ir;
	}

	public static BufferedImage base64ToPNG(String png64) throws IOException {

		byte[] decodedBytes = org.apache.commons.codec.binary.Base64
				.decodeBase64(png64);
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(
				decodedBytes));

		return image;
	}

	public static BufferedImage base64ToPNG(byte[] decodedBytes)
			throws IOException {

		BufferedImage image = ImageIO.read(new ByteArrayInputStream(
				decodedBytes));

		return image;
	}

	public static void base64ToBinary(String base64String, String path) {
		try {
			byte[] decodedBytes = org.apache.commons.codec.binary.Base64
					.decodeBase64(base64String);
			File decodedFile = new File(path);
			FileOutputStream os = new FileOutputStream(decodedFile);
			os.write(decodedBytes);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void base64ToBinary(byte[] decodedBytes, String path) {
		try {

			File decodedFile = new File(path);
			FileOutputStream os = new FileOutputStream(decodedFile);
			os.write(decodedBytes);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage scale(InputStream is, int max) {
		Image image = null;
		try {
			image = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		double dWidth = 0;
		double dHeight = 0;
		if (width == height) {
			dWidth = max;
			dHeight = max;
		} else if (width > height) {
			dWidth = max;
			dHeight = ((double) height / (double) width) * max;
		} else {
			dHeight = max;
			dWidth = ((double) width / (double) height) * max;
		}
		image = image.getScaledInstance((int) dWidth, (int) dHeight,
				Image.SCALE_SMOOTH);
		BufferedImage bImage = toBufferedImage(image);
		return bImage;

	}

	public static BufferedImage scale(Image image, int max) {

		int width = image.getWidth(null);
		int height = image.getHeight(null);
		double dWidth = 0;
		double dHeight = 0;
		if (width == height) {
			dWidth = max;
			dHeight = max;
		} else if (width > height) {
			dWidth = max;
			dHeight = ((double) height / (double) width) * max;
		} else {
			dHeight = max;
			dWidth = ((double) width / (double) height) * max;
		}
		image = image.getScaledInstance((int) dWidth, (int) dHeight,
				Image.SCALE_SMOOTH);
		BufferedImage bImage = toBufferedImage(image);
		return bImage;

	}

	public static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		BufferedImage bimage = new BufferedImage(img.getWidth(null),
				img.getHeight(null), BufferedImage.TYPE_INT_RGB);

		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		return bimage;
	}

	public static List<String> getPictureFileList(String path) {
		List<String> fileNames = new ArrayList<String>();

		String fileName;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles != null) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					fileName = listOfFiles[i].getName();
					if (fileName.toLowerCase().endsWith(".jpg")
							|| fileName.toLowerCase().endsWith(".jpeg")
							|| fileName.toLowerCase().endsWith(".gif")
							|| fileName.toLowerCase().endsWith(".png")) {
						// System.out.println(fileName);
						fileNames.add(fileName);
					}
				}
			}
		}
		
		Collections.sort(fileNames);
		
		
		return fileNames;
	}

	public static void deletePicture(String path, String fileName) {
		try {

			File f1 = new File(path + fileName);
			File f2 = new File(path + "big/" + fileName);
			File f3 = new File(path + "original/" + fileName);
			File f4 = new File(path + "thumb/" + fileName);
			f1.delete();
			f2.delete();
			f3.delete();
			f4.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void mergeImages(BufferedImage image, BufferedImage overlay,
			String path, String fileName) throws IOException {

		int w = Math.max(image.getWidth(), overlay.getWidth());
		int h = Math.max(image.getHeight(), overlay.getHeight());
		BufferedImage combined = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = combined.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.drawImage(overlay, 0, 0, null);

		// Save as new image
		ImageIO.write(combined, "JPG", new File(path, fileName));

	}

	public static void mergeImages(BufferedImage image, BufferedImage overlay,
			String path, String fileName, String type) throws IOException {

		int w = Math.max(image.getWidth(), overlay.getWidth());
		int h = Math.max(image.getHeight(), overlay.getHeight());
		BufferedImage combined = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = combined.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.drawImage(overlay, 0, 0, null);

		// Save as new image
		ImageIO.write(combined, type, new File(path, fileName));

	}

	public static void main(String[] args) throws Exception {

		/*
		 * File path = null; BufferedImage image = ImageIO.read(new File(path,
		 * "/a/image.jpg")); BufferedImage overlay = ImageIO.read(new File(path,
		 * "/a/overlay.png"));
		 * 
		 * ImageUtil.mergeImages(image,overlay,"/a", "image.jpg");
		 * 
		 * System.out.println(ImageUtil.getImageResolution("/a/image.jpg"));
		 */
	//	String fileName = "c:/eurotax.pdf";
	//	File file = new File(fileName);
	//	FileInputStream fis = new FileInputStream(file);
	//	byte byteData[] = new byte[(int) file.length()];
	//	fis.read(byteData);
	//	String encoded = new String(Base64.encode(byteData));
	//	System.out.println(encoded);
	//	ImageUtil.base64ToBinary(encoded, "c:/a/a.pdf");

	}

}
