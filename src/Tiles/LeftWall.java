package Tiles;

import ImageLoad.Assets;

public class LeftWall extends Tile{
    public LeftWall(int id) {
        super(id, Assets.leftWall);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
