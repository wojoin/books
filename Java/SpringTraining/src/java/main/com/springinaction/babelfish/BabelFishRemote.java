package com.springinaction.babelfish;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface BabelFishRemote extends Remote {
  public String BabelFish(String translationMode, String sourceData) throws RemoteException;
}
