import yaml
import numpy as np
import os, tempfile

def test(fName, risk):
   os.system('python PuLPpSulu.py -i %s'%fName)
   os.system('cp ./output/output_plot.png ./output/' + str(risk) + '.png')
   return

if __name__ == "__main__":
   templateF = '/Users/subrahma/proj/haier_psulu_interface/src/psulu/config/tmp/test1.yaml'
   template = yaml.load(open(templateF, 'r'))
   for wp in np.linspace(0.1, 0.35, 20):
      with tempfile.NamedTemporaryFile() as fp:
         print(fp.name)
         template['chance_constraint'] = wp
         yaml.dump(template, fp)
         test(fp.name, wp)

