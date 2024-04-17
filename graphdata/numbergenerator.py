import random
# Number generator code to create sequences for testing
# By 220019540

# Generate a list of numbers from 1 to 100
numbers = list(range(1, 101))

# Shuffle the list to make it unsorted
random.shuffle(numbers)

# Convert the list to a string in the desired format
unsortedArray100 = ",".join(map(str, numbers))

# Print the result
print(f"unsortedArray100 = {unsortedArray100}")
