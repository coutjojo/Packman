package Tiles;

import ImageLoad.Assets;

public class RightUpperCorner extends Tile{
    public RightUpperCorner(int id) {
        super(id, Assets.rightUpperCorner);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
