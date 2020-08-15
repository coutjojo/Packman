package Tiles;

import ImageLoad.Assets;

public class RightWall extends Tile {
    public RightWall(int id) {
        super(id, Assets.rightWall);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
