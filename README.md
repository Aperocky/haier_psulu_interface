# haier_psulu_interface
## Introduction
In the field of robotic control, one of the most challenging problems faced by all controllers is called receding horizon. 
This phenomenon is when the robot loses contact with its controllers for a brief moment of time due to environmental obstacles 
whereas the risk of the robot running into danger accumulates. The goal of this project is to find out under this special condition, 
whether it is preferrable to rely on a set of instructions previously set by human, or to completely rely on the robot's internal 
algorithm to decide next step of action.  

## Dependency
+ [Anaconda 4.4.0 Python 2.7 version](https://www.continuum.io/downloads)
+ PuLP 1.6.5(pip install pulp)
+ [Gurobi Optimizer](http://www.gurobi.com/downloads/gurobi-optimizer)
+ Gurobi academic license key which can be requested [here](https://user.gurobi.com/download/licenses/free-academic)

## Build
Download and install Anaconda 4.4.0 for Python 2.7. This python package will be used in replacement of the original system python. This package comes with pip which is a python pacakge manager. 
Use pip to install PuLP, a general mathematics python library. 
Download and install Gurobi Optimizer. Register a free academic license to activate Gurobi. 
Run the file in eclipse. 

## User Interface
<img src="https://github.com/YuansongFeng/risk_awareness_simulation_javafx/blob/master/screenshots/login.png" width="400">
<img src="https://github.com/YuansongFeng/risk_awareness_simulation_javafx/blob/master/screenshots/planning.png" width="400">
<img src="https://github.com/YuansongFeng/risk_awareness_simulation_javafx/blob/master/screenshots/pathshown.png" width="400">
      
## Features
+ Continuous space path planning with receding horizon based on psulu algorithm
+ Support user login and game editing. 
+ Record user actions in the log

