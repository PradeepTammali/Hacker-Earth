def findWinner(i_s, d_v, f_t, c_t):
    f_s = 0
    c_s = 0
    for j in range(4):
        f_s += i_s[j] - (f_t[j]*d_v[j])
        c_s += i_s[j] - (c_t[j]*d_v[j])
    if f_s > c_s:
        return 'Flash'
    elif c_s > f_s:
        return 'Cisco'
    elif f_s == c_s:
        if max(f_t) < max(c_t):
            return 'Flash'
        elif max(f_t) > max(c_t):
            return 'Cisco'
        elif max(f_t) == max(c_t):
            return 'Tie'

if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        initial_scores = list(map(int, input().rstrip().split()))
        decrease_value = list(map(int, input().rstrip().split()))
        flash_times = list(map(int, input().rstrip().split()))
        cisco_times = list(map(int, input().rstrip().split()))
        result = findWinner(initial_scores, decrease_value, flash_times, cisco_times)
        print(result)