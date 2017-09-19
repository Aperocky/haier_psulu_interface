from __future__ import division
import argparse
import yaml
import random
import numpy as np
import math

def firstPassCommandLine():
    
    # Creating the parser for the input arguments
    parser = argparse.ArgumentParser(description='Translate txt map into yaml')

    # Positional argument - Input XML file
    parser.add_argument('-i', type=str, default='./map.txt',
                        help='Input File Name', dest='inFile')
    parser.add_argument('-o', type=str, default='./map.yaml',
                        help='Output File name', dest='outFile')
    parser.add_argument('-width', type=float, default=0.02,
                        help='Width', dest='width')
    parser.add_argument('-height', type=float, default=0.02,
                        help='Height', dest='height')

    # Parse input
    args = parser.parse_args()
    return args

def getRand(minval=0, maxval=1):
    '''
    Generates a random number with uniform distribution in a given range
    '''	
    return random.uniform(minval, maxval)

def main(args):
    # Parameters
    w    = args.width
    h    = args.height
    inF  = args.inFile
    outF = args.outFile

    unit = 1/36
    # Creating the obstacles
    obst = {}
    obst['environment'] = {}
    obst['environment']['obstacles'] = {}

    # Open map textfile and store all obstacle information
    with open(inF, 'r+') as f:
        lines = f.readlines()
        for row in range(0, len(lines)):
            line = lines[row];
            obsR = line.split(' ')
            for col in range(0, len(obsR)):
                if obsR[col] == '0':
                    cObst = {}
                    cObst['shape'] = 'polygon'
                    # Create an obstacle rectangle
                    rect = np.array([[0,0], [w,0], [w,h], [0,h]])
                    # Translate obstacle to the right column and row
                    dispX = unit * col
                    dispY = 1-unit * row
                    rect = rect + np.array([dispX, dispY])
                    # Compile the corners into the dictionary
                    cObst['corners'] = rect.tolist()
                    n = row * len(obsR) + col
                    obstName = 'obs_%d'%(n)
                    obst['environment']['obstacles'][obstName] = cObst

    with open(outF, 'w') as outfile:
        yaml.dump(obst, outfile, explicit_start=True)

if __name__ == '__main__':
    args = firstPassCommandLine()
    main(args)
