euromillions-predictor
======================

The EuroMillions Predictor is a simple application that collects some statistics from the past EuroMillions results and tries to guess which numbers/combinations could be more successful on future draws.


Draws: 966, Algorithms: 22, Execution Time: 0m 37s.

                                                         ALGORITHM MODE MAX AVERAGE WINS  % STARS_DISTRIBUTED_FREQ          NUMBERS_DISTRIBUTED_FREQ                            DISTRIBUTED_FREQ
                                                 IntervalAlgorithm    0   0    0.00    0  0                {0=862}                           {0=862}                                     {0=861}
     IntervalSequenceCyclicDividedDifferenceInterpolationAlgorithm    1   4    0.94   72  8   {0=528, 1=315, 2=22}         {0=476, 1=326, 2=60, 3=3}            {0=301, 1=348, 2=183, 3=32, 4=1}
                IntervalSequenceCyclicLinearInterpolationAlgorithm    1   4    0.88   77  8   {0=558, 1=292, 2=14}         {0=497, 1=293, 2=65, 3=8}            {0=321, 1=358, 2=152, 3=30, 4=4}
                 IntervalSequenceCyclicLoessInterpolationAlgorithm    1   4    0.82   53  6   {0=546, 1=305, 2=13}         {0=532, 1=286, 2=46, 3=1}            {0=345, 1=348, 2=152, 3=19, 4=1}
                IntervalSequenceCyclicSplineInterpolationAlgorithm    1   4    0.87   66  7   {0=548, 1=299, 2=18}         {0=516, 1=288, 2=57, 3=4}            {0=327, 1=359, 2=152, 3=22, 4=5}
           IntervalSequenceDividedDifferenceInterpolationAlgorithm    1   4    0.92   70  8   {0=529, 1=314, 2=22}         {0=494, 1=308, 2=59, 3=4}            {0=310, 1=348, 2=174, 3=32, 4=1}
                      IntervalSequenceLinearInterpolationAlgorithm    1   4    0.90   70  8   {0=560, 1=294, 2=11}         {0=470, 1=328, 2=63, 3=4}            {0=309, 1=364, 2=159, 3=32, 4=1}
                       IntervalSequenceLoessInterpolationAlgorithm    1   4    0.86   64  7   {0=542, 1=309, 2=14}         {0=520, 1=287, 2=54, 3=4}            {0=321, 1=374, 2=143, 3=24, 4=3}
                      IntervalSequenceSplineInterpolationAlgorithm    1   4    0.87   68  7   {0=557, 1=289, 2=19}         {0=504, 1=298, 2=61, 3=2}            {0=329, 1=347, 2=162, 3=26, 4=1}
    IntervalSequenceTripledDividedDifferenceInterpolationAlgorithm    1   4    0.93   75  8   {0=526, 1=317, 2=22}         {0=492, 1=307, 2=62, 3=4}            {0=306, 1=350, 2=174, 3=34, 4=1}
               IntervalSequenceTripledLinearInterpolationAlgorithm    1   4    0.90   70  8   {0=560, 1=294, 2=11}         {0=470, 1=328, 2=63, 3=4}            {0=309, 1=364, 2=159, 3=32, 4=1}
                IntervalSequenceTripledLoessInterpolationAlgorithm    1   3    0.82   39  4   {0=529, 1=326, 2=10}         {0=537, 1=292, 2=35, 3=1}                 {0=325, 1=389, 2=131, 3=20}
               IntervalSequenceTripledSplineInterpolationAlgorithm    1   4    0.87   68  7   {0=556, 1=290, 2=19}         {0=504, 1=298, 2=61, 3=2}            {0=329, 1=346, 2=163, 3=26, 4=1}
                                             RelativeFreqAlgorithm    1   4    1.06  108 12   {0=502, 1=343, 2=20}         {0=438, 1=326, 2=95, 3=6}            {0=259, 1=350, 2=206, 3=45, 4=5}
                          RelativeFreqAndRelativeIntervalAlgorithm    0   0    0.00    0  0                {0=865}                           {0=865}                                     {0=865}
                   RelativeFreqAndReverseRelativeIntervalAlgorithm    5   7    4.94  857 99    {0=3, 1=240, 2=622} {1=27, 2=134, 3=375, 4=278, 5=51} {1=1, 2=8, 3=61, 4=199, 5=335, 6=227, 7=34}
       RelativeFreqAndReverseRelativeIntervalFreqSequenceAlgorithm    1   4    1.06  109 12   {0=506, 1=336, 2=23}         {0=438, 1=327, 2=94, 3=6}            {0=260, 1=353, 2=198, 3=50, 4=4}
                                          ReverseIntervalAlgorithm    1   4    0.92   61  7   {0=519, 1=327, 2=19}         {0=501, 1=306, 2=53, 3=5}            {0=308, 1=359, 2=166, 3=27, 4=5}
                                      ReverseRelativeFreqAlgorithm    0   4    0.66   41  4    {0=620, 1=240, 2=5}         {0=590, 1=234, 2=37, 3=4}             {0=425, 1=327, 2=99, 3=11, 4=3}
                   ReverseRelativeFreqAndRelativeIntervalAlgorithm    0   0    0.00    0  0                {0=865}                           {0=865}                                     {0=865}
ReverseRelativeFreqAndReverseRelativeIntervalFreqSequenceAlgorithm    0   5    0.68   41  4    {0=611, 1=247, 2=7}         {0=585, 1=239, 2=36, 3=5}        {0=418, 1=327, 2=106, 3=9, 4=4, 5=1}
                      ReverseRelativeIntervalFreqSequenceAlgorithm    1   4    0.97  103 11   {0=566, 1=288, 2=11}         {0=438, 1=327, 2=94, 3=6}            {0=286, 1=356, 2=183, 3=39, 4=1}
                  