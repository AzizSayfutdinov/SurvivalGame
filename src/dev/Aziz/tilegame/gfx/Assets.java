package dev.Aziz.tilegame.gfx;

import dev.Aziz.tilegame.sounds.SoundLoader;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;

//Contains any images, sounds, music, ect.
public class Assets {

    private static final int width = 32,height = 32;
    private static final int enemyWidth = 64, enemyHeight = 64;     //TODO: change name
    private static final int worldTileWidth = 16, worldTileHeight = 16;

    public static Clip testSound;
    public static Clip backGroundMusic;

    public static Font font28;  //size = 28;
    public static Font font22;
    public static Font font50;

    public static BufferedImage dirt, grass, stone, tree1, tree2, rock, wood;
    public static BufferedImage house;
    public static BufferedImage inventoryScreen;
    public static BufferedImage[] player_down, player_up, player_right, player_left;
    public static BufferedImage[] player_down_attacking, player_up_attacking, player_right_attacking, player_left_attacking;
    public static BufferedImage[] player_pants_down, player_pants_up, player_pants_right, player_pants_left;
    public static BufferedImage[] player_sword_down, player_sword_up, player_sword_right, player_sword_left;
    public static BufferedImage[] skeleton_down, skeleton_up, skeleton_left, skeleton_right;
    public static BufferedImage[] skeleton_down_attacking, skeleton_up_attacking, skeleton_left_attacking, skeleton_right_attacking;
    public static BufferedImage[] orc_down, orc_up, orc_left, orc_right;
    public static BufferedImage[] orc_down_attacking, orc_up_attacking, orc_left_attacking, orc_right_attacking;
    public static BufferedImage[] btn_start;
    public static BufferedImage[] btn_options;
    public static BufferedImage[] btn_credits;
    public static BufferedImage[] btn_exit;

    public static BufferedImage[] worldTiles;

    private static SpriteSheet playerSheet;
    private static SpriteSheet playerSwordSheet;
    private static SpriteSheet playerSwordAttackSheet;
    private static SpriteSheet sheet;
    private static SpriteSheet sheet3;
    private static SpriteSheet skeletonSheet;
    private static SpriteSheet orcSheet;
    private static SpriteSheet worldSheet;
    private static SpriteSheet treeSheeet;
    private static SpriteSheet menuSheet1;
    private static SpriteSheet menuSheet2;

