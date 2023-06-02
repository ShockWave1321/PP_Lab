from pprint import pprint
import more_itertools

data = 'This sentence has words of various lengths in it, both short ones and long ones'.split()

pprint(more_itertools.map_reduce(data, lambda word: word[0]))
