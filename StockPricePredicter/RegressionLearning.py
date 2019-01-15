import numpy as np
from sklearn.gaussian_process import GaussianProcessRegressor
from sklearn.kernel_ridge import KernelRidge
from sklearn.linear_model import HuberRegressor, LinearRegression, Ridge, SGDRegressor
from sklearn.neighbors import KNeighborsRegressor
from sklearn.neural_network import MLPRegressor
from sklearn.svm import LinearSVR, SVR
from sklearn.tree import DecisionTreeRegressor

class RegressionLearning:

    def __init__(self, training_set_X, training_set_y, validation_set_X, validation_set_y):
        self.models = regressions = [SVR(gamma='scale'), LinearRegression(), DecisionTreeRegressor(),
                                     MLPRegressor(max_iter=1000), KNeighborsRegressor(), Ridge(),
                                     HuberRegressor(), KernelRidge(), LinearSVR(max_iter=5000, tol=0.0001),
                                     SGDRegressor(max_iter=1000), GaussianProcessRegressor()]
        self.training_set_X = training_set_X
        self.training_set_y = training_set_y
        self.validation_set_X = validation_set_X
        self.validation_set_y = validation_set_y

    def run(self):
        self.calculateBaseline()
        for model in self.models:
            self.runRegression(model)

    def calculateBaseline(self):
        sum = []
        for i in self.validation_set_y:
            sum.append(i)
        print("Baseline: %f" % np.mean(sum))

    def runRegression(self, model):
        print("------------------------------------------------------------------------------------")
        print("Using model for %s" % model)

        model.fit(self.training_set_X, self.training_set_y)

        stock_price_predictions = model.predict(self.validation_set_X)

        sum = []
        for i in range(len(self.validation_set_y)):
            if stock_price_predictions[i] > 0.0:
                sum.append(self.validation_set_y[i])
        print("Predicted: %f" % np.mean(sum))

        self.calculateConfusions(self.validation_set_y, stock_price_predictions)

        print("\n")

    def calculateConfusions(self, validation_set_y, stock_price_predictions):
        true_positive = 0
        false_positive = 0
        true_negative = 0
        false_negative = 0

        for i in range(len(validation_set_y)):
            if stock_price_predictions[i] > 0.0:
                if validation_set_y[i] > 0.0:
                    true_positive += 1
                else:
                    false_positive += 1
            else:
                if validation_set_y[i] > 0.0:
                    false_negative += 1
                else:
                    true_negative += 1
        print("\tTP: %i\tFP: %i\n\tFN: %i\tTN: %i" % (true_positive, false_positive, false_negative, true_negative))
