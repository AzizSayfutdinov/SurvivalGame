package dev.Aziz.tilegame.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

//Contains any images, sounds, music, ect.
public class Assets {

    private static final int width = 32,height = 32;
    private static final int enemyWidth = 64, enemyHeight = 64;     //TODO: change name
    private static final int worldTileWidth = 16, worldTileHeight = 16;

    public static Font font28;  //size = 28;

    public static BufferedImage dirt, grass, stone, tree, rock, wood;
    public static BufferedImage inventoryScreen;
    public static BufferedImage[] player_down, player_up, player_right, player_left;
    public static BufferedImage[] player_down_attacking, player_up_attacking, player_right_attacking, player_left_attacking;
    public static BufferedImage[] player_pants_down, player_pants_up, player_pants_right, player_pants_left;
    public static BufferedImage[] player_sword_down, player_sword_up, player_sword_right, player_sword_left;
    public static BufferedImage[] enemy_down, enemy_up, enemy_left, enemy_right;
    public static BufferedImage[] btn_start;

    public static BufferedImage[] worldTiles;

    private static SpriteSheet playerSheet;
    private static SpriteSheet playerPantsSheet;
    private static SpriteSheet playerSwordSheet;
    private static SpriteSheet playerAttackSheet;
    private static SpriteSheet sheet;
    private static SpriteSheet sheet3;
    private static SpriteSheet enemySheet;
    private static SpriteSheet worldSheet;

    public static void init(){

        font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);

        sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        sheet3 = new SpriteSheet(ImageLoader.loadImage("/textures/sheet3.png"));
        playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/BODY_Male.png"));
        playerPantsSheet = new SpriteSheet(ImageLoader.loadImage("/textures/LEGS_pants_greenish.png"));
        playerSwordSheet = new SpriteSheet(ImageLoader.loadImage("/textures/WEAPON_dagger.png"));
        playerAttackSheet = new SpriteSheet(ImageLoader.loadImage("/textures/BODY_attacking.png"));
        enemySheet = new SpriteSheet(ImageLoader.loadImage("/textures/BODY_skeleton.png"));

        worldSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Overworld.png"));

        inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");


        enemy_up = new BufferedImage[9];
        enemy_down = new BufferedImage[9];
        enemy_left = new BufferedImage[9];
        enemy_right = new BufferedImage[9];

        player_down = new BufferedImage[9];
        player_up = new BufferedImage[9];
        player_right = new BufferedImage[9];
        player_left = new BufferedImage[9];

        player_down_attacking = new BufferedImage[6];
        player_up_attacking = new BufferedImage[6];
        player_right_attacking = new BufferedImage[6];
        player_left_attacking = new BufferedImage[6];

        player_pants_down = new BufferedImage[9];
        player_pants_up = new BufferedImage[9];
        player_pants_right = new BufferedImage[9];
        player_pants_left = new BufferedImage[9];

        player_sword_down = new BufferedImage[6];
        player_sword_up = new BufferedImage[6];
        player_sword_right = new BufferedImage[6];
        player_sword_left = new BufferedImage[6];

        btn_start = new BufferedImage[2];

        btn_start[0] = sheet3.crop(width * 6, height * 4, width * 2, height);
        btn_start[1] = sheet3.crop(width * 6, height * 5, width * 2, height);

        worldTiles = new BufferedImage[1440];


        //Player - Body

        loadPlayer();
        loadPlayerAttacking();
        loadPlayerPants();
        loadPlayerSword();

        // Enemy - Skeleton

        loadSkeleton();

        // World Tiles

        loadWorldTiles();



        grass = sheet.crop(width, 0, width, height);
        dirt = sheet.crop(2 * width, 0, width, height);
        stone = sheet.crop(3 * width, 0, width, height);
        tree = sheet.crop(0, height, width, height);
        rock = sheet3.crop(0,2 * width, width, height);
        wood = sheet3.crop(width,height, width, height);


    }

    private static void loadWorldTiles(){

        int i = 0;

        while(i < 1440) {
            for (int y = 0; y < 36; y++) {
                for (int x = 0; x < 40; x++) {
                    if (y == 0) {
                        if (x == 0) {
                            worldTiles[i] = worldSheet.crop(x * worldTileWidth, y * worldTileHeight, worldTileWidth, worldTileHeight);
                            i++;
                        } else {
                            worldTiles[i] = worldSheet.crop(x * worldTileWidth - 1, y * worldTileHeight, worldTileWidth, worldTileHeight);
                            i++;
                        }
                    } else {
                        if(x == 0){
                            worldTiles[i] = worldSheet.crop(x * worldTileWidth, y * worldTileHeight - 1, worldTileWidth, worldTileHeight);
                            i++;
                        } else {
                            worldTiles[i] = worldSheet.crop(x * worldTileWidth - 1, y * worldTileHeight - 1, worldTileWidth, worldTileHeight);
                            i++;
                        }
                    }
                }
            }
        }

    }

    //private static void loadFromSheet(BufferedImage[] image, SpriteSheet sheet, int xAmount, int yAmount){        //TODO: implement method to ease loading images
