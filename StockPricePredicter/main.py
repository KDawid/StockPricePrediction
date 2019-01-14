import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

from sklearn.gaussian_process import GaussianProcessRegressor
from sklearn.kernel_ridge import KernelRidge
from sklearn.linear_model import HuberRegressor, LinearRegression, Ridge, SGDRegressor
from sklearn.metrics import mean_squared_error, r2_score
from sklearn.neighbors import KNeighborsRegressor
from sklearn.neural_network import MLPRegressor
from sklearn.svm import LinearSVR, SVR
from sklearn.tree import DecisionTreeRegressor

USED_DAYS = 10
VALIDATION_SET_SPLIT = 2000
CSV_FILE_PATH = "AAPL_normalized.csv"


def create_learning_data(data_set, used_days):
    learning_data_X = []
    learning_data_y = []
    for i in range(used_days, len(data_set)):
        element = []
        for day in range(1, used_days):
            for j in [keys.index(key) for key in keys if key != "Date" and key != "Open"]:
                element.append(data_set[i - day][j])
        learning_data_y.append(data_set[i][keys.index("Close")])
        learning_data_X.append(element)
    return learning_data_X, learning_data_y


data = pd.read_csv(CSV_FILE_PATH)

keys = list(data.keys().get_values())
data = data.values

data_X, data_y = create_learning_data(data, USED_DAYS)

training_set_X, training_set_y = data_X[:VALIDATION_SET_SPLIT], data_y[:VALIDATION_SET_SPLIT]
validation_set_X, validation_set_y = data_X[VALIDATION_SET_SPLIT:], data_y[VALIDATION_SET_SPLIT:]

sum = []
for i in validation_set_y:
    sum.append(i)
print("Baseline: %f" % np.mean(sum))

regressions = [SVR(), LinearRegression(), DecisionTreeRegressor(), MLPRegressor(), KNeighborsRegressor(), Ridge(), HuberRegressor(), KernelRidge(), LinearSVR(), SGDRegressor(), GaussianProcessRegressor()]

#regr = SVR()
#regr = LinearRegression()
#regr = DecisionTreeRegressor()
#regr = MLPRegressor()
#regr = KNeighborsRegressor()
#regr = Ridge()
#regr = HuberRegressor()
#regr = KernelRidge()
#regr = LinearSVR()
#regr = SGDRegressor()
#regr = GaussianProcessRegressor()

for regression in regressions:
    print("------------------------------------------------------------------------------------")
    print("Using model for %s" % regression)
    regr = regression

    regr.fit(training_set_X, training_set_y)

    stock_prices_predictions = regr.predict(validation_set_X)

    sum = []
    for i in range(len(validation_set_y)):
        if stock_prices_predictions[i] > 0.0:
            sum.append(validation_set_y[i])
    print("Predicted: %f" % np.mean(sum))

    true_positive = 0
    false_positive = 0
    true_negative = 0
    false_negative = 0

    for i in range(len(validation_set_y)):
        if stock_prices_predictions[i] > 0.0:
            if validation_set_y[i] > 0.0:
                true_positive += 1
            else:
                false_positive += 1
        else:
            if validation_set_y[i] > 0.0:
                false_negative += 1
            else:
                true_negative += 1
    print("TP: %i\t FP: %i\tFN: %i\tTN: %i" % (true_positive, false_positive, false_negative, true_negative))
    print("\n")
