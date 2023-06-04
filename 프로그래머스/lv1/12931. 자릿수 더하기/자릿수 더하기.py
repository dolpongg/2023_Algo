def solution(n):
    answer = ""
    lst=[]
    for i in str(n):
        lst.append(i)
    
    a=list(map(int, lst))
    print(type(a))
    print(type(sum(a)))
    answer=sum(a)
    print(answer)
    
    return answer