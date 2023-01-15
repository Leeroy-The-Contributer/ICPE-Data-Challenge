import seaborn as sns
import umap.umap_ as umap
import pandas as pd
import numpy as np

df_features = pd.read_csv('features.csv')
df_run = pd.read_csv('run.csv')

df_features.drop(columns=df_features.columns[0], axis=1, inplace=True)
df_run.drop(columns=df_run.columns[0], axis=1, inplace=True)

print(df_features.shape)
print(df_run.shape)

for perplexity in [5, 30, 55, 80, 105,  130]:
    reducer = umap.UMAP(n_components=2, random_state=0, perplexity=perplexity, learning_rate=10)
    umap_out = reducer.fit_transform(df_features)
    umap_frame = pd.DataFrame(np.row_stack( umap_out ), columns=['x','y'])
    umap_frame['label'] = df_run['run'].astype('category') #baseline diagnosis labels
    umap_framesub = umap_frame.copy() #tsne_frame[tsne_frame.label.isin(['AD_Dementia','CN_CN','EMCI_MCI','MCI_MCI','MCI_Dementia'])]
    umap_framesub.label.cat.remove_unused_categories(inplace=True)
    sns.set_context("notebook",font_scale=1.1)
    sns.set_style("ticks")
    plot = sns.lmplot(x='x', y='y', 
               data=umap_framesub, 
               fit_reg=False, legend=True, 
               height=8, 
               hue='label', 
               scatter_kws={"s":50, "alpha":0.2})
    plot.figure.savefig(f'umap\\{perplexity}_umap.png')