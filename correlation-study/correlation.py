import pandas as pd
import scipy.stats

# Read df_features and df_run
df_features = pd.read_csv('features.csv')
df_features.drop(columns=df_features.columns[0], axis=1, inplace=True)
df_run = pd.read_csv('run.csv')
df_run.drop(columns=df_run.columns[0], axis=1, inplace=True)
df_run['run'] = df_run['run'].map({'steady state':1, 'no steady state':-1, 'inconsistent':0})
df_iter_features = pd.read_csv('features_for_iterations.csv')
df_iter_features.drop(columns=df_iter_features.columns[0], axis=1, inplace=True)
df_iterations = pd.read_csv('iterations.csv')
df_iterations.drop(columns=df_iterations.columns[0], axis=1, inplace=True)
columns = df_features.columns

# Pearson r
df_pearson = pd.DataFrame(columns=['feature','correlation'])
df_pearson = df_pearson.set_index('feature')
# Spearman's rho
df_spearman = pd.DataFrame(columns=['feature','correlation'])
df_spearman = df_spearman.set_index('feature')
# kendall's tau
df_kendall = pd.DataFrame(columns=['feature','correlation'])
df_kendall = df_kendall.set_index('feature')

# Pearson r
df_pearson_i = pd.DataFrame(columns=['feature','correlation'])
df_pearson_i = df_pearson_i.set_index('feature')
# Spearman's rho
df_spearman_i = pd.DataFrame(columns=['feature','correlation'])
df_spearman_i = df_spearman_i.set_index('feature')
# kendall's tau
df_kendall_i = pd.DataFrame(columns=['feature','correlation'])
df_kendall_i = df_kendall_i.set_index('feature')

for col in columns:
    df_pearson.loc[col] = scipy.stats.pearsonr(df_features[col], df_run)[0]
    df_spearman.loc[col] = scipy.stats.spearmanr(df_features[col], df_run).correlation
    df_kendall.loc[col] = scipy.stats.kendalltau(df_features[col], df_run).correlation
    df_pearson_i.loc[col] = scipy.stats.pearsonr(df_iter_features[col], df_iterations)[0]
    df_spearman_i.loc[col] = scipy.stats.spearmanr(df_iter_features[col], df_iterations).correlation
    df_kendall_i.loc[col] = scipy.stats.kendalltau(df_iter_features[col], df_iterations).correlation

df_pearson.to_csv(".\\correlations\\pearson_correlation.txt")
df_spearman.to_csv(".\\correlations\\spearman_correlation.txt")
df_kendall.to_csv(".\\correlations\\kendall_correlation.txt")
df_pearson_i.to_csv(".\\correlations\\pearson_i_correlation.txt")
df_spearman_i.to_csv(".\\correlations\\spearman_i_correlation.txt")
df_kendall_i.to_csv(".\\correlations\\kendall_i_correlation.txt")