import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.linear_model import LogisticRegression
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.neighbors import KNeighborsClassifier
from sklearn.naive_bayes import GaussianNB
from sklearn.svm import SVC
from sklearn.model_selection import train_test_split
from sklearn.metrics import confusion_matrix, classification_report

log_reg_params = [{"C":0.01}, {"C":0.1}, {"C":1}, {"C":10}]
dec_tree_params = [{"criterion": "gini"}, {"criterion": "entropy"}]
rand_for_params = [{"criterion": "gini"}, {"criterion": "entropy"}]
kneighbors_params = [{"n_neighbors":3}, {"n_neighbors":5}]
naive_bayes_params = [{}]
svc_params = [{"C":0.01}, {"C":0.1}, {"C":1}, {"C":10}]

modelclasses = [
    ["log regression", LogisticRegression, log_reg_params],
    ["decision tree", DecisionTreeClassifier, dec_tree_params],
    ["random forest", RandomForestClassifier, rand_for_params],
    ["k neighbors", KNeighborsClassifier, kneighbors_params],
    ["naive bayes", GaussianNB, naive_bayes_params],
    ["support vector machines", SVC, svc_params]
]

types = ['pearson', 'spearman', 'kendall']
condition = 0.06

df_features = pd.read_csv('features.csv')
df_run = pd.read_csv('run.csv')

df_features.drop(columns=df_features.columns[0], axis=1, inplace=True)
df_run.drop(columns=df_run.columns[0], axis=1, inplace=True)

y,labels = pd.factorize(df_run['run'])

X_train, X_test, y_train, y_test = train_test_split(df_features, df_run['run'], test_size=0.1, random_state=420)

insights = []
reports = []
for modelname, Model, params_list in modelclasses:
    for params in params_list:
        model = Model(**params)
        model.fit(X_train, y_train)
        score = model.score(X_test, y_test)
        y_pred = model.predict(df_features)

        cf_matrix = confusion_matrix(df_run, y_pred)
        ax = plt.subplot()
        hm = sns.heatmap(cf_matrix, annot=True, fmt='g', cmap='Blues')
        ax.set_xlabel('Predicted labels')
        ax.set_ylabel('True labels')
        ax.set_title(f'Confusion Matrix for {model} with Unfiltered Features')
        ax.xaxis.set_ticklabels(['steady state', 'inconsistent'])
        ax.yaxis.set_ticklabels(['inconsistent', 'steady state'])
        plt.savefig(f'ml\\heatmaps\\unfiltered_{modelname}_{model}_heatmap.png', bbox_inches="tight")
        plt.clf()

        report_pred = model.predict(X_test)

        insights.append((modelname, model, params, score))
        reports.append((modelname, model, params, classification_report(y_test,report_pred)))

insights.sort(key=lambda x:x[-1], reverse=True)
f = open("ml\\ml_classification.csv", "w")
for modelname, model, params, score in insights:
    f.write(f"unfiltered,{modelname},{params},{score}\n")
f.close()
for modelname, model, params, report in reports:
    f = open(f"ml\\reports\\unfiltered_{modelname}_{model}_report.txt", "w")
    f.write(report)
    f.close()

for t in types:

    df = pd.read_csv(f'.\\correlations\\{t}_correlation.txt', names=['feature', 'correlation'], header=0)
    df = df.dropna().sort_values('correlation')
    df = df.set_index('feature')
    df = df[(abs(df['correlation']) >= condition)]

    columns = df.index.values.tolist()

    df_features = pd.read_csv('features.csv')
    df_run = pd.read_csv('run.csv')
    
    df_features.drop(columns=df_features.columns[0], axis=1, inplace=True)
    df_run.drop(columns=df_run.columns[0], axis=1, inplace=True)

    X_train, X_test, y_train, y_test = train_test_split(df_features[columns], df_run['run'], test_size=0.1, random_state=420)

    insights = []
    reports = []
    for modelname, Model, params_list in modelclasses:
        for params in params_list:
            model = Model(**params)
            model.fit(X_train, y_train)
            score = model.score(X_test, y_test)
            y_pred = model.predict(df_features[columns])

            cf_matrix = confusion_matrix(df_run, y_pred)
            ax = plt.subplot()
            hm = sns.heatmap(cf_matrix, annot=True, fmt='g', cmap='Blues')
            ax.set_xlabel('Predicted labels')
            ax.set_ylabel('True labels')
            ax.set_title(f"Confusion Matrix for {model} with {t.capitalize()}'s Significant Features")
            ax.xaxis.set_ticklabels(['steady state', 'inconsistent'])
            ax.yaxis.set_ticklabels(['inconsistent', 'steady state'])
            plt.savefig(f'ml\\heatmaps\\{t}_{modelname}_{model}_heatmap.png', bbox_inches="tight")
            plt.clf()

            report_pred = model.predict(X_test)

            insights.append((modelname, model, params, score))
            reports.append((modelname, model, params, classification_report(y_test,report_pred)))

    insights.sort(key=lambda x:x[-1], reverse=True)
    f = open("ml\\ml_classification.csv", "a")
    for modelname, model, params, score in insights:
        f.write(f"{t},{modelname},{params},{score}\n")
    f.close()
    for modelname, model, params, report in reports:
        f = open(f"ml\\reports\\{t}_{modelname}_{model}_report.txt", "w")
        f.write(report)
        f.close()