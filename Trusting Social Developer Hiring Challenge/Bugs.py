if __name__ == '__main__':
    T = int(input())
    A = []
    out = []
    for i in range(T):
        operations = list(map(int, input().rstrip().split()))
        if len(operations) == 2:
            A.append(operations[1])
        else:
            if len(A) < 3:
                out.append('Not enough enemies')
            else:
                A.sort(reverse=True)
                out.append(A[int(len(A)/3)-1])
    print(*out, sep="\n")