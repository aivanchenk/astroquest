package com.mygdx.game.world;

import java.util.HashMap;

public enum TileType {


    GRASS(1,true, "Grass"),
    DIRT(2,true, "Dirt"),
    SKY(3,false, "Sky"),
    LAVA(4,false, "Lava"),
    CLOUD(5,false, "Cloud"),
    STONE(6,true, "Stone"),

    //RESOURCES
    RESIN_RAW(7, true, "Raw Resin"),
    RESIN(8, false, "Resin");

    public static final int TILE_SIZE = 16;

    private int id;
    private boolean collidable;
    private String name;


    private TileType(int id, boolean collidable, String name){
        this.id = id;
        this.collidable = collidable;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public boolean getCollidable(){
        return collidable;
    }

    public String getName(){
        return name;
    }

    public boolean isCollidable() {
        return collidable;
    }

    private static HashMap<Integer, TileType> tileMap;

    static{
        tileMap = new HashMap<>();
        for(TileType tileType : TileType.values()){
            tileMap.put(tileType.getId(),tileType);
        }
    }

    public static TileType getTileTypeById(int id){
        return tileMap.get(id);
    }
}
