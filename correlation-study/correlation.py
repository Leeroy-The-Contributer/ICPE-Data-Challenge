import pandas as pd
import numpy as np

df_run = pd.DataFrame(columns=['run'])
df_features = None

# Loop for every file
# Get the feature file and do this with it
if (df_features == None):
    df_features = pd.read_table('org.apache.arrow.adapter.jdbc.JdbcAdapterBenchmarks_everything.txt', delimiter=':', names=['feature', '0'])
    df_features = df_features.set_index('feature')
    df_features = df_features.T
else:
    df_temp = pd.read_table('org.apache.arrow.adapter.jdbc.JdbcAdapterBenchmarks_everything.txt', delimiter=':', names=['feature', len(df_features.index)])
    df_temp = df_temp.set_index('feature')
    df_temp = df_temp.T
    # Append this for every bench file related to it
    df_features.loc[len(df_features.index)] = df_temp.loc[len(df_features.index)]

print(df_features)

# Find related bench file(s) and do this
df_json = pd.read_json('apache__arrow#org.apache.arrow.adapter.jdbc.JdbcAdapterBenchmarks.consumeBenchmark#.json')
df_run.loc[len(df_run.index)] = df_json['run'][0]


# One File:
# Feature1 | Feature2 | etc.
# 10 | 420 | etc.
# ...
# N

# Second File:
# Run
# 1
# ...
# N

# Happens after loops
df_run['run'] = df_run['run'].map({'steady state':1, 'no steady state':-1, 'inconsistent':0})

print(df_features)
print(df_run)
print(df_features.corrwith(df_run['run']))