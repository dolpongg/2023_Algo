def solution(players, callings):
    answer = []
    
    name_dict = {}
    index_dict = {}
    for i in range(len(players)):
        name_dict[players[i]] = i
        index_dict[i] = players[i]
        
    for name in callings:
        front_name = name
        front_index = name_dict[name]
        back_name = index_dict[front_index-1]
        
        ## index바꾸기
        name_dict[front_name] = front_index-1
        name_dict[back_name] = front_index
        index_dict[front_index] = back_name
        index_dict[front_index-1] = front_name
    
    for i in range(len(players)):
        answer.append(index_dict[i])
        
    return answer