def solution(price, money, count):
    answer = 0

    sum = 0;
    for i in range(count):
        sum += ((i+1) * price)
    
    answer = money - sum
    if answer < 0:
        answer = -1 * answer
    else:
        answer = 0
    return answer