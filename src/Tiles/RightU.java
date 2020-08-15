package Tiles;

import ImageLoad.Assets;

public class RightU extends Tile{
    public RightU(int id) {
        super(id, Assets.rightU);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
