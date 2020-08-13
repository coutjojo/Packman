package Tiles;

import ImageLoad.Assets;

public class LowerWall extends Tile {
    public LowerWall(int id) {
        super(id, Assets.lowerWall);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
