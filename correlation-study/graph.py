import pandas as pd
import matplotlib.pyplot as plt

types = ['pearson', 'spearman', 'kendall', 'pearson_i', 'spearman_i', 'kendall_i']
condition = 0.06
heading_f = "Correlations for Different Features on Steady State"
heading_i = "Correlations for Different Features on Iterations to Reach Steady State"

for t in types:
    heading = ""
    if '_i' in t:
        heading = heading_i
    else:
        heading = heading_f
    if t == 'spearman_i':
        condition = 0.15
    elif t == 'kendall_i':
        condition = 0.12
    else:
        condition = 0.06
    # Correlation Graph for All libraries
    df = pd.read_csv(f'.\\correlations\\{t}_correlation.txt', names=['feature', 'correlation'], header=0)
    df = df.dropna().sort_values('correlation')
    df = df.set_index('feature')
    df.plot.barh(title=f'{t.capitalize().removesuffix("_i")} {heading}')
    plt.yticks(verticalalignment="center")
    plt.ylabel("Feature")
    plt.xlabel("Correlation")
    ax = plt.gca()
    ax.get_yaxis().set_ticks([])
    fig = plt.gcf()
    fig.set_dpi(200)
    fig.savefig(f'.\\graphs\\{t}_correlation_all.png', bbox_inches="tight")
    plt.clf()

    # Filter correlation for most significant increases
    df = df[(abs(df['correlation']) >= condition)]
    df.plot.barh(title=f'Significant {t.capitalize().removesuffix("_i")} {heading}')
    plt.yticks(verticalalignment="center")
    plt.ylabel("Feature")
    plt.xlabel("Correlation")
    fig = plt.gcf()
    fig.set_dpi(200)
    fig.savefig(f'.\\graphs\\{t}_correlation_significant.png', bbox_inches="tight")
    plt.clf()