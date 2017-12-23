%% Empirically determine the distribution of path complexity measure a
num_bins = 20;
d = sqrt(2);
map2_D = [1.5689, 1.5551, 1.5758, 1.6578, 1.6097,... 
          1.6091, 1.5535, 1.5627, 1.5522, 1.5390];
a = map2_D/d;
stdA = std(a)
meanA = mean(a)
