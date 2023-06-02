from functools import reduce
from functional import seq

numbers = [1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8]
trans1 = list(filter(lambda x: x >= 5, numbers))
trans2 = [(trans1[i], trans1[i + 1]) for i in range(0, len(trans1), 2)]
trans3 = list(map(lambda pair: pair[0] * pair[1], trans2))
finalTrans = reduce(lambda x, y: x + y, trans3)

print(finalTrans)
