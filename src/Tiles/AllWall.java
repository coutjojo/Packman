package Tiles;

import ImageLoad.Assets;

public class AllWall extends Tile{
    public AllWall(int id) {
        super(id, Assets.allWall);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
