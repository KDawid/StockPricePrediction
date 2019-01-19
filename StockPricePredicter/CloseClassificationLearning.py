from DataSetCreator import DataSetCreator

import numpy as np
from sklearn.ensemble import AdaBoostClassifier, GradientBoostingClassifier, RandomForestClassifier
from sklearn.gaussian_process import GaussianProcessClassifier
from sklearn.linear_model import SGDClassifier
from sklearn.neighbors import KNeighborsClassifier, RadiusNeighborsClassifier
from sklearn.neural_network import MLPClassifier
from sklearn.svm import LinearSVC
from sklearn.tree import DecisionTreeClassifier

class CloseClassificationLearning:
    def __init__(self, training_set_X, training_set_y, validation_set_X, validation_set_y, goal):
        self.models = [LinearSVC(), SGDClassifier(), KNeighborsClassifier(), RadiusNeighborsClassifier(),
                       DecisionTreeClassifier(), GaussianProcessClassifier(),
                       GradientBoostingClassifier(n_estimators=100),
                       RandomForestClassifier(), MLPClassifier(), AdaBoostClassifier()]
        self.training_set_X = training_set_X
        self.training_set_y = training_set_y
        self.validation_set_X = validation_set_X
        self.validation_set_y = validation_set_y

    def run(self, daily_price=1000, close_values=None):
        self.printStartPattern()
        self.calculateBaseline(daily_price, close_values)
        for model in self.models:
            self.runClassification(model, daily_price, close_values)
        self.printEndPattern()

    def calculateBaseline(self, daily_price=1000, close_values=None):
        sum = []
        for i in range(len(self.validation_set_y)):
            sum.append(daily_price * close_values[i])
        print("Baseline: %f$" % np.sum(sum))

    def runClassification(self, model, daily_price, close_values):
        print("------------------------------------------------------------------------------------")
        model_str = str(model).split("(")[0]
        print("Using model for %s" % model_str)

        model.fit(self.training_set_X, self.training_set_y)

        stock_price_predictions = model.predict(self.validation_set_X)

        sum = []
        for i in range(len(stock_price_predictions)):
            if stock_price_predictions[i]:
                if self.validation_set_y[i]:
                    sum.append(daily_price*close_values[i])
                else:
                    sum.append(daily_price)
            else:
                sum.append(daily_price)
        print("Predicted: %f" % np.sum(sum))

        self.calculateConfusions(self.validation_set_y, stock_price_predictions)
        print("\n")

    def calculateConfusions(self, validation_set_y, stock_price_predictions):
        true_positive = 0
        false_positive = 0
        true_negative = 0
        false_negative = 0

        for i in range(len(validation_set_y)):
            if stock_price_predictions[i]:
                if validation_set_y[i]:
                    true_positive += 1
                else:
                    false_positive += 1
            else:
                if validation_set_y[i]:
                    false_negative += 1
                else:
                    true_negative += 1
        print("\tTP: %i\tFP: %i\n\tFN: %i\tTN: %i" % (true_positive, false_positive, false_negative, true_negative))

    def printStartPattern(self):
        print("\n\t###################################")
        print("\t# STARTING 'CLOSE' CLASSIFICATION #")
        print("\t###################################\n")

    def printEndPattern(self):
        print("\n\t#######################################")
        print("\t# 'CLOSE' CLASSIFICATION HAS FINISHED #")
        print("\t#######################################\n")
