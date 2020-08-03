package ImageLoad;

import java.awt.image.BufferedImage;

public class Spritsheet {

    private BufferedImage bufferedImage;

    public Spritsheet(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage getTile(int x, int y, int width, int height){
        return bufferedImage.getSubimage(x,y,width,height);
    }
}
