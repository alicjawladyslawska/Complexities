import matplotlib.pyplot as plt

# Data for the first algorithm (Merge Sort)
sizes_merge_sort = [10, 25, 35, 50, 100, 250, 500, 1000, 10000]
runtime_merge_sort_ns = [110330, 106385, 76205, 31085, 83150, 125622, 265235, 469120, 2659841]
runtime_merge_sort_ms = [time_ns / 1000000 for time_ns in runtime_merge_sort_ns]

# Data for the second algorithm (Selection Sort)
sizes_selection_sort = [10, 25, 35, 50, 100, 250, 500, 1000, 10000]
runtime_selection_sort_ns = [45149, 43992, 87875, 140571, 631220, 1220585, 1890210, 2198650, 66807764]
runtime_selection_sort_ms = [time_ns / 1000000 for time_ns in runtime_selection_sort_ns]

# Plotting the data with a logarithmic x-axis
plt.plot(sizes_merge_sort, runtime_merge_sort_ms, marker='o', label='Merge Sort')
plt.plot(sizes_selection_sort, runtime_selection_sort_ms, marker='o', label='Selection Sort')

# Adding labels and title
plt.xscale('log')  # Set x-axis to logarithmic scale
plt.xlabel('List Size (log scale)')
plt.ylabel('Runtime (ms)')
plt.title('Runtime Comparison of Merge Sort and Selection Sort')
plt.legend()

# Display the plot
plt.show()
