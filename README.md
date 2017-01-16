# euromillions-predictor
The EuroMillions Predictor is a simple application that collects some statistics from the past EuroMillions results and tries to guess which numbers/combinations could be more successful on future draws.

## TODO
* Templates
* Prizes Sum
* Money Won vs Spent
* Snapshots Statistics
* Review Draws

##### Algorithms Statistics [Draws: 968, Snapshots: 868, Algorithms: 36, Execution Time: 1m 7s]
                                                                ALGORITHM MODE MAX AVERAGE WINS   (%) STARS_DISTRIBUTED_FREQ          NUMBERS_DISTRIBUTED_FREQ                            DISTRIBUTED_FREQ
                                                        IntervalAlgorithm    0   4    0.80   59  6.80    {0=586, 1=270, 2=9}         {0=519, 1=292, 2=52, 3=4}            {0=364, 1=329, 2=143, 3=25, 4=1}
            IntervalCyclicDividedDifferenceInterpolationSequenceAlgorithm    0   3    0.83   68  7.83   {0=598, 1=255, 2=15}         {0=494, 1=311, 2=60, 3=2}                 {0=351, 1=337, 2=152, 3=27}
                       IntervalCyclicLinearInterpolationSequenceAlgorithm    1   4    0.85   68  7.83   {0=586, 1=272, 2=10}    {0=496, 1=306, 2=60, 3=5, 4=1}            {0=323, 1=384, 2=133, 3=25, 4=3}
                        IntervalCyclicLoessInterpolationSequenceAlgorithm    1   4    0.81   53  6.11    {0=599, 1=261, 2=8}         {0=496, 1=320, 2=47, 3=5}            {0=329, 1=396, 2=121, 3=20, 4=2}
                       IntervalCyclicSplineInterpolationSequenceAlgorithm    1   4    0.85   69  7.95    {0=587, 1=272, 2=9}         {0=494, 1=307, 2=62, 3=5}            {0=330, 1=373, 2=134, 3=29, 4=2}
                  IntervalDividedDifferenceInterpolationSequenceAlgorithm    1   3    0.83   65  7.49   {0=601, 1=252, 2=15}         {0=490, 1=319, 2=57, 3=2}                 {0=342, 1=356, 2=145, 3=25}
                             IntervalLinearInterpolationSequenceAlgorithm    1   4    0.86   65  7.49   {0=596, 1=262, 2=10}    {0=480, 1=326, 2=52, 3=9, 4=1}            {0=328, 1=378, 2=125, 3=33, 4=4}
                              IntervalLoessInterpolationSequenceAlgorithm    0   4    0.81   63  7.26    {0=608, 1=254, 2=6}         {0=500, 1=306, 2=57, 3=5}            {0=363, 1=336, 2=142, 3=24, 4=2}
                             IntervalSplineInterpolationSequenceAlgorithm    1   4    0.87   72  8.29   {0=587, 1=268, 2=13}         {0=483, 1=317, 2=60, 3=8}            {0=330, 1=359, 2=146, 3=28, 4=5}
           IntervalTripledDividedDifferenceInterpolationSequenceAlgorithm    1   4    0.83   67  7.72   {0=602, 1=250, 2=16}         {0=497, 1=310, 2=58, 3=3}            {0=347, 1=352, 2=143, 3=25, 4=1}
                      IntervalTripledLinearInterpolationSequenceAlgorithm    1   4    0.86   65  7.49   {0=596, 1=262, 2=10}    {0=480, 1=326, 2=52, 3=9, 4=1}            {0=328, 1=378, 2=125, 3=33, 4=4}
                       IntervalTripledLoessInterpolationSequenceAlgorithm    0   4    0.77   57  6.57    {0=613, 1=249, 2=6}         {0=523, 1=290, 2=46, 3=9}            {0=385, 1=328, 2=126, 3=26, 4=3}
                      IntervalTripledSplineInterpolationSequenceAlgorithm    1   4    0.87   72  8.29   {0=588, 1=267, 2=13}         {0=483, 1=317, 2=60, 3=8}            {0=332, 1=356, 2=147, 3=28, 4=5}
                                                    RelativeFreqAlgorithm    1   4    0.86   65  7.49   {0=565, 1=289, 2=14}         {0=499, 1=309, 2=58, 3=2}            {0=319, 1=376, 2=148, 3=24, 4=1}
                                 RelativeFreqAndRelativeIntervalAlgorithm    1   4    0.88   60  6.91   {0=540, 1=303, 2=25}         {0=510, 1=306, 2=50, 3=2}            {0=316, 1=374, 2=145, 3=31, 4=2}
                          RelativeFreqAndReverseRelativeIntervalAlgorithm    1   4    0.88   69  7.95   {0=549, 1=301, 2=18}         {0=508, 1=294, 2=63, 3=3}            {0=329, 1=357, 2=141, 3=37, 4=4}
              RelativeFreqAndReverseRelativeIntervalFreqSequenceAlgorithm    1   4    0.87   68  7.83   {0=559, 1=293, 2=16}         {0=498, 1=308, 2=60, 3=2}            {0=320, 1=364, 2=158, 3=25, 4=1}
                                    RelativeIntervalFreqSequenceAlgorithm    1   4    0.90   62  7.14   {0=533, 1=321, 2=14}         {0=506, 1=302, 2=53, 3=7}            {0=303, 1=385, 2=150, 3=27, 4=3}
                                                 ReverseIntervalAlgorithm    1   4    0.88   55  6.34   {0=536, 1=314, 2=18}         {0=512, 1=303, 2=49, 3=4}            {0=320, 1=364, 2=158, 3=21, 4=5}
     ReverseIntervalCyclicDividedDifferenceInterpolationSequenceAlgorithm    1   4    0.94   72  8.29   {0=533, 1=311, 2=24}         {0=481, 1=325, 2=57, 3=5}            {0=310, 1=341, 2=182, 3=32, 4=3}
                ReverseIntervalCyclicLinearInterpolationSequenceAlgorithm    1   4    0.88   63  7.26   {0=554, 1=303, 2=11}         {0=494, 1=315, 2=54, 3=5}            {0=314, 1=379, 2=143, 3=30, 4=2}
                 ReverseIntervalCyclicLoessInterpolationSequenceAlgorithm    1   3    0.86   68  7.83   {0=542, 1=311, 2=15}         {0=521, 1=288, 2=57, 3=1}                 {0=333, 1=357, 2=145, 3=33}
                ReverseIntervalCyclicSplineInterpolationSequenceAlgorithm    1   3    0.88   57  6.57   {0=531, 1=323, 2=14}              {0=511, 1=302, 2=55}                 {0=317, 1=366, 2=158, 3=27}
           ReverseIntervalDividedDifferenceInterpolationSequenceAlgorithm    1   4    0.91   63  7.26   {0=531, 1=311, 2=26}         {0=492, 1=325, 2=47, 3=4}            {0=310, 1=355, 2=172, 3=29, 4=2}
                      ReverseIntervalLinearInterpolationSequenceAlgorithm    1   4    0.88   78  8.99   {0=583, 1=275, 2=10}         {0=483, 1=310, 2=70, 3=5}            {0=327, 1=351, 2=162, 3=27, 4=1}
                       ReverseIntervalLoessInterpolationSequenceAlgorithm    1   4    0.89   65  7.49   {0=543, 1=314, 2=11}         {0=498, 1=311, 2=54, 3=5}            {0=310, 1=382, 2=141, 3=34, 4=1}
                      ReverseIntervalSplineInterpolationSequenceAlgorithm    1   3    0.89   71  8.18   {0=550, 1=297, 2=21}         {0=501, 1=310, 2=51, 3=6}                 {0=321, 1=359, 2=154, 3=34}
    ReverseIntervalTripledDividedDifferenceInterpolationSequenceAlgorithm    1   3    0.91   62  7.14   {0=528, 1=315, 2=25}         {0=495, 1=320, 2=51, 3=2}                 {0=311, 1=349, 2=180, 3=28}
               ReverseIntervalTripledLinearInterpolationSequenceAlgorithm    1   4    0.88   78  8.99   {0=583, 1=275, 2=10}         {0=483, 1=310, 2=70, 3=5}            {0=327, 1=351, 2=162, 3=27, 4=1}
                ReverseIntervalTripledLoessInterpolationSequenceAlgorithm    1   4    0.86   53  6.11   {0=529, 1=329, 2=10}         {0=522, 1=297, 2=48, 3=1}            {0=317, 1=383, 2=143, 3=24, 4=1}
               ReverseIntervalTripledSplineInterpolationSequenceAlgorithm    1   3    0.89   71  8.18   {0=549, 1=298, 2=21}         {0=501, 1=310, 2=51, 3=6}                 {0=320, 1=360, 2=154, 3=34}
                                             ReverseRelativeFreqAlgorithm    1   5    0.83   63  7.26    {0=588, 1=272, 2=8}         {0=505, 1=300, 2=56, 3=7}       {0=338, 1=370, 2=134, 3=22, 4=3, 5=1}
                          ReverseRelativeFreqAndRelativeIntervalAlgorithm    1   4    0.83   58  6.68    {0=585, 1=275, 2=8}         {0=501, 1=311, 2=49, 3=7}            {0=338, 1=366, 2=141, 3=19, 4=4}
                   ReverseRelativeFreqAndReverseRelativeIntervalAlgorithm    1   4    0.91   67  7.72   {0=536, 1=309, 2=23}         {0=498, 1=309, 2=56, 3=5}            {0=315, 1=354, 2=165, 3=29, 4=5}
       ReverseRelativeFreqAndReverseRelativeIntervalFreqSequenceAlgorithm    0   5    0.81   65  7.49    {0=600, 1=260, 2=8}         {0=510, 1=293, 2=59, 3=6}       {0=356, 1=347, 2=144, 3=15, 4=5, 5=1}
                             ReverseRelativeIntervalFreqSequenceAlgorithm    1   4    0.79   64  7.37    {0=621, 1=238, 2=9}         {0=498, 1=308, 2=60, 3=2}            {0=352, 1=363, 2=133, 3=19, 4=1}

