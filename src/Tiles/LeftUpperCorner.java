package Tiles;

import ImageLoad.Assets;

public class LeftUpperCorner extends Tile{
    public LeftUpperCorner(int id) {
        super(id, Assets.leftUpperCorner);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
