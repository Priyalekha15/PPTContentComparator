package slidetoImageConvertor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;


public class PPTToImageConvertor {

	public static void main(String args[]) throws IOException {
		File file=new File("ExpectedPPT.pptx");
		XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(file));
		
		//get the dimensions
		// get the dimension and size of the slide
				Dimension pgsize = ppt.getPageSize();
				List<XSLFSlide> slide = ppt.getSlides();
				BufferedImage img = null;
		 
				System.out.println("No of slides in ppt: "+ slide.size());
		 
				for (int i = 0; i < slide.size(); i++) {
					img = new BufferedImage(
						pgsize.width, pgsize.height,
						BufferedImage.TYPE_INT_RGB);
					Graphics2D graphics = img.createGraphics();
		 
					// clear area
					graphics.setPaint(Color.white);
					graphics.fill(new Rectangle2D.Float(
						0, 0, pgsize.width, pgsize.height));
		 
					// draw the images
					slide.get(i).draw(graphics);
					FileOutputStream out = new FileOutputStream(
						"ExpectedImages//ppt_image" + i + ".png");
					javax.imageio.ImageIO.write(img, "png", out);
					ppt.write(out);
					out.close();
					System.out.println(i);
				}
				System.out.println("Image successfully created");
			

	}

}
