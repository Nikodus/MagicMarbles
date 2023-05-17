package Version2.magicmarbles.view;

public class PixelHelper {
    private static int DIAMETER;

    public static void setRADIUS(int radius) {
        DIAMETER = 2 * radius;
    }

    public static int[] convertPixelToIndex(int x, int y) {
        int[] widthAndHeightArr = new int[2];
        widthAndHeightArr[0] = x / DIAMETER;
        widthAndHeightArr[1] = y / DIAMETER;
        return widthAndHeightArr;
    }

    public static int[] convertIndexToPixel(int x, int y) {
        int[] widthAndHeightArr = new int[2];
        widthAndHeightArr[0] = x * DIAMETER;
        widthAndHeightArr[1] = y * DIAMETER;
        return widthAndHeightArr;
    }

}
