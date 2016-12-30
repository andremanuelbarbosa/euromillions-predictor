# euromillions-predictor
The EuroMillions Predictor is a simple application that collects some statistics from the past EuroMillions results and tries to guess which numbers/combinations could be more successful on future draws.

## TODO
* Review Draws
  
##### Draws: 968, Snapshots: 868, Algorithms: 22, Execution Time: 0m 37s.
                                                             ALGORITHM MODE MAX AVERAGE WINS (%) STARS_DISTRIBUTED_FREQ          NUMBERS_DISTRIBUTED_FREQ                            DISTRIBUTED_FREQ
                                                     IntervalAlgorithm    0   4    0.80   59   6    {0=586, 1=270, 2=9}         {0=518, 1=291, 2=52, 3=4}            {0=366, 1=330, 2=143, 3=25, 4=1}
         IntervalSequenceCyclicDividedDifferenceInterpolationAlgorithm    1   4    0.94   72   8   {0=532, 1=311, 2=24}         {0=481, 1=325, 2=57, 3=5}            {0=310, 1=341, 2=182, 3=32, 4=3}
                    IntervalSequenceCyclicLinearInterpolationAlgorithm    1   4    0.88   63   7   {0=552, 1=303, 2=11}         {0=491, 1=315, 2=54, 3=5}            {0=314, 1=377, 2=143, 3=30, 4=2}
                     IntervalSequenceCyclicLoessInterpolationAlgorithm    1   3    0.86   68   7   {0=542, 1=311, 2=15}         {0=522, 1=288, 2=57, 3=1}                 {0=333, 1=357, 2=145, 3=33}
                    IntervalSequenceCyclicSplineInterpolationAlgorithm    1   3    0.88   57   6   {0=531, 1=323, 2=14}              {0=511, 1=302, 2=55}                 {0=317, 1=366, 2=158, 3=27}
               IntervalSequenceDividedDifferenceInterpolationAlgorithm    1   4    0.91   63   7   {0=531, 1=311, 2=26}         {0=492, 1=325, 2=47, 3=4}            {0=310, 1=355, 2=172, 3=29, 4=2}
                          IntervalSequenceLinearInterpolationAlgorithm    1   4    0.88   78   8   {0=583, 1=275, 2=10}         {0=483, 1=310, 2=70, 3=5}            {0=327, 1=351, 2=162, 3=27, 4=1}
                           IntervalSequenceLoessInterpolationAlgorithm    1   4    0.89   65   7   {0=543, 1=314, 2=11}         {0=498, 1=311, 2=54, 3=5}            {0=310, 1=382, 2=141, 3=34, 4=1}
                          IntervalSequenceSplineInterpolationAlgorithm    1   3    0.89   71   8   {0=550, 1=297, 2=21}         {0=501, 1=310, 2=51, 3=6}                 {0=321, 1=359, 2=154, 3=34}
        IntervalSequenceTripledDividedDifferenceInterpolationAlgorithm    1   3    0.91   62   7   {0=528, 1=315, 2=25}         {0=495, 1=320, 2=51, 3=2}                 {0=311, 1=349, 2=180, 3=28}
                   IntervalSequenceTripledLinearInterpolationAlgorithm    1   4    0.88   78   8   {0=583, 1=275, 2=10}         {0=483, 1=310, 2=70, 3=5}            {0=327, 1=351, 2=162, 3=27, 4=1}
                    IntervalSequenceTripledLoessInterpolationAlgorithm    1   4    0.86   53   6   {0=529, 1=329, 2=10}         {0=522, 1=297, 2=48, 3=1}            {0=317, 1=383, 2=143, 3=24, 4=1}
                   IntervalSequenceTripledSplineInterpolationAlgorithm    1   3    0.89   71   8   {0=549, 1=298, 2=21}         {0=501, 1=310, 2=51, 3=6}                 {0=320, 1=360, 2=154, 3=34}
                                                 RelativeFreqAlgorithm    1   4    0.86   65   7   {0=565, 1=289, 2=14}         {0=499, 1=309, 2=58, 3=2}            {0=319, 1=376, 2=148, 3=24, 4=1}
                              RelativeFreqAndRelativeIntervalAlgorithm    1   4    0.88   60   6   {0=540, 1=303, 2=25}         {0=510, 1=306, 2=50, 3=2}            {0=316, 1=374, 2=145, 3=31, 4=2}
                       RelativeFreqAndReverseRelativeIntervalAlgorithm    1   4    0.88   69   7   {0=549, 1=301, 2=18}         {0=508, 1=294, 2=63, 3=3}            {0=329, 1=357, 2=141, 3=37, 4=4}
           RelativeFreqAndReverseRelativeIntervalFreqSequenceAlgorithm    1   4    0.87   68   7   {0=559, 1=293, 2=16}         {0=498, 1=308, 2=60, 3=2}            {0=320, 1=364, 2=158, 3=25, 4=1}
                                              ReverseIntervalAlgorithm    1   4    0.88   55   6   {0=536, 1=314, 2=18}         {0=512, 1=303, 2=49, 3=4}            {0=320, 1=364, 2=158, 3=21, 4=5}
                                          ReverseRelativeFreqAlgorithm    1   5    0.83   63   7    {0=588, 1=272, 2=8}         {0=505, 1=300, 2=56, 3=7}       {0=338, 1=370, 2=134, 3=22, 4=3, 5=1}
                       ReverseRelativeFreqAndRelativeIntervalAlgorithm    1   4    0.83   58   6    {0=585, 1=275, 2=8}         {0=501, 1=311, 2=49, 3=7}            {0=338, 1=366, 2=141, 3=19, 4=4}
    ReverseRelativeFreqAndReverseRelativeIntervalFreqSequenceAlgorithm    0   5    0.81   65   7    {0=600, 1=260, 2=8}         {0=510, 1=293, 2=59, 3=6}       {0=356, 1=347, 2=144, 3=15, 4=5, 5=1}
                          ReverseRelativeIntervalFreqSequenceAlgorithm    1   4    0.79   64   7    {0=621, 1=238, 2=9}         {0=498, 1=308, 2=60, 3=2}            {0=352, 1=363, 2=133, 3=19, 4=1}

