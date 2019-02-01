package dev.Aziz.tilegame.gfx;

import dev.Aziz.tilegame.sounds.SoundLoader;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;

//Contains any images, sounds, music, ect.
public class Assets {

    private static final int width = 64, height = 64;
    private static final int worldTileWidth = 16, worldTileHeight = 16;

    public static Clip walkingSound;
    public static Clip testSound;
    public static Clip backGroundMusic;
    public static Clip attackingSwordSound;
    public static Clip fireBallSound;

    public static Font font28;  //size = 28;
    public static Font font22;
    public static Font font50;
    public static Font font100;

    public static BufferedImage house, wood, tree1, tree2;
    public static BufferedImage inventoryScreen;
    public static BufferedImage btn_restartImage;
    public static BufferedImage[] player_down, player_up, player_right, player_left;
    public static BufferedImage[] player_down_attacking, player_up_attacking, player_right_attacking, player_left_attacking;
    public static BufferedImage[] skeleton_down, skeleton_up, skeleton_left, skeleton_right;
    public static BufferedImage[] skeleton_down_attacking, skeleton_up_attacking, skeleton_left_attacking, skeleton_right_attacking;
    public static BufferedImage[] orc_down, orc_up, orc_left, orc_right;
    public static BufferedImage[] orc_down_attacking, orc_up_attacking, orc_left_attacking, orc_right_attacking;
    public static BufferedImage[] fireball_down, fireball_up, fireball_right, fireball_left;
    public static BufferedImage[] btn_start;
    public static BufferedImage[] btn_options;
    public static BufferedImage[] btn_credits;
    public static BufferedImage[] btn_exit;
    public static BufferedImage[] btn_restart;

    public static BufferedImage[] worldTiles;

    private static SpriteSheet playerSheet;
    private static SpriteSheet playerSwordSheet;
    private static SpriteSheet playerSwordAttackSheet;
    private static SpriteSheet skeletonSheet;
    private static SpriteSheet orcSheet;
    private static SpriteSheet worldSheet;
    private static SpriteSheet sheet3;
    private static SpriteSheet treeSheeet;
    private static SpriteSheet menuSheet1;
    private static SpriteSheet menuSheet2;
    private static SpriteSheet btnRestartSheet;

    public static void init(){

        walkingSound = SoundLoader.loadSound("/sounds/sfx_step_grass.wav");
        attackingSwordSound = SoundLoader.loadSound("/sounds/sword_hit.wav");
        testSound = SoundLoader.loadSound("/sounds/stepdirt_1.wav");
        backGroundMusic = SoundLoader.loadSound("/sounds/Heroic Demise.wav");
        fireBallSound = SoundLoader.loadSound("/sounds/fireball.wav");

        font100 = FontLoader.loadFont("res/fonts/slkscr.ttf", 100);
        font50 = FontLoader.loadFont("res/fonts/slkscr.ttf", 50);
        font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
        font22 = FontLoader.loadFont("res/fonts/slkscr.ttf", 22);

        //TODO: Check if you have to adjust the paths to load the jar file successfully

        menuSheet1 = new SpriteSheet(ImageLoader.loadImage("/textures/GUI1.png"));
        menuSheet2 = new SpriteSheet(ImageLoader.loadImage("/textures/GUI2.png"));

        sheet3 = new SpriteSheet(ImageLoader.loadImage("/textures/sheet3.png"));

        playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player_dagger.png"));
        //playerSwordSheet = new SpriteSheet(ImageLoader.loadImage("/textures/WEAPON_dagger.png"));
        playerSwordAttackSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player_sword.png"));
        skeletonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/skeleton_dagger.png"));
        orcSheet = new SpriteSheet(ImageLoader.loadImage("/textures/orc.png"));

        worldSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Overworld.png"));

        treeSheeet = new SpriteSheet(ImageLoader.loadImage("/textures/trees-green.png"));

        inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");

        btnRestartSheet = new SpriteSheet(ImageLoader.loadImage("/textures/restart.png"));


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

        btn_start = new BufferedImage[2];
        btn_options = new BufferedImage[2];
        btn_credits = new BufferedImage[2];
        btn_exit = new BufferedImage[2];
        btn_restart = new BufferedImage[1];

        fireball_down = new BufferedImage[6];
        fireball_up = new BufferedImage[6];
        fireball_right = new BufferedImage[6];
        fireball_left = new BufferedImage[6];

        btn_start[0] = menuSheet1.crop(454, 194, 462, 88);
        btn_start[1] = menuSheet2.crop(454, 194, 462, 88);

        btn_restart[0] = btnRestartSheet.crop(0,0,384, 384);

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

        loadFireBalls();


        house = worldSheet.crop(6 * worldTileWidth , 0, 5 * worldTileWidth, 5 * worldTileHeight);
        tree1 = treeSheeet.crop(296, 512, 180, 195);
        tree2 = treeSheeet.crop(671, 512, 163, 195);
        wood = sheet3.crop(width / 2,height / 2, width / 2, height / 2);


    }

