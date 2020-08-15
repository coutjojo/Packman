package Tiles;

import ImageLoad.Assets;

public class RightLowerCorner extends Tile {
    public RightLowerCorner(int id) {
        super(id, Assets.rightLowerCorner);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
