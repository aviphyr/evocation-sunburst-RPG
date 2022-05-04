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

        tile = new Tile[120];
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
        setup(11, "GrassA", false);
        setup(12, "GrassT", false);
        setup(13, "GrassB", false);
        setup(14, "GrassL", false);
        setup(15, "GrassR", false);
        setup(16, "GrassRTI", false);
        setup(17, "GrassLTI", false);
        setup(18, "GrassRBI", false);
        setup(19, "GrassLBI", false);
        setup(20, "GrassRTO", false);
        setup(21, "GrassLTO", false);
        setup(22, "GrassRBO", false);
        setup(23, "GrassLBO", false);

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
        setup(38, "SandT_W", false);
        setup(39, "SandB_W", false);
        setup(40, "SandL_W", false);
        setup(41, "SandR_W", false);
        setup(42, "SandRTI_W", false);
        setup(43, "SandLTI_W", false);
        setup(44, "SandRBI_W", false);
        setup(45, "SandLBI_W", false);
        setup(46, "SandRTO_W", false);
        setup(47, "SandLTO_W", false);
        setup(48, "SandRBO_W", false);
        setup(49, "SandLBO_W", false);

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

        setup(62, "BridgeT", false);
        setup(63, "BridgeB", false);
        setup(64, "BridgeL", false);
        setup(65, "BridgeR", false);
        setup(66, "BridgeS_NS", false);
        setup(67, "BridgeS_WE", false);

        setup(68, "DirtS", false);
        setup(69, "DirtA", false);

        setup(70, "WaterS", true);
        setup(71, "WaterA", true);

        setup(72, "WallS", true);

        setup(73, "Tree1", true);
        setup(74, "Tree2", true);

        setup(75, "minimaps/Void", false);
        setup(76, "minimaps/CeilingRBO", true);
        setup(77, "minimaps/CeilingLBO", true);
        setup(78, "minimaps/CeilingLTO", true);
        setup(79, "minimaps/CeilingRTO", true);
        setup(80, "minimaps/CeilingLBI", true);
        setup(81, "minimaps/CeilingRBI", true);
        setup(82, "minimaps/CeilingLTI", true);
        setup(83, "minimaps/CeilingRTI", true);
        setup(84, "minimaps/CeilingL1", true);
        setup(85, "minimaps/CeilingL2", true);
        setup(86, "minimaps/CeilingR1", true);
        setup(87, "minimaps/CeilingR2", true);
        setup(88, "minimaps/CeilingB1", true);
        setup(89, "minimaps/CeilingB2", true);
        setup(90, "minimaps/CeilingT1", true);
        setup(91, "minimaps/CeilingT2", true);
        setup(92, "minimaps/WT1", true);
        setup(93, "minimaps/WT2", true);
        setup(94, "minimaps/WB1", true);
        setup(95, "minimaps/WB2", true);
        setup(96, "minimaps/FTE1", false);
        setup(97, "minimaps/FTE2", false);
        setup(98, "minimaps/FBE1", false);
        setup(99, "minimaps/FBE2", false);
        setup(100, "minimaps/FLE1", false);
        setup(101, "minimaps/FLE2", false);
        setup(102, "minimaps/FRE1", false);
        setup(103, "minimaps/FRE2", false);
        setup(104, "minimaps/FS", false);
        setup(105, "minimaps/SW1", true);
        setup(106, "minimaps/SW2", true);
        setup(107, "minimaps/SW3", true);

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
            System.out.println(e);
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

            // Stop moving the camera the edge
            if (gp.player.screenX > gp.player.worldX){
                screenX = worldX;
            }
            if (gp.player.screenY > gp.player.worldY){
                screenY = worldY;
            }
            int rightOffset = gp.screenWidth - gp.player.screenX;
            if(rightOffset > gp.worldWidth - gp.player.worldX){
                screenX = gp.screenWidth - (gp.worldWidth - worldX);
            }
            int bottomOffset = gp.screenHeight - gp.player.screenY;
            if(bottomOffset > gp.worldHeight - gp.player.worldY){
                screenY = gp.screenHeight - (gp.worldHeight - worldY);
            }

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY,null);
            }
            else if(gp.player.screenX > gp.player.worldX ||
                    gp.player.screenY > gp.player.worldY ||
                    rightOffset > gp.worldWidth - gp.player.worldX ||
                    bottomOffset > gp.worldHeight - gp.player.worldY) {
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