    private static void loadFireBalls(){

        fireball_down[0] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_01_down.png");
        fireball_down[1] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_02_down.png");
        fireball_down[2] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_03_down.png");
        fireball_down[3] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_04_down.png");
        fireball_down[4] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_05_down.png");
        fireball_down[5] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_06_down.png");

        fireball_up[0] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_01_up.png");
        fireball_up[1] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_02_up.png");
        fireball_up[2] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_03_up.png");
        fireball_up[3] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_04_up.png");
        fireball_up[4] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_05_up.png");
        fireball_up[5] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_06_up.png");

        fireball_right[0] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_01_right.png");
        fireball_right[1] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_02_right.png");
        fireball_right[2] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_03_right.png");
        fireball_right[3] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_04_right.png");
        fireball_right[4] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_05_right.png");
        fireball_right[5] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_06_right.png");

        fireball_left[0] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_01_left.png");
        fireball_left[1] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_02_left.png");
        fireball_left[2] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_03_left.png");
        fireball_left[3] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_04_left.png");
        fireball_left[4] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_05_left.png");
        fireball_left[5] = ImageLoader.loadImage("/textures/fireball/Fireball_Effect_06_left.png");


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
                player_up[i] = playerSheet.crop(i * width, 8 * height - 1, width, height);
            else
                player_up[i] = playerSheet.crop(i * width - 1, 8 * height - 1, width, height);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                player_left[i] = playerSheet.crop(i * width, 9 * height - 1, width, height);
            else
                player_left[i] = playerSheet.crop(i * width - 1, 9 * height - 1, width, height);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                player_down[i] = playerSheet.crop(i * width, 10 * height - 1, width, height);
            else
                player_down[i] = playerSheet.crop(i * width - 1, 10 * height - 1, width, height);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                player_right[i] = playerSheet.crop(i * width, 11 * height - 1, width, height);
            else
                player_right[i] = playerSheet.crop(i * width - 1, 11 * height - 1, width, height);
        }
    }

    private static void loadPlayerAttackingDagger(){

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_up_attacking[i] = playerSheet.crop(i * width, 12 * height - 1, width, height);      //TODO: change to a general variable: enemyWidth
            else
                player_up_attacking[i] = playerSheet.crop(i * width - 1, 12 * height - 1, width, height);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_left_attacking[i] = playerSheet.crop(i * width, 13 * height - 1, width, height);
            else
                player_left_attacking[i] = playerSheet.crop(i * width - 1, 13 * height - 1, width, height);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_down_attacking[i] = playerSheet.crop(i * width, 14 * height - 1, width, height);
            else
                player_down_attacking[i] = playerSheet.crop(i * width - 1, 14 * height - 1, width, height);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_right_attacking[i] = playerSheet.crop(i * width, 15 * height - 1, width, height);
            else
                player_right_attacking[i] = playerSheet.crop(i * width - 1, 15 * height - 1, width, height);
        }
    }

    private static void loadPlayerAttackingSword(){     //TODO: Finish loading animation

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_up_attacking[i] = playerSwordAttackSheet.crop(0, 0, width * 3, height * 3);
            else
                player_up_attacking[i] = playerSwordAttackSheet.crop(i * 64*3 - 1, 0, width * 3, height * 3);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_left_attacking[i] = playerSwordAttackSheet.crop(i * width, height - 1, width * 3, height * 3);
            else
                player_left_attacking[i] = playerSwordAttackSheet.crop(i * width - 1, height - 1, width * 3, height * 3);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_down_attacking[i] = playerSwordAttackSheet.crop(i * width, 2 * height - 1, width * 3, height * 3);
            else
                player_down_attacking[i] = playerSwordAttackSheet.crop(i * width - 1, 2 * height - 1, width * 3, height * 3);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                player_right_attacking[i] = playerSwordAttackSheet.crop(i * width, 3 * height - 1, width * 3, height * 3);
            else
                player_right_attacking[i] = playerSwordAttackSheet.crop(i * width - 1, 3 * height - 1, width * 3, height * 3);
        }
    }


    private static void loadSkeleton(){

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                skeleton_up[i] = skeletonSheet.crop(i * width, 8 * height - 1, width, height);
            else
                skeleton_up[i] = skeletonSheet.crop(i * width - 1, 8 * height - 1, width, height);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                skeleton_left[i] = skeletonSheet.crop(i * width, 9 * height - 1, width, height);
            else
                skeleton_left[i] = skeletonSheet.crop(i * width - 1, 9 * height - 1, width, height);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                skeleton_down[i] = skeletonSheet.crop(i * width, 10 * height - 1, width, height);
            else
                skeleton_down[i] = skeletonSheet.crop(i * width - 1, 10 * height - 1, width, height);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                skeleton_right[i] = skeletonSheet.crop(i * width, 11 * height - 1, width, height);
            else
                skeleton_right[i] = skeletonSheet.crop(i * width - 1, 11 * height - 1, width, height);
        }

    }

    private static void loadSkeletonAttacking(){

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                skeleton_up_attacking[i] = skeletonSheet.crop(i * width, 12 * height - 1, width, height);
            else
                skeleton_up_attacking[i] = skeletonSheet.crop(i * width - 1, 12 * height - 1, width, height);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                skeleton_left_attacking[i] = skeletonSheet.crop(i * width, 13 * height - 1, width, height);
            else
                skeleton_left_attacking[i] = skeletonSheet.crop(i * width - 1, 13 * height - 1, width, height);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                skeleton_down_attacking[i] = skeletonSheet.crop(i * width, 14 * height - 1, width, height);
            else
                skeleton_down_attacking[i] = skeletonSheet.crop(i * width - 1, 14 * height - 1, width, height);
        }

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                skeleton_right_attacking[i] = skeletonSheet.crop(i * width, 15 * height - 1, width, height);
            else
                skeleton_right_attacking[i] = skeletonSheet.crop(i * width - 1, 15 * height - 1, width, height);
        }

    }


    private static void loadOrc(){

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                orc_up[i] = orcSheet.crop(i * width, 8 * height - 1, width, height);
            else
                orc_up[i] = orcSheet.crop(i * width - 1, 8 * height - 1, width, height);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                orc_left[i] = orcSheet.crop(i * width, 9 * height - 1, width, height);
            else
                orc_left[i] = orcSheet.crop(i * width - 1, 9 * height - 1, width, height);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                orc_down[i] = orcSheet.crop(i * width, 10 * height - 1, width, height);
            else
                orc_down[i] = orcSheet.crop(i * width - 1, 10 * height - 1, width, height);
        }

        for(int i = 0; i < 9; i++) {
            if(i == 0)
                orc_right[i] = orcSheet.crop(i * width, 11 * height - 1, width, height);
            else
                orc_right[i] = orcSheet.crop(i * width - 1, 11 * height - 1, width, height);
        }

    }


    private static void loadOrcAttacking(){

        for(int i = 0; i < 8; i++) {
            if(i == 0)
                orc_up_attacking[i] = orcSheet.crop(i * width, 4 * height - 1, width, height);
            else
                orc_up_attacking[i] = orcSheet.crop(i * width - 1, 4 * height - 1, width, height);
        }

        for(int i = 0; i < 8; i++) {
            if(i == 0)
                orc_left_attacking[i] = orcSheet.crop(i * width, 5 * height - 1, width, height);
            else
                orc_left_attacking[i] = orcSheet.crop(i * width - 1, 5 * height - 1, width, height);
        }

        for(int i = 0; i < 8; i++) {
            if(i == 0)
                orc_down_attacking[i] = orcSheet.crop(i * width, 6 * height - 1, width, height);
            else
                orc_down_attacking[i] = orcSheet.crop(i * width - 1, 6 * height - 1, width, height);
        }

        for(int i = 0; i < 8; i++) {
            if(i == 0)
                orc_right_attacking[i] = orcSheet.crop(i * width, 7 * height - 1, width, height);
            else
                orc_right_attacking[i] = orcSheet.crop(i * width - 1, 7 * height - 1, width, height);
        }

    }

}