//
    //    for(int i = 0; i < 9; i++) {
    //        if(i == 0)
    //            image[i] = sheet.crop(xAmount * enemyWidth, 0, enemyWidth, enemyHeight);
    //        else
    //            image[i] = sheet.crop(i * enemyWidth - 1, 0, enemyWidth, enemyHeight);
    //    }
    //just copy the whole thing
    //
    //}


    private static void loadPlayer(){

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                player_up[i] = playerSheet.crop(i * enemyWidth, 0, enemyWidth, enemyHeight);
            else
                player_up[i] = playerSheet.crop(i * enemyWidth - 1, 0, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                player_left[i] = playerSheet.crop(i * enemyWidth, enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_left[i] = playerSheet.crop(i * enemyWidth - 1, enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                player_down[i] = playerSheet.crop(i * enemyWidth, 2 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_down[i] = playerSheet.crop(i * enemyWidth - 1, 2 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                player_right[i] = playerSheet.crop(i * enemyWidth, 3 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_right[i] = playerSheet.crop(i * enemyWidth - 1, 3 * enemyHeight - 1, enemyWidth, enemyHeight);
        }
    }

    private static void loadPlayerAttacking(){

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_up_attacking[i] = playerAttackSheet.crop(i * enemyWidth, 0, enemyWidth, enemyHeight);
            else
                player_up_attacking[i] = playerAttackSheet.crop(i * enemyWidth - 1, 0, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_left_attacking[i] = playerAttackSheet.crop(i * enemyWidth, enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_left_attacking[i] = playerAttackSheet.crop(i * enemyWidth - 1, enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_down_attacking[i] = playerAttackSheet.crop(i * enemyWidth, 2 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_down_attacking[i] = playerAttackSheet.crop(i * enemyWidth - 1, 2 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_right_attacking[i] = playerAttackSheet.crop(i * enemyWidth, 3 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_right_attacking[i] = playerAttackSheet.crop(i * enemyWidth - 1, 3 * enemyHeight - 1, enemyWidth, enemyHeight);
        }
    }

    private static void loadPlayerPants(){

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                player_pants_up[i] = playerPantsSheet.crop(i * enemyWidth, 0, enemyWidth, enemyHeight);
            else
                player_pants_up[i] = playerPantsSheet.crop(i * enemyWidth - 1, 0, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                player_pants_left[i] = playerPantsSheet.crop(i * enemyWidth, enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_pants_left[i] = playerPantsSheet.crop(i * enemyWidth - 1, enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                player_pants_down[i] = playerPantsSheet.crop(i * enemyWidth, 2 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_pants_down[i] = playerPantsSheet.crop(i * enemyWidth - 1, 2 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                player_pants_right[i] = playerPantsSheet.crop(i * enemyWidth, 3 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_pants_right[i] = playerPantsSheet.crop(i * enemyWidth - 1, 3 * enemyHeight - 1, enemyWidth, enemyHeight);
        }
    }

    private static void loadPlayerSword(){

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_sword_up[i] = playerSwordSheet.crop(i * enemyWidth, 0, enemyWidth, enemyHeight);
            else
                player_sword_up[i] = playerSwordSheet.crop(i * enemyWidth - 1, 0, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_sword_left[i] = playerSwordSheet.crop(i * enemyWidth, enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_sword_left[i] = playerSwordSheet.crop(i * enemyWidth - 1, enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_sword_down[i] = playerSwordSheet.crop(i * enemyWidth, 2 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_sword_down[i] = playerSwordSheet.crop(i * enemyWidth - 1, 2 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_sword_right[i] = playerSwordSheet.crop(i * enemyWidth, 3 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_sword_right[i] = playerSwordSheet.crop(i * enemyWidth - 1, 3 * enemyHeight - 1, enemyWidth, enemyHeight);
        }
    }

    private static void loadSkeleton(){

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                enemy_up[i] = enemySheet.crop(i * enemyWidth, 0, enemyWidth, enemyHeight);
            else
                enemy_up[i] = enemySheet.crop(i * enemyWidth - 1, 0, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                enemy_left[i] = enemySheet.crop(i * enemyWidth, enemyHeight - 1, enemyWidth, enemyHeight);
            else
                enemy_left[i] = enemySheet.crop(i * enemyWidth - 1, enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                enemy_down[i] = enemySheet.crop(i * enemyWidth, 2 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                enemy_down[i] = enemySheet.crop(i * enemyWidth - 1, 2 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                enemy_right[i] = enemySheet.crop(i * enemyWidth, 3 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                enemy_right[i] = enemySheet.crop(i * enemyWidth - 1, 3 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

    }

}