    public static void init(){

        testSound = SoundLoader.loadSound("/sounds/stepdirt_1.wav");
        backGroundMusic = SoundLoader.loadSound("/sounds/Heroic Demise.wav");

        font50 = FontLoader.loadFont("res/fonts/slkscr.ttf", 50);
        font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
        font22 = FontLoader.loadFont("res/fonts/slkscr.ttf", 22);

        //TODO: Check if you have to adjust the paths to load the jar file successfully
        sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        sheet3 = new SpriteSheet(ImageLoader.loadImage("/textures/sheet3.png"));

        menuSheet1 = new SpriteSheet(ImageLoader.loadImage("/textures/GUI1.png"));
        menuSheet2 = new SpriteSheet(ImageLoader.loadImage("/textures/GUI2.png"));

        playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player_dagger.png"));
        playerSwordSheet = new SpriteSheet(ImageLoader.loadImage("/textures/WEAPON_dagger.png"));
        playerSwordAttackSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player_sword.png"));
        skeletonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/skeleton_dagger.png"));
        orcSheet = new SpriteSheet(ImageLoader.loadImage("/textures/orc.png"));

        worldSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Overworld.png"));

        treeSheeet = new SpriteSheet(ImageLoader.loadImage("/textures/trees-green.png"));

        inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");


        skeleton_up = new BufferedImage[9];
        skeleton_down = new BufferedImage[9];
        skeleton_left = new BufferedImage[9];
        skeleton_right = new BufferedImage[9];

        skeleton_up_attacking = new BufferedImage[6];
        skeleton_down_attacking = new BufferedImage[6];
        skeleton_left_attacking = new BufferedImage[6];
        skeleton_right_attacking = new BufferedImage[6];

        orc_up = new BufferedImage[9];
        orc_down = new BufferedImage[9];
        orc_left = new BufferedImage[9];
        orc_right = new BufferedImage[9];

        orc_up_attacking = new BufferedImage[8];
        orc_down_attacking = new BufferedImage[8];
        orc_left_attacking = new BufferedImage[8];
        orc_right_attacking = new BufferedImage[8];

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
        btn_options = new BufferedImage[2];
        btn_credits = new BufferedImage[2];
        btn_exit = new BufferedImage[2];

        btn_start[0] = menuSheet1.crop(454, 194, 462, 88);
        btn_start[1] = menuSheet2.crop(454, 194, 462, 88);

        btn_options[0] = menuSheet1.crop(454, 194 + 88, 462, 88);
        btn_options[1] = menuSheet2.crop(454, 194 + 88, 462, 88);

        btn_credits[0] = menuSheet1.crop(454, 194 + 88 * 2, 462, 88);
        btn_credits[1] = menuSheet2.crop(454, 194 + 88 * 2, 462, 88);

        btn_exit[0] = menuSheet1.crop(454, 194 + 88 * 3, 462, 88);
        btn_exit[1] = menuSheet2.crop(454, 194 + 88 * 3, 462, 88);

        worldTiles = new BufferedImage[1440];


        //Player - Body

        loadPlayer();
        loadPlayerAttackingDagger();

        // Enemy - Skeleton

        loadSkeleton();
        loadSkeletonAttacking();

        loadOrc();
        loadOrcAttacking();

        // World Tiles

        loadWorldTiles();



        grass = sheet.crop(width, 0, width, height);
        dirt = sheet.crop(2 * width, 0, width, height);
        stone = sheet.crop(3 * width, 0, width, height);
        rock = sheet3.crop(0,2 * width, width, height);
        wood = sheet3.crop(width,height, width, height);

        house = worldSheet.crop(6 * worldTileWidth , 0, 5 * worldTileWidth, 5 * worldTileHeight);
        tree1 = treeSheeet.crop(296, 512, 180, 195);
        tree2 = treeSheeet.crop(671, 512, 163, 195);


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
                            worldTiles[i] = worldSheet.crop(x * worldTileWidth, y * worldTileHeight, worldTileWidth, worldTileHeight);
                            i++;
                        }
                    } else {
                        if(x == 0){
                            worldTiles[i] = worldSheet.crop(x * worldTileWidth, y * worldTileHeight, worldTileWidth, worldTileHeight);
                            i++;
                        } else {
                            worldTiles[i] = worldSheet.crop(x * worldTileWidth, y * worldTileHeight, worldTileWidth, worldTileHeight);
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
                player_up[i] = playerSheet.crop(i * enemyWidth, 8 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_up[i] = playerSheet.crop(i * enemyWidth - 1, 8 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                player_left[i] = playerSheet.crop(i * enemyWidth, 9 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_left[i] = playerSheet.crop(i * enemyWidth - 1, 9 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                player_down[i] = playerSheet.crop(i * enemyWidth, 10 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_down[i] = playerSheet.crop(i * enemyWidth - 1, 10 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                player_right[i] = playerSheet.crop(i * enemyWidth, 11 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_right[i] = playerSheet.crop(i * enemyWidth - 1, 11 * enemyHeight - 1, enemyWidth, enemyHeight);
        }
    }

    private static void loadPlayerAttackingDagger(){

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_up_attacking[i] = playerSheet.crop(i * enemyWidth, 12 * enemyHeight - 1, enemyWidth, enemyHeight);      //TODO: change to a general variable: enemyWidth
            else
                player_up_attacking[i] = playerSheet.crop(i * enemyWidth - 1, 12 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_left_attacking[i] = playerSheet.crop(i * enemyWidth, 13 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_left_attacking[i] = playerSheet.crop(i * enemyWidth - 1, 13 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_down_attacking[i] = playerSheet.crop(i * enemyWidth, 14 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_down_attacking[i] = playerSheet.crop(i * enemyWidth - 1, 14 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_right_attacking[i] = playerSheet.crop(i * enemyWidth, 15 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                player_right_attacking[i] = playerSheet.crop(i * enemyWidth - 1, 15 * enemyHeight - 1, enemyWidth, enemyHeight);
        }
    }

    private static void loadPlayerAttackingSword(){     //TODO: Finish loading animation

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_up_attacking[i] = playerSwordAttackSheet.crop(0, 0, enemyWidth * 3, enemyHeight * 3);
            else
                player_up_attacking[i] = playerSwordAttackSheet.crop(i * 64*3 - 1, 0, enemyWidth * 3, enemyHeight * 3);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_left_attacking[i] = playerSwordAttackSheet.crop(i * enemyWidth, enemyHeight - 1, enemyWidth * 3, enemyHeight * 3);
            else
                player_left_attacking[i] = playerSwordAttackSheet.crop(i * enemyWidth - 1, enemyHeight - 1, enemyWidth * 3, enemyHeight * 3);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_down_attacking[i] = playerSwordAttackSheet.crop(i * enemyWidth, 2 * enemyHeight - 1, enemyWidth * 3, enemyHeight * 3);
            else
                player_down_attacking[i] = playerSwordAttackSheet.crop(i * enemyWidth - 1, 2 * enemyHeight - 1, enemyWidth * 3, enemyHeight * 3);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_right_attacking[i] = playerSwordAttackSheet.crop(i * enemyWidth, 3 * enemyHeight - 1, enemyWidth * 3, enemyHeight * 3);
            else
                player_right_attacking[i] = playerSwordAttackSheet.crop(i * enemyWidth - 1, 3 * enemyHeight - 1, enemyWidth * 3, enemyHeight * 3);
        }
    }


    private static void loadSkeleton(){

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                skeleton_up[i] = skeletonSheet.crop(i * enemyWidth, 8 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                skeleton_up[i] = skeletonSheet.crop(i * enemyWidth - 1, 8 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                skeleton_left[i] = skeletonSheet.crop(i * enemyWidth, 9 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                skeleton_left[i] = skeletonSheet.crop(i * enemyWidth - 1, 9 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                skeleton_down[i] = skeletonSheet.crop(i * enemyWidth, 10 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                skeleton_down[i] = skeletonSheet.crop(i * enemyWidth - 1, 10 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                skeleton_right[i] = skeletonSheet.crop(i * enemyWidth, 11 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                skeleton_right[i] = skeletonSheet.crop(i * enemyWidth - 1, 11 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

    }

    private static void loadSkeletonAttacking(){

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                skeleton_up_attacking[i] = skeletonSheet.crop(i * enemyWidth, 12 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                skeleton_up_attacking[i] = skeletonSheet.crop(i * enemyWidth - 1, 12 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                skeleton_left_attacking[i] = skeletonSheet.crop(i * enemyWidth, 13 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                skeleton_left_attacking[i] = skeletonSheet.crop(i * enemyWidth - 1, 13 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                skeleton_down_attacking[i] = skeletonSheet.crop(i * enemyWidth, 14 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                skeleton_down_attacking[i] = skeletonSheet.crop(i * enemyWidth - 1, 14 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                skeleton_right_attacking[i] = skeletonSheet.crop(i * enemyWidth, 15 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                skeleton_right_attacking[i] = skeletonSheet.crop(i * enemyWidth - 1, 15 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

    }


    private static void loadOrc(){

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                orc_up[i] = orcSheet.crop(i * enemyWidth, 8 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                orc_up[i] = orcSheet.crop(i * enemyWidth - 1, 8 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                orc_left[i] = orcSheet.crop(i * enemyWidth, 9 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                orc_left[i] = orcSheet.crop(i * enemyWidth - 1, 9 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                orc_down[i] = orcSheet.crop(i * enemyWidth, 10 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                orc_down[i] = orcSheet.crop(i * enemyWidth - 1, 10 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                orc_right[i] = orcSheet.crop(i * enemyWidth, 11 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                orc_right[i] = orcSheet.crop(i * enemyWidth - 1, 11 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

    }

    //TODO: mention helper methods in the documentation

    private static void loadOrcAttacking(){

        for(int i = 0; i < 8; i++) {
            if(i == 0)
                orc_up_attacking[i] = orcSheet.crop(i * enemyWidth, 4 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                orc_up_attacking[i] = orcSheet.crop(i * enemyWidth - 1, 4 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 8; i++) {
            if(i == 0)
                orc_left_attacking[i] = orcSheet.crop(i * enemyWidth, 5 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                orc_left_attacking[i] = orcSheet.crop(i * enemyWidth - 1, 5 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 8; i++) {
            if(i == 0)
                orc_down_attacking[i] = orcSheet.crop(i * enemyWidth, 6 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                orc_down_attacking[i] = orcSheet.crop(i * enemyWidth - 1, 6 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

        for(int i = 0; i < 8; i++) {
            if(i == 0)
                orc_right_attacking[i] = orcSheet.crop(i * enemyWidth, 7 * enemyHeight - 1, enemyWidth, enemyHeight);
            else
                orc_right_attacking[i] = orcSheet.crop(i * enemyWidth - 1, 7 * enemyHeight - 1, enemyWidth, enemyHeight);
        }

    }

}
