def findFrequncy():
    return 0

if __name__ == '__main__':
    N, Q = list(map(int, input().rstrip().split()))
    edges = []
    queries = []
    for i in range(N-1):
        edges.append(list(map(int, input().rstrip().split())))
    values = list(map(int, input().rstrip().split()))
    for j in range(Q):
        queries.append(list(map(int, input().rstrip().split())))
    result = findFrequncy()
    print(result)