package Tiles;

import ImageLoad.Assets;

public class RightCorner extends Tile{
    public RightCorner(int id) {
        super(id, Assets.rightCorner);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
