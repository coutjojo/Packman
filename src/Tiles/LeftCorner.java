package Tiles;

import ImageLoad.Assets;

public class LeftCorner extends Tile{
    public LeftCorner(int id) {
        super(id, Assets.leftCorner);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
