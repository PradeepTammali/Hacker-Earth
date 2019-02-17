def solve(y, x, Q, S):
    subs = get_all_substrings(S)
    UR_subs = []
    DL_subs = []
    RD_subs = []
    LU_subs = []
    count = 0
    for sub in subs:
      if 'U' and 'R' in sub:
        UR_subs.append(sub)
      elif 'D' and 'L' in sub:
        DL_subs.append(sub)
      elif 'R' and 'D' in sub:
        RD_subs.append(sub)
      elif 'L' and 'U' in sub:
        LU_subs.append(sub)
    if x[0] == 0 and y[0] == 0:
      count += 1   
    for i in range(1, Q):
      if x[i] >= 0 and y[i] >= 0:
        for sub in UR_subs:
          if checkPoint(x[i], y[i], sub):
            count +=1
            break
      elif x[i] < 0 and y[i] < 0:
        for sub in DL_subs:
          if checkPoint(x[i], y[i], sub):
            count +=1
            break
      elif x[i] >= 0 and y[i] < 0:
        for sub in RD_subs:
          if checkPoint(x[i], y[i], sub):
            count +=1
            break
      elif x[i] < 0 and y[i] >= 0:
        for sub in LU_subs:
          if checkPoint(x[i], y[i], sub):
            count +=1
            break
    return count

def checkPoint(x, y, sub):
  xd = 0
  yd = 0
  for ch in sub:
    if ch == 'U':
      yd += 1
    elif ch == 'D':
      yd -= 1
    elif ch == 'L':
      xd -= 1
    elif ch == 'R':
      xd += 1
  if xd == x and yd == y:
    return True
  else:
    return False

def get_all_substrings(input_string):
  length = len(input_string)
  return [input_string[i:j+1] for i in range(length) for j in range(i, length)]


T = int(input())
for _ in range(T):
    S = input()
    Q = int(input())
    x = list(map(int, input().split()))
    y = list(map(int, input().split()))
    out_ = solve(y, x, Q, S)
    print(out_)
