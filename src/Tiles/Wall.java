package Tiles;

import ImageLoad.Assets;

public class Wall extends Tile{
    public Wall(int id) {
        super(id, Assets.rightleftTunnel);
    }
    @Override
    public boolean isSolid(){
      return true;
    }
}
