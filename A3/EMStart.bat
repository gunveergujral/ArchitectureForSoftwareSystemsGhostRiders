%ECHO OFF
START "EVENT MANAGER REGISTRY" /MIN /NORMAL rmiregistry
START "EVENT MANAGER" /MIN /NORMAL java MessageManager -Djava.rmi.server.hostname=localhost
