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
+ Gurobi academic license key which can be requested [here](https://user.gurobi.com/download/licenses/free-academic). Notice that the license can only be registered in a college or academic network. Once registered, the license can be used anywhere. 

## Build
Download and install Anaconda 4.4.0 for Python 2.7. This python package will be used in replacement of the original system python. It comes with pip which is a python pacakge manager. 
Use pip to install PuLP, a general mathematics python library. 
Download and install Gurobi Optimizer. Register a free academic license to activate Gurobi. 
There is a minor bug in Anaconda package so we need to fix it. Locate <Anaconda Location>/Lib/site-packages/pulp/solvers. Go to line 2029. We need to change 

```
name, value = line.split()
values[name] = float(value)
```
into 
```
try: 
    name, value = line.split()
values[name] = float(value)
except ValueError:
continue
```
This change is to make sure that when some input lines are not name-value pair, the program won't crash. 

Now we are ready to run **HaierPsuluInterface.class** in Eclipse. 

## User Interface
<img src="https://github.com/YuansongFeng/haier_psulu_interface/blob/master/screenshots/login.png" width="400" height="400">
<img src="https://github.com/YuansongFeng/haier_psulu_interface/blob/master/screenshots/planning.png" width="400" height="500">
<img src="https://github.com/YuansongFeng/haier_psulu_interface/blob/master/screenshots/pathshown.png" width="400" height="500">
      
## Features
+ Continuous space path planning with receding horizon based on psulu algorithm
+ Support user login and game editing. 
+ Record user actions in the log

