import os
import pandas as pd

features = ".\\Everything\\"
classifications = ".\\classification\\"

# Add all benchmark filenames to a dictionary
classification_dict = dict()
for c in os.listdir(classifications):
    name = c.replace(c.split("#", 1)[0], "")[1:len(c)]
    classification_dict.update({name : c})

# Define DataFrames
df_run = pd.DataFrame(columns=['run'])
df_features = pd.DataFrame()
df_iterations = pd.DataFrame(columns=['iterations'])
df_iter_features = None

# Loop for every file
# Get the feature file and do this with it
for name in os.listdir(features):
    for b in classification_dict:
        trunc_name = name.replace("_everything.txt", "")
        trunc_name = trunc_name[trunc_name.rindex("."):len(trunc_name)]
        if trunc_name in b:
            df_feature = None
            index = 0
            if (df_features.empty):
                df_features = pd.read_table(features + name, delimiter=':', names=['feature', '0'])
                df_features = df_features.set_index('feature')
                df_features = df_features.T
                df_feature = df_features
            else:
                index = len(df_features.index)
                df_temp = pd.read_table(features + name, delimiter=':', names=['feature', index])
                df_temp = df_temp.set_index('feature')
                df_temp = df_temp.T
                df_feature = df_temp
                # Append this for every bench file related to it
                df_features.loc[index] = df_temp.loc[index]
            #print(df_features)
            # Find related bench file(s) and do this
            print(classifications + classification_dict[b])
            columns = df_feature.columns
            df_json = pd.read_json(classifications + classification_dict[b])
            df_run.loc[len(df_run.index)] = df_json['run'][0]
            if (df_json['run'][0] == 'steady state'):
                for iteration in df_json['steady_state_starts']:
                    if (df_iter_features is None):
                        df_iter_features = pd.DataFrame(columns=columns)
                        print(df_iter_features.columns)
                        print(df_feature.columns)
                        df_iter_features = pd.concat([df_iter_features, df_feature])
                    else:
                        df_iter_features = pd.concat([df_iter_features, df_feature])
                    df_iterations.loc[len(df_iterations.index)] = iteration

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
# Save df_features and df_run
df_features.to_csv('features.csv')
df_run.to_csv('run.csv')
df_iter_features.to_csv('features_for_iterations.csv')
df_iterations.to_csv('iterations.csv')