import numpy as np
from sklearn.ensemble import AdaBoostRegressor
from sklearn.gaussian_process import GaussianProcessRegressor
from sklearn.kernel_ridge import KernelRidge
from sklearn.linear_model import HuberRegressor, LinearRegression, Ridge, SGDRegressor
from sklearn.neighbors import KNeighborsRegressor
from sklearn.neural_network import MLPRegressor
from sklearn.svm import LinearSVR, SVR
from sklearn.tree import DecisionTreeRegressor

class RegressionLearning:

    def __init__(self, training_set_X, training_set_y, validation_set_X, validation_set_y):
        self.models = [SVR(gamma='scale'), LinearRegression(), DecisionTreeRegressor(),
                       MLPRegressor(max_iter=1000), KNeighborsRegressor(), Ridge(),
                       HuberRegressor(), KernelRidge(), LinearSVR(max_iter=5000, tol=0.0001),
                       SGDRegressor(max_iter=1000), GaussianProcessRegressor(), AdaBoostRegressor()]
        self.training_set_X = training_set_X
        self.training_set_y = training_set_y
        self.validation_set_X = validation_set_X
        self.validation_set_y = validation_set_y

    def run(self, daily_price=100):
        self.printStartPattern()
        self.calculateBaseline(daily_price)
        for model in self.models:
            self.runRegression(model, daily_price)
        self.printEndPattern()

    def calculateBaseline(self, daily_price=100):
        print("Buy every day %i$, and sell before close" % daily_price)
        sum = []
        for i in self.validation_set_y:
            sum.append(i*daily_price)
        print("Baseline: %f$" % np.sum(sum))

    def runRegression(self, model, daily_price):
        print("------------------------------------------------------------------------------------")
        model_str = str(model).split("(")[0]
        print("Using model for %s" % model_str)

        model.fit(self.training_set_X, self.training_set_y)

        stock_price_predictions = model.predict(self.validation_set_X)

        sum = []
        for i in range(len(self.validation_set_y)):
            if stock_price_predictions[i] > 1.0:
                sum.append(self.validation_set_y[i]*daily_price)
            else:
                sum.append(daily_price)
        print("Predicted: %f$" % np.sum(sum))

        self.calculateConfusions(self.validation_set_y, stock_price_predictions)

        print("\n")

    def calculateConfusions(self, validation_set_y, stock_price_predictions):
        true_positive = 0
        false_positive = 0
        true_negative = 0
        false_negative = 0

        for i in range(len(validation_set_y)):
            if stock_price_predictions[i] > 1.0:
                if validation_set_y[i] > 1.0:
                    true_positive += 1
                else:
                    false_positive += 1
            else:
                if validation_set_y[i] > 1.0:
                    false_negative += 1
                else:
                    true_negative += 1
        print("\tTP: %i\tFP: %i\n\tFN: %i\tTN: %i" % (true_positive, false_positive, false_negative, true_negative))

    def printStartPattern(self):
        print("\n\t#######################")
        print("\t# STARTING REGRESSION #")
        print("\t#######################\n")

    def printEndPattern(self):
        print("\n\t###########################")
        print("\t# REGRESSION HAS FINISHED #")
        print("\t###########################\n")
