T = int(input())
for _ in range(0, T):
    php = 0
    nhp = 0
    C = int(input())
    Q = list(map(int, input().split()))
    sa = dict(zip(range(1, C+1), Q))
    for k, v in sa.items():
      if k % 2 == 0:
        if v > 0:
            nhp += v
        else:
            php += v
      if k % 2 == 1:
        if v > 0:
            php += v
        else:
            nhp -= v
    if (abs(php) > abs(nhp)):
      print(php)
    else:
      print(nhp)
