package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[80];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world2.txt");
    }

    public void getTileImage()
    {

        setup(0, "notexture", false);
        setup(1, "notexture", false);
        setup(2, "notexture", false);
        setup(3, "notexture", false);
        setup(4, "notexture", false);
        setup(5, "notexture", false);
        setup(6, "notexture", false);
        setup(7, "notexture", false);
        setup(8, "notexture", false);
        setup(9, "notexture", false);

        setup(10, "GrassS", false);
        setup(11, "notexture", false);
        setup(12, "GrassT", true);
        setup(13, "GrassB", true);
        setup(14, "GrassL", true);
        setup(15, "GrassR", true);
        setup(16, "GrassRTI", true);
        setup(17, "GrassLTI", true);
        setup(18, "GrassRBI", true);
        setup(19, "GrassLBI", true);
        setup(20, "GrassRTO", true);
        setup(21, "GrassLTO", true);
        setup(22, "GrassRBO", true);
        setup(23, "GrassLBO", true);

        setup(24, "RoadS", false);
        setup(25, "RoadT", false);
        setup(26, "RoadB", false);
        setup(27, "RoadL", false);
        setup(28, "RoadR", false);
        setup(29, "RoadRTI", false);
        setup(30, "RoadLTI", false);
        setup(31, "RoadRBI", false);
        setup(32, "RoadLBI", false);
        setup(33, "RoadRTO", false);
        setup(34, "RoadLTO", false);
        setup(35, "RoadRBO", false);
        setup(36, "RoadLBO", false);

        setup(37, "SandS", false);
        setup(38, "SandT_W", true);
        setup(39, "SandB_W", true);
        setup(40, "SandL_W", true);
        setup(41, "SandR_W", true);
        setup(42, "SandRTI_W", true);
        setup(43, "SandLTI_W", true);
        setup(44, "SandRBI_W", true);
        setup(45, "SandLBI_W", true);
        setup(46, "SandRTO_W", true);
        setup(47, "SandLTO_W", true);
        setup(48, "SandRBO_W", true);
        setup(49, "SandLBO_W", true);

        setup(50, "SandT_G", false);
        setup(51, "SandB_G", false);
        setup(52, "SandL_G", false);
        setup(53, "SandR_G", false);
        setup(54, "SandRTI_G", false);
        setup(55, "SandLTI_G", false);
        setup(56, "SandRBI_G", false);
        setup(57, "SandLBI_G", false);
        setup(58, "SandRTO_G", false);
        setup(59, "SandLTO_G", false);
        setup(60, "SandRBO_G", false);
        setup(61, "SandLBO_G", false);

        setup(62, "BridgeT", true);
        setup(63, "BridgeB", true);
        setup(64, "BridgeL", true);
        setup(65, "BridgeR", true);
        setup(66, "BridgeS_NS", false);
        setup(67, "BridgeS_WE", false);

        setup(68, "DirtS", false);
        setup(69, "notexture", false);

        setup(70, "WaterS", true);
        setup(71, "WaterA", true);

        setup(72, "WallS", true);
    }

    public void setup(int index, String image, boolean collision)
    {
        UtilityTool uTool = new UtilityTool();

        try
        {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + image + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapLocation) {
        try {
            InputStream is = getClass().getResourceAsStream(mapLocation);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e){

        }
    }

    public void draw(Graphics2D g2){
        //g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);

        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY,null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
