package Tiles;

import ImageLoad.Assets;

public class LeftU extends Tile {
    public LeftU(int id) {
        super(id, Assets.leftU);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
