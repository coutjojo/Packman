package Tiles;

import ImageLoad.Assets;

public class UpperWall extends Tile{
    public UpperWall(int id) {
        super(id, Assets.upperWall);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}

