# ICPE-Data-Challenge - Analysing Static Source Code Features to Determine a Correlation to Steady State Performance in Java Microbenchmarks

## Extracting Static Source Code Features
Our solution requires that you download the `antlr-4.11.1-complete.jar` from https://www.antlr.org/download.html

After adding the jar into the `antlr-real/lib/` folder you can build a runnable jar file for the project 

## Correlation Study on Source Code Features vs Steady State Classifications
The correlation sudy extracted data from the `data/classification/` folder from https://github.com/SEALABQualityGroup/steady-state to find the correlation coefficient between each source code feature and steady state performance.

The following command will find the correlation coeffecient using Pearson's r, Spearman's rho and Kendall's tau and save the results in `correlation_study/correlations/`
```sh
python correlation.py
```

## Generating Graphs on Correlation Coefficients
The following command will graph the correlation coefficients stored in `correlation_study/correlations/`
```sh
python graph.py
```

## Predicting Steady State Classifications with Machine Learning
The following command will consider the correlation coefficients in `correlation_study/correlations/` and save the accuracy for machine learning models to predict the ability of a benchmark to reach steady state in `correlation_study/ml/`
```sh
python ml_classification.py
```
