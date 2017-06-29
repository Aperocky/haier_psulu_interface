import random as R
import yaml as Y
import argparse
import pdb
import os, sys

from model.gamedata.jython import VerticesSupplier

class ObstacleFactory(VerticesSupplier):
  #obstaclePath is the path to yaml file of all obstalces 
  def __init__(self, obstaclePath=None):
    self.nObstacles     = None #number of obstacles 
    self.nSides         = None #number of sides for each obstacle

    # Standard names used in the paper
    # H -> Polytope boundaries, V -> Corner Vertices, g->Plane Constant
    self.obstVert     = None #V, needs to be specified
    self.obstName     = None

    if obstaclePath is not None:
      # Initializing the nSides to 4 for now - To be generalized
      self.nSides = 4
      obstaclePath = obstaclePath.strip('[()]')
      self.__readObstMap__(obstaclePath)

  # obtain the list of vertices for all obstacles
  def getObstacleVertices(self):
    return self.obstVert

  # input self.obstVert
  def __readObstMap__(self, obstMapFile):
    '''
    Read Obstacle map from the YAML
    '''

    # Read Obstacle YAML map
    # print('Reading Obstacle Map: %s'%obstMapFile)
    stream = file(obstMapFile, 'r')
    envParams = Y.load(stream)

    # Initialize the class objects
    obstacles = envParams['environment']['obstacles']
    self.nObstacles = len(obstacles.keys())

    # Read obstancles from the environment file
    # self.obstVert = np.zeros((self.nSides,2,self.nObstacles))
    self.obstVert = []
    self.obstName = []
    self.zInit    = []
    for i, obstName in enumerate(obstacles.keys()):
      # self.obstVert[:,:,i] = obstacles[obstName]['corners']
      # a list of all lists of obstacle corners
      self.obstVert.append(obstacles[obstName]['corners'])
      self.obstName.append(obstName)
      try:
         # Search for initialization variables
         cKeys = obstacles[obstName].keys()
         for k in cKeys:
           if 'init' in k:
              stepnum = int(k.replace('init_',''))
              self.zInit.append({'stepnum': stepnum, 'obstname': obstName, 'obstnum': i,\
					'side': obstacles[obstName][k]})
      except:
         continue

    return
