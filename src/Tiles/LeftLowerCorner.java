package Tiles;

import ImageLoad.Assets;

public class LeftLowerCorner extends Tile {
    public LeftLowerCorner(int id) {
        super(id, Assets.leftLowerCorner);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
