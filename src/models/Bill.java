package models;

import java.awt.image.BufferedImage;

public class Bill {
    BufferedImage image;
    int denomination;

    public Bill(BufferedImage image, int denomination) {
        this.image = image;
        this.denomination = denomination;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "image=" + image +
                ", denomination=" + denomination +
                '}';
    }
}
