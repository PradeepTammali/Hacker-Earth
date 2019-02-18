# With out using any Counter for creating dictionary
T = int(input())
for _ in range(0, T):
    N = int(input())
    palindrom_number = str(N) + str(N)[::-1]
    sa = {}
    for i in list(palindrom_number):
        if i in sa:
            sa[i] += 1
        else:
            sa[i] = 1
    maxValue = max(sa.values())
    fn = []
    for k, v in sa.items():
        if sa[k] == maxValue:
            fn.append(k)
    print(min(fn))



# using counter for creating dictionary 
from collections import Counter
T = int(input())
for _ in range(0, T):
    N = int(input())
    palindrom_number = list(str(N) + str(N)[::-1])
    dict = Counter(palindrom_number)  # dictionary with its repeated count as value
    value = sorted(dict.values(), reverse=True) 
    maxValue = value[0]  # the most repeated value in the dict
    fl = []
    for (key, val) in dict.items(): 
        if val == maxValue: 
            fl.append(int(key))
    fl.sort()
    print(fl[0])