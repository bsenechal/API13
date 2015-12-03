package com.utc.api13.server.com.interfaces;

import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.server.com.ComServerManager;

public interface IServeurComToData {

    /*****************************************************************/
    /*****************************************************************/
    // on a plus besoin de cette classe, ne rien modifier SVP
    // Xiaowei ZHANG
    /*****************************************************************/
    /*****************************************************************/
    public void multicastMove(PublicUserEntity users, UUID idPlayer, MoveEntity move);

    public void multicastFinished(GameEntity game);

    public void multicastNewPlayer(PublicUserEntity pubPlayer);

    public ComServerManager getComServerManagerInstance();

    public void setComServerManagerInstance(ComServerManager comServerManagerInstance);

}
