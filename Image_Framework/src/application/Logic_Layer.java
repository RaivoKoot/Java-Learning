package application;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Logic_Layer {

	private BufferedImage map;

	public Logic_Layer() {
		map = null;
	}

	public BufferedImage getMap() {
		return map;
	}

	public void setMap(BufferedImage map) {
		this.map = map;
	}

	public void setMap(File image_file) {

		try {
			BufferedImage buffered_image = ImageIO.read(image_file);

			this.map = buffered_image;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void edit_image() {

		for (int x = 300; x < 400; x++)
			for (int y = 300; y < 400; y++)
				map.setRGB(x, y, Color.ORANGE.getRGB());

	}

	public void edit_pixel(int x, int y, int color) {

		map.setRGB(x, y, color);

	}

}
